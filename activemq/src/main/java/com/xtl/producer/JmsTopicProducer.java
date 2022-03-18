package com.xtl.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author 31925
 */
public class JmsTopicProducer {
    /**
     * activeMQ服务地址
     */
    private static final String BROKER_URL = "tcp://192.168.188.128:61616";

    /**
     * 消息队列名称
     */
    private static final String TOPIC_NAME = "first_topic";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory =new ActiveMQConnectionFactory(BROKER_URL);
        // 通过连接工厂,获得connection
        Connection connection = factory.createConnection();
        connection.start();
        /*
         * 创建session，得到会话
         * 两个参数transacted=事务,acknowledgeMode=确认模式(签收)
         */
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 根据session创建主题队列
        Topic topic=session.createTopic(TOPIC_NAME);
        // 创建消息的生产者并指定队列
        MessageProducer messageProducer = session.createProducer(topic);
        // 通过使用消息生产者,生产消息,发送到MQ的队列里面
        for (int i = 1; i <= 10; i++) {
            TextMessage textMessage = session.createTextMessage("主题消息:\t" + i);
            messageProducer.send(textMessage);
        }
        // 关闭资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("**********msg send success**********");
    }
}
