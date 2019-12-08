package model;

import java.util.List;

public class BotResponse {

    String replyText;
    List<ActionName> availableActions;

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public List<ActionName> getAvailableActions() {
        return availableActions;
    }

    public void setAvailableActions(List<ActionName> availableActions) {
        this.availableActions = availableActions;
    }
}
