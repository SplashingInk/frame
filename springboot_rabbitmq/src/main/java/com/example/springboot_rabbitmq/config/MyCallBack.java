package com.example.springboot_rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author 31925
 */
@Slf4j
@Component
public class MyCallBack implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }
    /** * 交换机确认回调方法 * 1、发消息 交换机接收到了 回调 * 1.1 correlationData保存回调消息的ID及相关信息 * 1.2 交换机收到消息 ack=true * 1.3 cause null * 2、发消息 交换机接受失败了 回调 * 2.1 correlationData保存回调消息的ID及相关信息 * 2.2 交换机收到消息 ack=false * 2.3 cause 失败的原因 */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData != null ? correlationData.getId() : "";
        if(ack){
            log.info("交换机已经收到Id为：{}的消息",id);
        }else{
            log.info("交换机还未收到Id为:{}的消息，由于原因:{}",id,cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("消息{},被交换机{}退回，退回的原因：{},路由Key:{}",new String(message.getBody()),
                exchange,replyText,routingKey);
    }
}