package context;

import model.ActionName;
import state.State;
import state.impl.FinishedState;
import state.impl.InProgressState;
import state.impl.NewState;
import state.impl.WaitingState;

import java.util.ArrayList;

public class DialogContext {

    private State state;

    public DialogContext() {
        setState(new NewState());
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getStateOnString(String stateName) {
        switch (stateName) {
            case "NewState":
                return new NewState();
            case "WaitingState":
                return new WaitingState();
            case "InProgressState":
                return new InProgressState();
            case "FinishedState":
                return new FinishedState();
        }
        return null;
    }


    public String returnReplyText() {
        return state.returnText();
    }

    public ArrayList<ActionName> returnActionNames() {
        return state.getActionsList();
    }

    //меняет статус текущего объекта по переданному событию
    public void process(ActionName action) {
        state.changeDialogState(this, action);
    }

}
