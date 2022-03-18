package com.xtl.producer;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.xtl.util.ConnectUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author 31925
 */
public class AckProducer {
    private final static String QUEUE_NAME = "ack_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection =  ConnectUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明（创建）一个队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()&&!"quit".equals(scanner.next())){
            String msg=scanner.next();
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("发送消息："+msg);
        }
        //5 记得要关闭相关的连接
        channel.close();
        connection.close();
    }
}
