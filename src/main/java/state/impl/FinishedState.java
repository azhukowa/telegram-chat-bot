package state.impl;


import model.Action;
import service.api.TestApiHandler;
import service.api.WeatherHandler;
import state.IState;

import java.util.ArrayList;

import static java.util.Arrays.asList;

import static model.Action.SAY_HELLO;

public class FinishedState implements IState {

    private final ArrayList<Action> actionsList = new ArrayList<>(asList(SAY_HELLO));

    @Override
    public String getStateName() {
        return getClass().getName();
    }


    @Override
    public String returnText() {
        return "Bot: Goodbye!";
    }

    @Override
    public ArrayList<Action> getActionsList() {
        return actionsList;
    }


    @Override
    public IState handleStateByAction(Action action, TestApiHandler testApiHandler, WeatherHandler weatherHandler) {

        switch (action) {
            case SAY_HELLO:
                return new NewState();
        }

        return this;

    }

    @Override
    public IState handleStateByText(String text) {
        return this;
    }


}
