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

public class WaitingState implements IState {

    private final ArrayList<Action> actionsList = new ArrayList<>(asList(GIVE_ANSWER, SAY_BYE, SAY_HELLO));
=======
import static model.Actions.*;

public class WaitingState implements state.State {

    private ArrayList<Actions> actionsList = new ArrayList<>(asList(GIVE_ANSWER, SAY_BYE, SAY_HELLO));
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db

    @Override
    public String returnText() {
        return "Bot: How are you?";
    }

    @Override
    public String getStateName() {
<<<<<<< HEAD
        return getClass().getName();
    }

    @Override
    public ArrayList<Action> getActionsList() {
=======
        return getClass().getSimpleName();
    }

    @Override
    public ArrayList<Actions> getActionsList() {
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
        return actionsList;
    }


    @Override
<<<<<<< HEAD
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
=======
    public state.State changeState(Actions action) {

        switch (action) {
            case GIVE_ANSWER:
                return StatesList.IN_PROGRESS.getStateInstance();
            case SAY_HELLO:
                return StatesList.NEW.getStateInstance();
            case SAY_BYE:
                return StatesList.FINISHED.getStateInstance();
        }

        return null;

    }
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
}
