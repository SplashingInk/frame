package com.example.springboot_rabbitmq.consumer;

import com.example.springboot_rabbitmq.config.RabbitLazyConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author 31925
 */
@Component
public class RabbitLazyReceiver {

    @RabbitListener(queues = RabbitLazyConfig.LAZY_TOPIC_QUEUE)
    public void onMessage(Channel channel, Message<Object> message) throws Exception {
        System.out.println("--------------------------------------");
        System.out.println("消费端Payload: " + message.getPayload()+"-ID:"+message.getHeaders().getId()+"-messageId:"+message.getHeaders());
        channel.basicAck((Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG),
                false);
    }
}
