package state.impl;

import model.Actions;
import model.StatesList;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static model.Actions.*;

public class WaitingState implements state.State {

    private ArrayList<Actions> actionsList = new ArrayList<>(asList(GIVE_ANSWER, SAY_BYE, SAY_HELLO));

    @Override
    public String returnText() {
        return "Bot: How are you?";
    }

    @Override
    public String getStateName() {
        return getClass().getSimpleName();
    }

    @Override
    public ArrayList<Actions> getActionsList() {
        return actionsList;
    }


    @Override
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
}
