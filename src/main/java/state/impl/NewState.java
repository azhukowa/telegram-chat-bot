package state.impl;

import model.Actions;
import model.StatesList;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static model.Actions.SAY_BYE;
import static model.Actions.WRITE_YES;

public class NewState implements state.State {

    private final ArrayList<Actions> actionsList = new ArrayList<>(asList(WRITE_YES, SAY_BYE));

    @Override
    public String getStateName() {
        return getClass().getSimpleName();
    }

    @Override
    public String returnText() {
        return "Bot: Hello! Wanna write yes?";
    }

    @Override
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

    }

}
