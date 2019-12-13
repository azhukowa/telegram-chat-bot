package service;

import model.Actions;
import model.StatesList;
import repository.StateRepository;
import state.State;


public class StateHandler {

    private final StateRepository stateRepository;

    public StateHandler(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public State getCurrentState(long chatId){

        State currentState;

        if(stateRepository.get(chatId) == null){
            stateRepository.put(chatId, StatesList.NEW.getStateName());
            currentState = StatesList.NEW.getStateInstance();
        }
        else {
            byte[] chatState = stateRepository.get(chatId);
            currentState = StatesList.instanceFromString(new String(chatState));
        }

        return currentState;

    }


    public State changeStateOnAction(State currentState, long chatId, String actionText ){
        State newState = currentState.changeState(Actions.valueFromString(actionText));
        stateRepository.put(chatId, newState.getStateName());
        return newState;
    }

}
