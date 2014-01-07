/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.jms;

import javax.jms.JMSException;

/**
 *
 * @author Tuan
 */
public class ClientJMS extends BasicJMSActor {

    private final String userId;

    public ClientJMS(String userId) {
        this.userId = userId;
    }

    public void sendStatusOnline() throws JMSException {
        this.connectionToTopic();
        this.sendMessageToTopic(GlobalJMS.topicCheckOnline, "1_on_" + userId);
        this.closeTopicConnection();
    }

    public void sendStatusOffline() throws JMSException {
        this.connectionToTopic();
        this.sendMessageToTopic(GlobalJMS.topicCheckOnline, "1_of_" + userId);
        this.closeTopicConnection();
    }

    public void sendMessageChat(String receiveId, String message) throws JMSException {
        this.connectionToQueue();
        this.sendMessageToQueue(GlobalJMS.queueChat, "2_" + userId + "_" + receiveId + "_" + message);
        this.closeQueueConnection();
    }
}
