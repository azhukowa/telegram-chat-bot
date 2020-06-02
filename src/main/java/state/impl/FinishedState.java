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
import static model.Action.SAY_HELLO;

public class FinishedState implements IState {

    private final ArrayList<Action> actionsList = new ArrayList<>(asList(SAY_HELLO));

    @Override
    public String getStateName() {
        return getClass().getName();
=======
import static model.Actions.SAY_HELLO;

public class FinishedState implements state.State {

    private ArrayList<Actions> actionsList = new ArrayList<>(asList(SAY_HELLO));

    @Override
    public String getStateName() {
        return getClass().getSimpleName();
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
    }


    @Override
    public String returnText() {
        return "Bot: Goodbye!";
    }

    @Override
<<<<<<< HEAD
    public ArrayList<Action> getActionsList() {
=======
    public ArrayList<Actions> getActionsList() {
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
        return actionsList;
    }


    @Override
<<<<<<< HEAD
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

=======
    public state.State changeState(Actions action) {

        switch (action) {
            case SAY_HELLO:
                return StatesList.NEW.getStateInstance();
        }

        return null;

    }

>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db

}
