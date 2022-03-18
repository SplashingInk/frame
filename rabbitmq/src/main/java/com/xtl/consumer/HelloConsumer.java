package com.xtl.consumer;

import com.rabbitmq.client.*;
import com.xtl.util.ConnectUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author 31925
 */
public class HelloConsumer {
    private final static String QUEUE_NAME = "helloMQ";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1 创建连接
        Connection connection = ConnectUtil.getConnection();
        //2通过connection创建一个Channel
        Channel channel = connection.createChannel();
        Map<String,Object> map=new HashMap<>(16);
        //设置队列的优先级，范围是0-255，数值越大，优先级越高，不建议设置值过大
        map.put("x-max-priority",10);
        //3 声明（创建）一个队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, map);
        //5 创建消费者
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String msg = new String(body, StandardCharsets.UTF_8);
                System.out.println("消费端:" + msg);
            }
        };
        //6 订阅消息
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
