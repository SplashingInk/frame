package com.xtl.dead;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xtl.util.ConnectUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 31925
 */
public class Producer {
    private static final String NORMAL_EXCHANGE="normal_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection =  ConnectUtil.getConnection();
        Channel channel = connection.createChannel();
//        设置死信消息的TTL，单位是ms
        AMQP.BasicProperties properties=new AMQP.BasicProperties().builder().expiration("100000").build();
        for (int i = 0; i <10 ; i++) {
            String msg="test ttl to death:"+i;
            channel.basicPublish(NORMAL_EXCHANGE,"normal",properties,msg.getBytes());
            System.out.println("发送消息："+msg);
        }

        for (int i = 0; i <10 ; i++) {
            String msg="test_max_length to death:"+i;
            channel.basicPublish(NORMAL_EXCHANGE,"normal",null,msg.getBytes());
            System.out.println("发送消息："+msg);
        }
        channel.close();
        connection.close();
    }
}
