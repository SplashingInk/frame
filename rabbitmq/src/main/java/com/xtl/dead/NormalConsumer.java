package com.xtl.dead;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.xtl.util.ConnectUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author 31925
 */
public class NormalConsumer {
    private static final String NORMAL_EXCHANGE="normal_exchange";
    private static final String DEAD_EXCHANGE="dead_exchange";
    private static final String NORMAL_QUEUE="normal_queue";
    private static final String DEAD_QUEUE="dead_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection =  ConnectUtil.getConnection();
        Channel channel = connection.createChannel();
        Map<String,Object> map=new HashMap<>(16);
        map.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        map.put("x-dead-letter-routing-key","dead");
//        设置队列的长度
        map.put("x-max-length",6);
        channel.queueDeclare(NORMAL_QUEUE,true,false,false,map);
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.queueBind(NORMAL_QUEUE,NORMAL_EXCHANGE,"normal");

        channel.queueDeclare(DEAD_QUEUE,true,false,false,null);
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"dead");

        System.out.println("等待接收消息。。。。。。。。。。。。。。");

        DeliverCallback deliverCallback=(consumerTag,delivery)->{
            String msg=new String(delivery.getBody(), StandardCharsets.UTF_8);
            if("test ttl to death:4".equals(msg)||"test_max_length to death:2".equals(msg)){
                System.out.println("NormalConsumer接收到的消息是："+msg);
                //false表示不会重新入队
                channel.basicReject(delivery.getEnvelope().getDeliveryTag(),false);
            }else{
                System.out.println("NormalConsumer接收到的消息是："+msg);
                //false表示不进行批量确认
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
            }
        };
        channel.basicConsume(NORMAL_QUEUE,false,deliverCallback,cancelCallback->{});
    }
}
