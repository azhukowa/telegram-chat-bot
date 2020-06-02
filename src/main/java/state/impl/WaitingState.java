package state.impl;

import model.Action;
import service.api.TestApiHandler;
import service.api.WeatherHandler;
import state.IState;

import java.util.ArrayList;

import static java.util.Arrays.asList;

import static model.Action.*;

public class WaitingState implements IState {

    private final ArrayList<Action> actionsList = new ArrayList<>(asList(GIVE_ANSWER, SAY_BYE, SAY_HELLO));

    @Override
    public String returnText() {
        return "Bot: How are you?";
    }

    @Override
    public String getStateName() {

        return getClass().getName();
    }

    @Override
    public ArrayList<Action> getActionsList() {
        return actionsList;
    }


    @Override
    public IState handleStateByAction(Action action, TestApiHandler testApiHandler, WeatherHandler weatherHandler) {

        switch (action) {
            case GIVE_ANSWER:
                return new InProgressState();
            case SAY_HELLO:
                return new NewState();
            case SAY_BYE:
                return new FinishedState();
        }

        return this;

    }

    @Override
    public IState handleStateByText(String text) {
        return this;
    }

}
