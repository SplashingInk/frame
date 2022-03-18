package com.xtl.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author 31925
 */
public class JmsTopicConsumer2 {
    private static final String BROKER_URL = "tcp://192.168.188.128:61616";
    private static final String TOPIC_NAME = "first_topic";


    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory factory =new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic=session.createTopic(TOPIC_NAME);
        // 创建消息消费者并指定消费的队列
        MessageConsumer messageConsumer = session.createConsumer(topic);
        /*
         * 通过监听的方式来消费消息，是以异步非阻塞的方式来消费消息
         * 通过messageConsumer 的setMessageListener 注册一个监听器，当有消息发送来时，系统自动调用MessageListener 的 onMessage 方法处理消息
         */
        messageConsumer.setMessageListener((message) -> {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("消费掉的Topic消息 -> " + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        /*
         * 此处是为了不让主线程结束，因为一旦主线程结束了，其他的线程（如此处的监听消息的线程）也都会被迫结束。
         * 实际开发中不需要
         */
        System.in.read();
        // 关闭资源
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
