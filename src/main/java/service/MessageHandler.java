package service;


import model.*;
import repository.IStateRepository;
import state.IState;
import state.impl.NewState;


import java.util.List;

public class MessageHandler {

    private final StateHandler stateHandler;


    private final IStateRepository stateRepository;


    public MessageHandler(IStateRepository stateRepository, StateHandler stateHandler) {

        this.stateRepository = stateRepository;
        this.stateHandler = stateHandler;
    }

    public OutgoingMessage getCurrentStateMessage(long chatId) {


        IState currentState = stateHandler.getCurrentState(chatId);


        OutgoingMessage outgoingMessage = new OutgoingMessage(chatId);
        outgoingMessage.setReplyText(currentState.returnText());
        outgoingMessage.setAvailableActions(currentState.getActionsList());
        return outgoingMessage;
    }


    public OutgoingMessage processMessage(IncomingMessage incomingMessage)  {

        ReturnedContent returnedContent = processAction(incomingMessage.getChatId(), incomingMessage.getSelectedAction());
        OutgoingMessage outgoingMessage = new OutgoingMessage(incomingMessage.getChatId(), incomingMessage.getMessageId());
        outgoingMessage.setReplyText(returnedContent.getReplyText());
        outgoingMessage.setAvailableActions(returnedContent.getActions());

        return outgoingMessage;

    }


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

            message = "Action doesn't exist";
            availableActions = currentState.getActionsList();

        }

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

    }

}