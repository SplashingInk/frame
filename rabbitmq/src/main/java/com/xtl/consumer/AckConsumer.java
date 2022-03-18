package com.xtl.consumer;

import com.rabbitmq.client.*;
import com.xtl.util.ConnectUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author 31925
 */
public class AckConsumer {
    private final static String QUEUE_NAME = "ack_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectUtil.getConnection();
        Channel channel = connection.createChannel();
        DeliverCallback deliverCallback=(consumerTag,message)->{
            try {
                Thread.sleep(2000);
                System.out.println("接收到消息："+new String(message.getBody(), StandardCharsets.UTF_8));
//                第一个参数表示消息的标记，第二个参数表示是否批量应答，false表示不批量应答
                channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
//        设置不公平分发，能者多劳
        int prefetch=2;
        channel.basicQos(prefetch);
//        手动应答
        boolean autoAck=false;
        channel.basicConsume(QUEUE_NAME,autoAck,deliverCallback,(consumerTag->{
            System.out.println(consumerTag+"\t  消息被取消");
        }));
    }
}
