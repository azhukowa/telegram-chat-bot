package state;

import context.DialogContext;
import model.ActionName;

import java.util.ArrayList;


public interface State {

    String getStateName();

    String returnText();

    ArrayList<ActionName> getActionsList();

    void changeDialogState(DialogContext dialogContext, ActionName actionName);

}
