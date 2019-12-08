package state.impl;

import context.DialogContext;
import model.ActionName;
import state.State;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static model.ActionName.*;

public class WaitingState implements State {

    private ArrayList<ActionName> actionsList = new ArrayList<>(asList(GIVE_ANSWER, SAY_BYE, SAY_HELLO));

    @Override
    public String returnText() {
        return "Bot: How are you?";
    }

    @Override
    public String getStateName() {
        return getClass().getSimpleName();
    }

    @Override
    public ArrayList<ActionName> getActionsList() {
        return actionsList;
    }


    @Override
    public void changeDialogState(DialogContext dialogContext, ActionName actionName) {

        switch (actionName) {
            case GIVE_ANSWER:
                dialogContext.setState(new InProgressState());
                break;
            case SAY_BYE:
                dialogContext.setState(new FinishedState());
                break;
            case SAY_HELLO:
                dialogContext.setState(new NewState());
                break;
        }

    }

}
