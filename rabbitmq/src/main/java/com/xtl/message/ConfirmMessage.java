package com.xtl.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.xtl.util.ConnectUtil;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author 31925
 */
public class ConfirmMessage {
    private static final int COUNT=1000;

    public static void main(String[] args) throws Exception {
        ConfirmMessage.confirmMessageByOne();
        ConfirmMessage.confirmMessageBatch();
        ConfirmMessage.confirmMessageAsync();
    }

    public static  void  confirmMessageByOne()throws Exception{
        Connection connection = ConnectUtil.getConnection();
        Channel channel = connection.createChannel();
//        开启发布确认
        channel.confirmSelect();
        String queenName = UUID.randomUUID().toString();
        channel.queueDeclare(queenName,true,false,false,null);
        long start=System.currentTimeMillis();
        String msg="";
        for (int i = 0; i < COUNT; i++) {
            msg=i+"";
            channel.basicPublish("",queenName, MessageProperties.PERSISTENT_TEXT_PLAIN,msg.getBytes(StandardCharsets.UTF_8));
            boolean flag=channel.waitForConfirms();
            if(flag){
                System.out.println("消息发送成功");
            }
        }
        long end=System.currentTimeMillis();
        System.out.println("发送"+COUNT+" 条消息,采用单个发布确认，耗时时长为："+(end-start)+"ms");
        channel.close();
        connection.close();
    }

    public static  void  confirmMessageBatch()throws Exception{
        Connection connection = ConnectUtil.getConnection();
        Channel channel = connection.createChannel();
//        开启发布确认
        channel.confirmSelect();
        String queenName = UUID.randomUUID().toString();
        channel.queueDeclare(queenName,true,false,false,null);
        long start=System.currentTimeMillis();
        String msg="";
        int batchSize =100;
        for (int i = 0; i < COUNT; i++) {
            msg=i+"";
            channel.basicPublish("",queenName, MessageProperties.PERSISTENT_TEXT_PLAIN,msg.getBytes(StandardCharsets.UTF_8));
            if(i%batchSize==0){
                boolean flag=channel.waitForConfirms();
                if(flag){
                    System.out.println("消息发送成功");
                }
            }
        }
        long end=System.currentTimeMillis();
        System.out.println("发送"+COUNT+" 条消息,采用批量发布确认，耗时时长为："+(end-start)+"ms");
        channel.close();
        connection.close();
    }

    public static  void  confirmMessageAsync()throws Exception{
        Connection connection = ConnectUtil.getConnection();
        Channel channel = connection.createChannel();
//        开启发布确认
        channel.confirmSelect();
        String queenName = UUID.randomUUID().toString();
        channel.queueDeclare(queenName,true,false,false,null);
        /*
         * 线程安全有序的一份哈希表 适用于高并发的情况下
         * 1、轻松的将序号与消息进行关联
         * 2、轻松的批量删除条目 只要给到序号
         * 3、支持高并发(多线程)
         */
        ConcurrentSkipListMap<Long,String> outstandingConfirms=new ConcurrentSkipListMap<>();



        //消息确认成功 回调函数
        ConfirmCallback ackCallback=(deliveryTag, multiple) -> {
            if(multiple){   //批量
                //2、删除掉已经确认的消息 剩下的就是未确认的消息
                ConcurrentNavigableMap<Long, String> confirmed =
                        outstandingConfirms.headMap(deliveryTag);
                confirmed.clear();
            }else{          //单个
                outstandingConfirms.remove(deliveryTag);
            }
            System.out.println("确认的消息："+deliveryTag);
        };
        //消息确认失败 回调函数
        /*
         * 参数1：消息的标记
         * 参数2：是否为批量确认
         */
        ConfirmCallback nackCallback=(deliveryTag, multiple) -> {
            //3、打印一下未确认的消息都有哪些
            String message = outstandingConfirms.get(deliveryTag);
            System.out.println("未确认的消息是："+message+":::未确认的消息tag："+deliveryTag);
        };

        //准备消息的监听器 监听哪些消息成功了 哪些消息失败了
        /*
         * 参数1：监听哪些消息成功了
         * 参数2：哪些消息失败了
         */
        channel.addConfirmListener(ackCallback,nackCallback);
        long start=System.currentTimeMillis();
        String msg="";
        for (int i = 0; i < COUNT; i++) {
            msg=i+"";
            channel.basicPublish("",queenName, MessageProperties.PERSISTENT_TEXT_PLAIN,msg.getBytes(StandardCharsets.UTF_8));
            //1、此处记录下所有要发送的消息 消息的总和
            outstandingConfirms.put(channel.getNextPublishSeqNo(),msg);
        }
        long end=System.currentTimeMillis();
        System.out.println("发送"+COUNT+" 条消息,采用异步发布确认，耗时时长为："+(end-start)+"ms");
        channel.close();
        connection.close();
    }
}
