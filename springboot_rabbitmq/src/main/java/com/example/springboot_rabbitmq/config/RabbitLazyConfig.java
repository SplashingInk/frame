package com.example.springboot_rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 31925
 */
@Configuration
public class RabbitLazyConfig {
    public static final String LAZY_TOPIC_EXCHANGE = "lazy.topic.exchange";
    public static final String LAZY_TOPIC_QUEUE = "lazy_topic_queue";
    public static final String LAZY_TOPIC_ROUTING_KEY = "*.topic.*";

    @Bean
    public Queue topicLazyQueue(){
        Map<String, Object> args =new HashMap<>(16);
        args.put("x-queue-mode", "lazy");
        return QueueBuilder.durable(LAZY_TOPIC_QUEUE).withArguments(args).build();
    }

    @Bean
    public TopicExchange topicLazyExchange(){
        return new TopicExchange(LAZY_TOPIC_EXCHANGE);
    }

    @Bean
    public Binding bindingTopicLazyExchangeQueue(){
        return BindingBuilder.bind(topicLazyQueue()).to(topicLazyExchange()).with(LAZY_TOPIC_ROUTING_KEY);
    }

}
