package service;

import javafx.util.Pair;
import model.Actions;
import model.StatesList;
import model.messages.OutgoingMessage;
import model.messages.IncomingMessage;
import repository.StateRepository;
import state.State;

import java.util.List;

public class MessageHandler {

    private final StateHandler stateHandler;

    private final StateRepository stateRepository;

    public MessageHandler(StateRepository stateRepository, StateHandler stateHandler) {
        this.stateRepository = stateRepository;
        this.stateHandler = stateHandler;
    }

    public OutgoingMessage getCurrentStateMessage(long chatId) {

        State currentState = stateHandler.getCurrentState(chatId);

        OutgoingMessage outgoingMessage = new OutgoingMessage(chatId);
        outgoingMessage.setReplyText(currentState.returnText());
        outgoingMessage.setAvailableActions(currentState.getActionsList());
        return outgoingMessage;
    }

    public OutgoingMessage processMessage(IncomingMessage incomingMessage) {

        Pair<String, List<Actions>> returnedContent = processAction(incomingMessage.getChatId(), incomingMessage.getSelectedAction());
        OutgoingMessage outgoingMessage = new OutgoingMessage(incomingMessage.getChatId(), incomingMessage.getMessageId());
        outgoingMessage.setReplyText(returnedContent.getKey());
        outgoingMessage.setAvailableActions(returnedContent.getValue());
        return outgoingMessage;

    }

    private Pair<String, List<Actions>> processAction(long chatId, String selectedAction) {

        String message;
        List<Actions> availableActions;
        State currentState = stateHandler.getCurrentState(chatId);
        Pair<String, List<Actions>> returnedContent;

        if (selectedAction.equals("/start")) {

            message = StatesList.NEW.getStateInstance().returnText();
            stateRepository.put(chatId, StatesList.NEW.getStateName());
            availableActions = (StatesList.NEW.getStateInstance().getActionsList());

        } else if (Actions.isActionInEnum(selectedAction)) {

            returnedContent = processKnownAction(chatId, selectedAction, currentState);
            message = returnedContent.getKey();
            availableActions = returnedContent.getValue();

        } else {

            message = "Action doesn't exist";
            availableActions = currentState.getActionsList();

        }

        returnedContent = new Pair<String, List<Actions>>(message, availableActions);
        return returnedContent;
    }

    private Pair<String, List<Actions>> processKnownAction(long chatId, String selectedAction, State currentState) {

        String message;

        if (!currentState.getActionsList().contains(Actions.valueFromString(selectedAction))) {

            message = "Action is forbidden right now";

        } else {

            //update user state
            currentState = stateHandler.changeStateOnAction(currentState, chatId, selectedAction);

            message = currentState.returnText();
        }

        Pair<String, List<Actions>> returnedContent = new Pair<String, List<Actions>>(message, currentState.getActionsList());
        return returnedContent;
    }

}
