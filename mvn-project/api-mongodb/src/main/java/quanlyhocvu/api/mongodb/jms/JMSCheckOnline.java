/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.jms;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicPublisher;

/**
 *
 * @author Tuan
 */
public class JMSCheckOnline extends BasicJMSActor implements MessageListener {

    private String idActor;
    
    public static long timeOut = 2000;

    private List<String> listIdOnline;

    private final String topicName = "Check_Online";

    public JMSCheckOnline() {
        listIdOnline = new ArrayList<String>();
    }

    public void connectionAndReceiveMessage(String idActor) {

        this.idActor = idActor;
        this.connectionBroker();
        this.connectionToQueue();
        this.connectionToTopic();
        this.receiveMessageFromTopic(topicName, new onReceiveMessageFromTopic(idActor, queueSession, replyProducer));

    }
    
    public void closeConnection() {
        this.disconnectionBrokder();
    }

    public void sendMessageOnline(String text) throws JMSException {
        listIdOnline.clear();
        System.out.println(listIdOnline);
        Topic topic = this.topicSession.createTopic(topicName);

        //Tạo 1 publisher gửi thông điệp đến các client trạm
        TopicPublisher topicPublisher = this.topicSession.createPublisher(topic);
        topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        //Tạo bộ nhận phản hồi thông điệp
        Queue tempQueue = this.queueSession.createTemporaryQueue();
        MessageConsumer consumer = this.queueSession.createConsumer(tempQueue);
        consumer.setMessageListener(this);

        //Tạo thông điệp gửi
        TextMessage textMessage = this.topicSession.createTextMessage();
        textMessage.setJMSReplyTo(tempQueue);
        textMessage.setJMSCorrelationID(idActor);
        textMessage.setText(text);

        topicPublisher.send(topic, textMessage);
//        System.out.println("Send: " + textMessage.getText());
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage txtMsg = (TextMessage) message;
                String messageText = txtMsg.getText();
                if (!listIdOnline.contains(messageText)) {
                    listIdOnline.add(messageText);
                }
            }
        } catch (JMSException e) {

        }
    }

    private static class onReceiveMessageFromTopic implements MessageListener {

        private final QueueSession queueSession;
        private final MessageProducer replyProducer;
        private final String idReply;

        public onReceiveMessageFromTopic(String idReply, QueueSession queueSession, MessageProducer replyProducer) {
            this.idReply = idReply;
            this.queueSession = queueSession;
            this.replyProducer = replyProducer;
        }

        @Override
        public void onMessage(Message message) {
            try {
                TextMessage response = this.queueSession.createTextMessage();
                if (message instanceof TextMessage) {
                    TextMessage txtMsg = (TextMessage) message;
                    String messageText = txtMsg.getText();
                    response.setText(idReply);
                }

                response.setJMSCorrelationID(message.getJMSCorrelationID());
                this.replyProducer.send(message.getJMSReplyTo(), response);

            } catch (JMSException e) {

            }
        }
    }

    private static class onReceiveMessageFromQueue implements MessageListener {

        private final String idReceive;

        public onReceiveMessageFromQueue(String idReceive) {
            this.idReceive = idReceive;
        }

        @Override
        public void onMessage(Message message) {

        }
    }

    public String getIdActor() {
        return idActor;
    }

    public void setIdActor(String idActor) {
        this.idActor = idActor;
    }

    public List<String> getListIdOnline() {
        return listIdOnline;
    }

    public void setListIdOnline(List<String> listIdOnline) {
        this.listIdOnline = listIdOnline;
    }
}
