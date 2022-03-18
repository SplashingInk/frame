package com.xtl.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 31925
 */
@Service
public class MqConsumer {
    @Resource
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        MqConsumer mqConsumer=(MqConsumer)applicationContext.getBean("mqConsumer");
        String result=(String)mqConsumer.jmsTemplate.receiveAndConvert();
        System.out.println(result);
    }
}
