/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhocvu.api.mongodb.jms;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Tuan
 */
public final class ServerJMS extends BasicJMSActor implements MessageListener {

    private List<String> listUserId;
    private boolean receiveStatus;

    private Map<String, List<String>> mapMessage;

    public ServerJMS() {
        listUserId = new ArrayList<String>();
        mapMessage = new HashMap<String, List<String>>();
        this.setupConnection();
    }

    private void setupConnection() {
        this.connectionBroker();
        this.connectionToTopic();
        this.connectionToQueue();
        this.receiveMessageFromTopic(GlobalJMS.topicCheckOnline, this);
        this.receiveMessageFromQueue(GlobalJMS.queueChat, this);
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage txtMsg = (TextMessage) message;
                String messageText = txtMsg.getText();
                String type = messageText.substring(0, 1);
                if (type.equals("1")) {
                    //Nhận được thông điệp trạng thái người dùng.
                    String status = messageText.substring(2, 4);
                    String userId = messageText.substring(5);

                    if (status.equals("on")) {
                        addIdOnline(userId);

                    } else if (status.equals("of")) {
                        removeIdExit(userId);

                    }

                } else if (type.equals("2")) {
                    //Nhận tin nhắn
                    String sendId = messageText.substring(2, 26);
                    String receiveId = messageText.substring(27, 51);
                    String text = messageText.substring(52);
                    addNewMessage(sendId, receiveId, text);
                }
            }
        } catch (JMSException e) {

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ServerJMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addIdOnline(String userId) {
        if (!listUserId.contains(userId)) {
            listUserId.add(userId);
        }
    }

    private void removeIdExit(String userId) {
        if (listUserId.contains(userId)) {
            listUserId.remove(userId);
        }
    }

    private void addNewMessage(String sendId, String receiveId, String text) throws UnsupportedEncodingException {
        byte ptext[] = text.getBytes("ISO-8859-1");
        String textMsg = new String(ptext, "UTF-8");
        String key = sendId + "_" + receiveId;
        if (mapMessage.containsKey(key)) {
            List<String> listMessage = mapMessage.get(key);
            listMessage.add(textMsg);
            mapMessage.remove(key);
            mapMessage.put(key, listMessage);
        } else {
            List<String> listMessage = new ArrayList();
            listMessage.add(textMsg);
            mapMessage.put(key, listMessage);
        }

    }

    public List<String> getMessageBy(String sendId, String receiveId) {
        String key = sendId + "_" + receiveId;
        List<String> listMessage = mapMessage.get(key);
        mapMessage.remove(key);
        return listMessage;
    }

    public boolean isReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(boolean receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public List<String> getListUserId() {
        return listUserId;
    }
}
