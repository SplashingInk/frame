package com.xtl.producer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.MapMessage;

/**
 * @author 31925
 */
@Service
public class MqProducer {
    @Resource
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        MqProducer produce=(MqProducer)applicationContext.getBean("mqProducer");
        produce.jmsTemplate.send((session -> {
            return session.createTextMessage("*****spring整合activemq的demo*****");
        }));
        produce.jmsTemplate.send(session -> {
            MapMessage message = session.createMapMessage();
            message.setString("name","姬如雪");
            return  message;
        });
        System.out.println("发送完毕");
    }
}
