package service;

import context.DialogContext;
import model.ActionName;
import model.BotResponse;
import repository.StateRepository;
import state.impl.NewState;

import java.io.UnsupportedEncodingException;

public class MessageHandler {

    private final DialogContext dialogContext;

    public StateHandler getStateHandler() {
        return stateHandler;
    }

    private final StateHandler stateHandler;
    private final StateRepository stateRepository;
    private final BotResponse botResponse;


    public DialogContext getDialogContext() {
        return dialogContext;
    }

    public MessageHandler(DialogContext dialogContext, StateRepository stateRepository, StateHandler stateHandler, BotResponse botResponse) {
        this.dialogContext = dialogContext;
        this.stateRepository = stateRepository;
        this.stateHandler = stateHandler;
        this.botResponse = botResponse;
    }

    public BotResponse process(long chatID, String actionName) throws UnsupportedEncodingException {

        final byte[] chatIdBytes = String.valueOf(String.valueOf(chatID)).getBytes("UTF-8");

        if (actionName.equals("/start")) {
            dialogContext.setState(new NewState());
            stateRepository.put(chatIdBytes, dialogContext.getState().getStateName().getBytes("UTF-8"));
        }

        stateHandler.switchContextState(chatID);

        if (ActionName.isActionInEnum(actionName)) {

            if (!dialogContext.getState().getActionsList().contains(ActionName.valueFromString(actionName))) {
                botResponse.setReplyText("Action is forbidden right now" + "\n" + dialogContext.returnReplyText());
            } else {
                //update user state
                dialogContext.process(ActionName.valueFromString(actionName));
                stateRepository.put(chatIdBytes, dialogContext.getState().getStateName().getBytes("UTF-8"));
                botResponse.setReplyText(dialogContext.returnReplyText());
            }

        } else if (actionName.equals("/start")) {
            botResponse.setReplyText(dialogContext.returnReplyText());
        } else
            botResponse.setReplyText("Action doesn't exist" + "\n" + dialogContext.returnReplyText());

        botResponse.setAvailableActions(dialogContext.returnActionNames());
        return botResponse;

    }

}
