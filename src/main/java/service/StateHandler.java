package service;

import context.DialogContext;
import repository.StateRepository;

import java.io.UnsupportedEncodingException;

public class StateHandler {

    private DialogContext dialogContext;
    private final StateRepository stateRepository;

    public StateHandler(DialogContext dialogContext, StateRepository stateRepository) {
        this.dialogContext = dialogContext;
        this.stateRepository = stateRepository;
    }

    public void switchContextState(long chatId) throws UnsupportedEncodingException {

        //new user
        if (stateRepository.get(String.valueOf(chatId).getBytes("UTF-8")) == null) {
            stateRepository.put(String.valueOf(chatId).getBytes("UTF-8"), dialogContext.getState().getStateName().getBytes("UTF-8"));
        }

        //get status from db
        byte[] chatDbState = stateRepository.get(String.valueOf(chatId).getBytes("UTF-8"));

        //if state from db != dialogContext state --> change dialogContext state
        if (!(new String(chatDbState)).equals(dialogContext.getState().getStateName())) {
            dialogContext.setState(dialogContext.getStateOnString(new String(chatDbState)));
        }
    }


}
