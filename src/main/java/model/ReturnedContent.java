package model;

import java.util.List;

public class ReturnedContent {


    private String replyText;
    private List<Action> actions;

    public ReturnedContent(){ }

    public ReturnedContent(String replyText, List<Action> actions) {
        this.replyText = replyText;
        this.actions = actions;
    }


    public String getReplyText() {
        return replyText;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }




}
