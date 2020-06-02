package model;

public class IncomingMessage {

    private final long chatId;
    private final String selectedAction;
    private final int messageId;

    public IncomingMessage(long chatId, String selectedAction, int messageId) {
        this.chatId = chatId;
        this.selectedAction = selectedAction;
        this.messageId = messageId;
    }

    public int getMessageId() {
        return messageId;
    }
    public long getChatId() {
        return chatId;
    }
    public String getSelectedAction() {
        return selectedAction;
    }


}
