package com.xtl.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xtl.util.ConnectUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 31925
 */
public class TopicProducer {
    private final static String EXCHANGE_NAME = "topic_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection =  ConnectUtil.getConnection();
        Channel channel = connection.createChannel();
//        第一个参数是交换机名称，第二个参数是交换机类型
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");
        String msg="测试Topic类型的工作模式，路由键采用模式匹配，支持正则表达式";
        channel.basicPublish(EXCHANGE_NAME,"item.insert",null,msg.getBytes());
        System.out.println("消息已经发送出去："+msg);
        String note="#表示匹配一个或多个单词，*表示匹配一个单词";
        channel.basicPublish(EXCHANGE_NAME,"item.update.one",null,note.getBytes());
        System.out.println("消息已经发送出去："+note);
        channel.close();
        connection.close();
    }
}
