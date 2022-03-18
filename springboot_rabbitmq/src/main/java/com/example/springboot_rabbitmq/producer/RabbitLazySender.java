package com.example.springboot_rabbitmq.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author 31925
 */
@Component
public class RabbitLazySender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 扩展点，在消息转换完成之后，发送之前调用；可以修改消息属性、消息头信息
     */
    private final MessagePostProcessor messagePostProcessor = message -> {
        MessageProperties properties = message.getMessageProperties();
        /*
         * 设置消息发送到队列之后多久被丢弃，单位：毫秒
         * 此种方案需要每条消息都设置此属性，比较灵活；
         * 还有一种方案是在声明队列的时候指定发送到队列中的过期时间；
         * * Queue queue = new Queue("test_queue2");
         * * queue.getArguments().put("x-message-ttl", 10000);
         * 这两种方案可以同时存在，以值小的为准
         */
        //properties.setExpiration("10000");
        /*
         * 设置消息的优先级
         */
        properties.setPriority(9);
        /*
         * 设置消息发送到队列中的模式，持久化|非持久化（只存在于内存中）
         */
        properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        return message;
    };

    public void sendMsg(String exchange, String routingKey, String message, MessageProperties properties){
        /*
         * 新增消息转换完成后、发送之前的扩展点
         */
        this.rabbitTemplate.setBeforePublishPostProcessors(messagePostProcessor);
        try {
            if(null == properties){
                properties = new MessageProperties();
            }
            /*
             * 设置消息唯一标识
             */
            properties.setMessageId(UUID.randomUUID().toString());
            /*
             * 创建消息包装对象
             */
            Message msg = MessageBuilder.withBody(message.getBytes()).andProperties(properties).build();
            /*
             * 将消息主题和属性封装在Message类中
             */
            Message returnedMessage = MessageBuilder.withBody(message.getBytes()).build();
            /*
             * 相关数据
             */
            CorrelationData correlationData = new CorrelationData();
            /*
             * 消息ID，全局唯一
             */
            correlationData.setId(msg.getMessageProperties().getMessageId());
            /*
             * 如果msg是org.springframework.amqp.core.Message对象的实例，则直接返回，否则转化为Message对象
             */
            this.rabbitTemplate.convertAndSend(exchange, routingKey, msg, correlationData);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
