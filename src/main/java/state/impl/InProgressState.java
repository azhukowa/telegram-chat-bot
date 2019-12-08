package state.impl;

import context.DialogContext;
import model.ActionName;
import state.State;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static model.ActionName.SAY_BYE;
import static model.ActionName.SAY_HELLO;

public class InProgressState implements State {

    private ArrayList<ActionName> actionsList = new ArrayList<>(asList(SAY_HELLO, SAY_BYE));

    @Override
    public String getStateName() {
        return getClass().getSimpleName();
    }


    @Override
    public String returnText() {
        return "Bot: Okay";
    }

    @Override
    public ArrayList<ActionName> getActionsList() {
        return actionsList;
    }


    @Override
    public void changeDialogState(DialogContext dialogContext, ActionName actionName) {

        switch (actionName) {
            case SAY_HELLO:
                dialogContext.setState(new NewState());
                break;
            case SAY_BYE:
                dialogContext.setState(new FinishedState());
                break;
        }

    }

}
