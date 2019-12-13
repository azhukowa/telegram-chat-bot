package model.messages;

public class IncomingMessage {

    private long chatId;
    private String selectedAction;
    private int messageId;

    public IncomingMessage(long chatId, String selectedAction, int messageId) {
        this.chatId = chatId;
        this.selectedAction = selectedAction;
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

    public String getSelectedAction() {
        return selectedAction;
    }

    public void setSelectedAction(String selectedAction) {
        this.selectedAction = selectedAction;
    }
}
