package com.xtl.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author 31925
 */
public class JmsProducer {
    /**
     * activeMQ服务地址
     *     private static final String BROKER_URL = "tcp://192.168.188.128:61616";
     *     private static final String BROKER_URL = "tcp://localhost:61616";
     */
    private static final String BROKER_URL = "tcp://192.168.188.128:61616";

    /**
     * 消息队列名称
     */
    private static final String QUEUE_NAME = "jdbc";

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
        // 根据session创建队列
        Queue queue = session.createQueue(QUEUE_NAME);
        // 创建消息的生产者并指定队列
        MessageProducer messageProducer = session.createProducer(queue);
//        开启持久化
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        // 通过使用消息生产者,生产消息,发送到MQ的队列里面
        for (int i = 1; i <= 3; i++) {
            TextMessage textMessage = session.createTextMessage("hello_activeMQ_Server:\t" + i);
            if(i%2==0){
                textMessage.setStringProperty("vip","vip客户专属通道");
            }
            messageProducer.send(textMessage);

            MapMessage mapMessage=session.createMapMessage();
            mapMessage.setString("job","teacher");
            messageProducer.send(mapMessage);
        }
        // 关闭资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("**********msg send success**********");
    }
}
