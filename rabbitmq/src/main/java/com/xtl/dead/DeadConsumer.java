package com.xtl.dead;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.xtl.util.ConnectUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author 31925
 */
public class DeadConsumer {
    private static final String DEAD_QUEUE="dead_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection =  ConnectUtil.getConnection();
        Channel channel = connection.createChannel();
        DeliverCallback deliverCallback=(consumerTag, delivery)->{
            System.out.println("DeadConsumer接收到的消息是："+new String(delivery.getBody(), StandardCharsets.UTF_8));
        };
        channel.basicConsume(DEAD_QUEUE,true,deliverCallback,cancelCallback->{});
    }
}
