package com.xtl.consumer;

import com.rabbitmq.client.*;
import com.xtl.util.ConnectUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author 31925
 */
public class FanoutConsumer {
    private final static String EXCHANGE_NAME = "fanout_test";
    private final static String QUEUE_NAME="fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection =  ConnectUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
//        第一个参数是交换机名称，第二个参数是交换机类型
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
//        将队列绑定到交换机上
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");
        //5 创建消费者
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String msg = new String(body, StandardCharsets.UTF_8);
                System.out.println("消费端:" + msg);
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
