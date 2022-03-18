package com.xtl.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xtl.util.ConnectUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 31925
 */
public class DirectProducer {
    private final static String EXCHANGE_NAME = "direct_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection =  ConnectUtil.getConnection();
        Channel channel = connection.createChannel();
//        第一个参数是交换机名称，第二个参数是交换机类型
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        String msg="测试Direct类型的工作模式，路由键必须完全匹配";
        channel.basicPublish(EXCHANGE_NAME,"hello_world",null,msg.getBytes());
        System.out.println("消息已经发送出去："+msg);
        channel.close();
        connection.close();
    }
}
