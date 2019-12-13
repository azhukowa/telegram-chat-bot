package state.impl;

import model.Actions;
import model.StatesList;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static model.Actions.SAY_BYE;
import static model.Actions.SAY_HELLO;

public class InProgressState implements state.State {

    private ArrayList<Actions> actionsList = new ArrayList<>(asList(SAY_HELLO, SAY_BYE));

    @Override
    public String getStateName() {
        return getClass().getSimpleName();
    }


    @Override
    public String returnText() {
        return "Bot: Okay";
    }

    @Override
    public ArrayList<Actions> getActionsList() {
        return actionsList;
    }


    @Override
    public state.State changeState(Actions action) {

        switch (action) {
            case SAY_HELLO:
                return StatesList.NEW.getStateInstance();
            case SAY_BYE:
                return StatesList.FINISHED.getStateInstance();
        }

        return null;

    }

}
