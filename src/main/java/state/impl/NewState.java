package state.impl;

import model.Action;
import service.api.TestApiHandler;
import service.api.WeatherHandler;
import state.IState;


import java.util.ArrayList;

import static java.util.Arrays.asList;

import static model.Action.*;

public class NewState implements IState {

    private final ArrayList<Action> actionsList = new ArrayList<>(asList(WRITE_YES, SAY_BYE, TEST_API, WEATHER_API));

    @Override
    public String getStateName() {
        return getClass().getName();
    }

    @Override
    public String returnText() {
        return "Bot: Hello! Wanna write yes?";
    }

    @Override
    public ArrayList<Action> getActionsList() {
        return actionsList;
    }

    @Override
    public IState handleStateByAction(Action action, TestApiHandler testApiHandler, WeatherHandler weatherHandler) {

        switch (action) {
            case WRITE_YES:
                return new WaitingState();
            case SAY_BYE:
                return new FinishedState();
            case WEATHER_API:
                return new WeatherApiState(weatherHandler);
            case TEST_API:
                return new TestApiState(testApiHandler);
        }

        return this;

    }

    @Override
    public IState handleStateByText(String text) {
        return this;
    }

}
