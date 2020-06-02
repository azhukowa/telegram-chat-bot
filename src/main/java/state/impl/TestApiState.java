package state.impl;

import model.Action;
import service.api.TestApiHandler;
import service.api.WeatherHandler;
import state.IState;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static model.Action.GO_BACK;


public class TestApiState implements IState {

    TestApiHandler testApiHandler;

    public TestApiState(TestApiHandler testApiHandler) {
        this.testApiHandler = testApiHandler;
    }

    private final ArrayList<Action> actionsList = new ArrayList<>(asList(GO_BACK));

    @Override
    public String getStateName() {
        return getClass().getName();
    }

    @Override
    public String returnText() {
        return makeRequest();
    }


    private String makeRequest() {
        return testApiHandler.sendRequest();
    }

    @Override
    public ArrayList<Action> getActionsList() {
        return actionsList;
    }

    @Override
    public IState handleStateByAction(Action action, TestApiHandler testApiHandler, WeatherHandler weatherHandler) {

        switch (action) {
            case GO_BACK:
                return new NewState();
        }

        return this;

    }

    @Override
    public IState handleStateByText(String text) {
        return this;
    }

}
