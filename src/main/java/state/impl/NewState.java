package state.impl;

import context.DialogContext;
import model.ActionName;
import state.State;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static model.ActionName.SAY_BYE;
import static model.ActionName.WRITE_YES;

public class NewState implements State {

    private final ArrayList<ActionName> actionsList = new ArrayList<>(asList(WRITE_YES, SAY_BYE));

    @Override
    public String getStateName() {
        return getClass().getSimpleName();
    }

    @Override
    public String returnText() {
        return "Bot: Hello! Wanna write yes?";
    }

    @Override
    public ArrayList<ActionName> getActionsList() {
        return actionsList;
    }


    @Override
    public void changeDialogState(DialogContext dialogContext, ActionName actionName) {

        switch (actionName) {
            case WRITE_YES:
                dialogContext.setState(new WaitingState());
                break;
            case SAY_BYE:
                dialogContext.setState(new FinishedState());
                break;
        }

    }

}
