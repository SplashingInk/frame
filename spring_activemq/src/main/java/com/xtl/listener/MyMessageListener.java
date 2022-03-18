package com.xtl.listener;

import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * @author 31925
 */
@Component
public class MyMessageListener  implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            TextMessage textMessage=(TextMessage)message;
            try {
                System.out.println(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        if(message instanceof MapMessage){
            MapMessage mapMessage=(MapMessage) message;
             try{
                 System.out.println(mapMessage.getString("name"));
             }catch(Exception e){
                e.printStackTrace();
             }
        }
    }
}
