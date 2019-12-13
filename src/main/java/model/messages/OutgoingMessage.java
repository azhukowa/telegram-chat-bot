package model.messages;

import model.Actions;

import java.util.List;

public class OutgoingMessage {

    private long chatId;
    private String replyText;
    private List<Actions> availableActions;
    private int messageId;

    public OutgoingMessage(long chatId) {
        this.chatId = chatId;
    }

    public OutgoingMessage(long chatId, int messageId) {
        this.chatId = chatId;
        this.messageId = messageId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }


    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public List<Actions> getAvailableActions() {
        return availableActions;
    }

    public void setAvailableActions(List<Actions> availableActions) {
        this.availableActions = availableActions;
    }
}
