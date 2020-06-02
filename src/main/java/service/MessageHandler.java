package service;

<<<<<<< HEAD
import model.*;
import repository.IStateRepository;
import state.IState;
import state.impl.NewState;
=======
import javafx.util.Pair;
import model.Actions;
import model.StatesList;
import model.messages.OutgoingMessage;
import model.messages.IncomingMessage;
import repository.StateRepository;
import state.State;
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db

import java.util.List;

public class MessageHandler {

    private final StateHandler stateHandler;

<<<<<<< HEAD
    private final IStateRepository stateRepository;


    public MessageHandler(IStateRepository stateRepository, StateHandler stateHandler) {
=======
    private final StateRepository stateRepository;

    public MessageHandler(StateRepository stateRepository, StateHandler stateHandler) {
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
        this.stateRepository = stateRepository;
        this.stateHandler = stateHandler;
    }

    public OutgoingMessage getCurrentStateMessage(long chatId) {

<<<<<<< HEAD
        IState currentState = stateHandler.getCurrentState(chatId);
=======
        State currentState = stateHandler.getCurrentState(chatId);
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db

        OutgoingMessage outgoingMessage = new OutgoingMessage(chatId);
        outgoingMessage.setReplyText(currentState.returnText());
        outgoingMessage.setAvailableActions(currentState.getActionsList());
        return outgoingMessage;
    }

<<<<<<< HEAD
    public OutgoingMessage processMessage(IncomingMessage incomingMessage)  {

        ReturnedContent returnedContent = processAction(incomingMessage.getChatId(), incomingMessage.getSelectedAction());
        OutgoingMessage outgoingMessage = new OutgoingMessage(incomingMessage.getChatId(), incomingMessage.getMessageId());
        outgoingMessage.setReplyText(returnedContent.getReplyText());
        outgoingMessage.setAvailableActions(returnedContent.getActions());
=======
    public OutgoingMessage processMessage(IncomingMessage incomingMessage) {

        Pair<String, List<Actions>> returnedContent = processAction(incomingMessage.getChatId(), incomingMessage.getSelectedAction());
        OutgoingMessage outgoingMessage = new OutgoingMessage(incomingMessage.getChatId(), incomingMessage.getMessageId());
        outgoingMessage.setReplyText(returnedContent.getKey());
        outgoingMessage.setAvailableActions(returnedContent.getValue());
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
        return outgoingMessage;

    }

<<<<<<< HEAD
    private ReturnedContent processAction(long chatId, String selectedAction)  {

        String message;
        List<Action> availableActions;
        IState currentState = stateHandler.getCurrentState(chatId);
        ReturnedContent returnedContent = new ReturnedContent();

        if (selectedAction.equals("/start")) {

            IState newState = new NewState();
            message = newState.returnText();
            stateRepository.put(chatId, newState.getStateName());
            availableActions = (newState.getActionsList());

        } else if (Action.isActionInEnum(selectedAction) | UnvalidatedStateList.getStateList().contains(currentState.getStateName())) {
            returnedContent = processKnownAction(chatId, selectedAction, currentState);
            message = returnedContent.getReplyText();
            availableActions = returnedContent.getActions();

        } else {
=======
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

>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
            message = "Action doesn't exist";
            availableActions = currentState.getActionsList();

        }

<<<<<<< HEAD
        returnedContent.setReplyText(message);
        returnedContent.setActions(availableActions);

        return returnedContent;
    }

    private ReturnedContent processKnownAction(long chatId, String selectedAction, IState currentState) {

        String message;

        if (!currentState.getActionsList().contains(Action.valueFromString(selectedAction)) & !UnvalidatedStateList.getStateList().contains(currentState.getStateName())) {
            message = "Action is forbidden right now";
        } else {
            //update user state
            currentState = stateHandler.changeState(currentState, chatId, selectedAction);
            message = currentState.returnText();
        }

        return new ReturnedContent(message, currentState.getActionsList());
=======
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
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
    }

}
