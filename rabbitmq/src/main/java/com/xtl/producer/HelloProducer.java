package com.xtl.producer;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xtl.util.ConnectUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author 31925
 */
public class HelloProducer {
    private final static String QUEUE_NAME = "helloMQ";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection =  ConnectUtil.getConnection();
        Channel channel = connection.createChannel();
        Map<String,Object> map=new HashMap<>(16);
        //设置队列的优先级，范围是0-255，数值越大，优先级越高，不建议设置值过大
        map.put("x-max-priority",10);
        // 声明（创建）一个队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, map);
        for(int i=0; i < 10; i++){
            String msg = "Hello RabbitMQ!";
            /*
             * 1 exchange
             * 2 routingKey
             * 3 传递AMQP.BasicProperties属性信息
             * 4 消息
             */
            AMQP.BasicProperties properties=new AMQP.BasicProperties().builder().build();
            if(i==5){
                msg+=" 优先级最高";
                properties=new AMQP.BasicProperties().builder().priority(8).build();
            }
            if(i==8){
                msg+=" 优先级次之";
                properties=new AMQP.BasicProperties().builder().priority(5).build();
            }
            channel.basicPublish("", QUEUE_NAME,properties , msg.getBytes());
        }

        //5 记得要关闭相关的连接
        channel.close();
        connection.close();
    }
}
