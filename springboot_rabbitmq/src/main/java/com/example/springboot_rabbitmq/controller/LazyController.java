package com.example.springboot_rabbitmq.controller;

import com.example.springboot_rabbitmq.config.ConfirmConfig;
import com.example.springboot_rabbitmq.producer.RabbitLazySender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.example.springboot_rabbitmq.config.RabbitLazyConfig.LAZY_TOPIC_EXCHANGE;

/**
 * @author 31925
 */
@Slf4j
@RestController
@RequestMapping("/lazy")
public class LazyController {

    @Resource
    private RabbitLazySender rabbitLazySender;

    @ResponseBody
    @GetMapping("/lazyMessage")
    public String sendMessage(){
        for(long i=0;i<10000;i++){
            String msg=i+"";
            rabbitLazySender.sendMsg(LAZY_TOPIC_EXCHANGE,"item.topic.hello",msg,null);
        }
        return "success";
    }
}
