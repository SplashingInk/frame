package com.xtl.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author 31925
 */
public class JmsTopicPersistentConsumer2 {
    private static final String BROKER_URL = "tcp://192.168.188.128:61616";
    private static final String TOPIC_NAME = "persistent_topic";


    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory =new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = factory.createConnection();
        connection.setClientID("姬如雪");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic=session.createTopic(TOPIC_NAME);
        TopicSubscriber subscriber = session.createDurableSubscriber(topic, "remark...");
        connection.start();
        Message message = subscriber.receive();
        while (null!=message){
            TextMessage textMessage=(TextMessage) message;
            System.out.println("消费掉的Topic消息 -> " + textMessage.getText());
            message = subscriber.receive();
        }
        // 关闭资源
        session.close();
        connection.close();
    }
}
