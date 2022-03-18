package com.xtl.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 31925
 */
public class ConnectUtil {
    public static Connection getConnection() throws IOException, TimeoutException {
        //1 创建一个ConnectionFactory, 并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //服务器的IP地址
        connectionFactory.setHost("192.168.188.128");
        //端口
        connectionFactory.setPort(5672);
        //指定HOST
        connectionFactory.setVirtualHost("/test");
        //登录名
        connectionFactory.setUsername("joker");
        //密码
        connectionFactory.setPassword("123456");
        //2 通过连接工厂创建连接
        return connectionFactory.newConnection();
    }
}
