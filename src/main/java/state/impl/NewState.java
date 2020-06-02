package state.impl;

<<<<<<< HEAD
import model.Action;
import service.api.TestApiHandler;
import service.api.WeatherHandler;
import state.IState;
=======
import model.Actions;
import model.StatesList;
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db

import java.util.ArrayList;

import static java.util.Arrays.asList;
<<<<<<< HEAD
import static model.Action.*;

public class NewState implements IState {

    private final ArrayList<Action> actionsList = new ArrayList<>(asList(WRITE_YES, SAY_BYE, TEST_API, WEATHER_API));

    @Override
    public String getStateName() {
        return getClass().getName();
=======
import static model.Actions.SAY_BYE;
import static model.Actions.WRITE_YES;

public class NewState implements state.State {

    private final ArrayList<Actions> actionsList = new ArrayList<>(asList(WRITE_YES, SAY_BYE));

    @Override
    public String getStateName() {
        return getClass().getSimpleName();
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
    }

    @Override
    public String returnText() {
        return "Bot: Hello! Wanna write yes?";
    }

    @Override
<<<<<<< HEAD
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
=======
    public ArrayList<Actions> getActionsList() {
        return actionsList;
    }


    @Override
    public state.State changeState(Actions action) {

        switch (action) {
            case WRITE_YES:
                return StatesList.WAITING.getStateInstance();
            case SAY_BYE:
                return StatesList.FINISHED.getStateInstance();
        }

        return null;

>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
    }

}
