package com.xtl.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author 31925
 */
public class JmsTopicConsumer {
    private static final String BROKER_URL = "tcp://192.168.188.128:61616";
    private static final String TOPIC_NAME = "first_topic";


    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory =new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic=session.createTopic(TOPIC_NAME);
        // 创建消息消费者并指定消费的队列
        MessageConsumer messageConsumer = session.createConsumer(topic);
        while (true) {
            /*
             * receive() 一直等待接收消息，在能够接收到消息之前将一直阻塞。 是同步阻塞方式 。和socket的accept方法类似的。
             * receive(Long time) : 等待n毫秒之后还没有收到消息，就是结束阻塞。
             * 因为发送消息的时候是TextMessage类型，所以消费消息的时候也要是TextMessage类型，要以一一对应
             */
            TextMessage textMessage = (TextMessage) messageConsumer.receive();
            if(textMessage == null){
                break;
            }
            System.out.println("消费掉的Topic消息 -> " + textMessage.getText());
        }
        // 关闭资源
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
