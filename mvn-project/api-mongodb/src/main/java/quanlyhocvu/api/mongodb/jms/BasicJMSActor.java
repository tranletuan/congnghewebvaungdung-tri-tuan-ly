/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.jms;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

/**
 *
 * @author Tuan
 */
public class BasicJMSActor {

        private TopicConnection topicConnection;
    private QueueConnection queueConnection;
    private BrokerService broker;

    protected TopicSession topicSession;
    protected QueueSession queueSession;

    protected MessageProducer replyProducer;

    protected String BrokerUrl = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
    protected boolean transacted = false;
    protected int ackMode = Session.AUTO_ACKNOWLEDGE;

    //Kết nối với broker 
    protected void connectionBroker() {
        try {
            broker = new BrokerService();
            broker.setPersistent(false);
            broker.setUseJmx(false);
            broker.addConnector(BrokerUrl);
            broker.start();

        } catch (Exception e) {

        }
    }

    //Hủy kết nối với broker
    protected void disconnectionBrokder() {
        try {
            this.topicConnection.stop();
            this.topicConnection.close();
            this.queueConnection.close();

        } catch (Exception e) {
            System.out.println("Loi dong ket noi");

        }
    }

    protected void closeQueueConnection() {
        try {
            this.queueSession.close();
            this.queueConnection.close();
        } catch (Exception e) {

        }
    }

    protected void closeTopicConnection() {
        try {
            this.topicSession.close();
            this.topicConnection.close();
        } catch (Exception e) {

        }
    }

    //Tạo kết nối với topic
    protected void connectionToTopic() {
        ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(BrokerUrl);
        try {
            topicConnection = cf.createTopicConnection();
            topicConnection.start();
            this.topicSession = topicConnection.createTopicSession(transacted, ackMode);
        } catch (JMSException e) {
            System.out.println("Error: " + e.getErrorCode());
        }
    }

    //Tạo kết nối với queue
    protected void connectionToQueue() {
        ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(BrokerUrl);
        try {
            queueConnection = cf.createQueueConnection();
            queueConnection.start();
            this.queueSession = queueConnection.createQueueSession(transacted, ackMode);

            this.replyProducer = this.queueSession.createProducer(null);
            this.replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        } catch (JMSException e) {
            System.out.println("Error: " + e.getErrorCode());
        }
    }

    //Gửi thông điêp đến Topic
    protected void sendMessageToTopic(String topicName, String text) throws JMSException {
        Topic topic = this.topicSession.createTopic(topicName);

        //Tạo 1 publisher gửi thông điệp
        TopicPublisher topicPublisher = this.topicSession.createPublisher(topic);
        topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        //Tạo thông điệp gửi
        TextMessage textMessage = this.topicSession.createTextMessage();
        textMessage.setJMSReplyTo(topic);
        textMessage.setText(text);

        topicPublisher.send(topic, textMessage);
    }

    //Gửi thông điệp đến Queue
    protected void sendMessageToQueue(String queueName, String text) throws JMSException {
        Queue queue = this.queueSession.createQueue(queueName);

        //Tạo 1 producer gửi thông điệp
        QueueSender queueSender = queueSession.createSender(queue);
        queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        //Tạo thông điệp gửi
        TextMessage textMessage = queueSession.createTextMessage();
        textMessage.setText(text);

        queueSender.send(textMessage);
    }

    protected void receiveMessageFromTopic(String topicName, MessageListener listener) {
        try {
            Topic topic = this.topicSession.createTopic(topicName);

            //Tạo subcriber nhận thông điệp
            TopicSubscriber topicSubscriber = this.topicSession.createSubscriber(topic);

            topicSubscriber.setMessageListener(listener);
        } catch (JMSException e) {
            System.out.println("Error receive message from topic");
        }
    }

    protected void receiveMessageFromQueue(String queueName, MessageListener listener) {
        try {
            Queue queue = this.queueSession.createQueue(queueName);

            //Tạo receiver nhận thông điệp
            QueueReceiver receiver = this.queueSession.createReceiver(queue);

            receiver.setMessageListener(listener);
        } catch (Exception e) {
            System.out.println("Error receive message from queue");
        }
    }
}
