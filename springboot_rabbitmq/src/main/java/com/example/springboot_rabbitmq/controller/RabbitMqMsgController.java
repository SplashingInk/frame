package com.example.springboot_rabbitmq.controller;

import com.example.springboot_rabbitmq.config.DelayTypeEnum;
import com.example.springboot_rabbitmq.producer.DelayMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * @author 31925
 */
@Slf4j
@RequestMapping("rabbitmq")
@RestController
public class RabbitMqMsgController {

    @Resource
    private DelayMessageSender sender;

    @ResponseBody
    @RequestMapping("sendMessage")
    public String sendMsg(String msg, Integer delayType){
        log.info("当前时间：{},收到请求，msg:{},delayType:{}", new Date(), msg, delayType);
        sender.sendMsg(msg, Objects.requireNonNull(DelayTypeEnum.getDelayTypeEnumByValue(delayType)));
        return "success";
    }

    @ResponseBody
    @RequestMapping("delayMsg")
    public String delayMsg(String msg, Integer delayTime) {
        log.info("当前时间：{},收到请求，msg:{},delayTime:{}", new Date(), msg, delayTime);
        sender.sendDelayMsg(msg, delayTime);
        return "success";
    }

}
