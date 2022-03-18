package com.xtl.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author 31925
 */
public class JmsConsumer {
/**
 * private static final String BROKER_URL = "tcp://192.168.188.128:61616";
 * private static final String BROKER_URL = "tcp://localhost:61616";
 */
    private static final String BROKER_URL = "tcp://192.168.188.128:61616";
    private static final String QUEUE_NAME = "jdbc";


    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory =new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        // 创建消息消费者并指定消费的队列
        MessageConsumer messageConsumer = session.createConsumer(queue);
        while (true) {
            /*
             * receive() 一直等待接收消息，在能够接收到消息之前将一直阻塞。 是同步阻塞方式 。和socket的accept方法类似的。
             * receive(Long time) : 等待n毫秒之后还没有收到消息，就是结束阻塞。
             * 因为发送消息的时候是TextMessage类型，所以消费消息的时候也要是TextMessage类型，要以一一对应
             */
            Message message = messageConsumer.receive();
            if(message==null){
                break;
            }
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                System.out.println("消费掉的TextMessage消息 -> " + textMessage.getText());
                System.out.println("消息的vip情况是-> "+textMessage.getStringProperty("vip"));
            }
            if (message instanceof MapMessage) {
                MapMessage mapMessage = (MapMessage) message;
                System.out.println("消费掉的MapMessage消息 -> " + mapMessage.getString("job"));
            }
        }
        // 关闭资源
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
