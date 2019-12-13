package state;

import model.Actions;

import java.util.ArrayList;


public interface State {

    String getStateName();

    String returnText();

    ArrayList<Actions> getActionsList();

    State changeState(Actions action);

}
