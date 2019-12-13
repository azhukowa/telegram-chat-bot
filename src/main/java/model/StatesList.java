package model;

import state.impl.FinishedState;
import state.impl.InProgressState;
import state.impl.NewState;
import state.impl.WaitingState;

public enum StatesList {

    NEW (new NewState(), "NewState"),
    WAITING (new WaitingState(), "WaitingState"),
    IN_PROGRESS (new InProgressState(), "InProgressState"),
    FINISHED  (new FinishedState(), "FinishedState");

    private state.State stateInstance;

    private String stateName;

    StatesList(state.State stateInstance, String stateName) {
        this.stateInstance = stateInstance;
        this.stateName = stateName;
    }

    StatesList(state.State stateInstance) {
        this.stateInstance = stateInstance;
    }

    public state.State getStateInstance() {
        return stateInstance;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public static state.State instanceFromString(String stateName) {
        for (StatesList statesList : StatesList.values()) {
            if (statesList.stateName.equalsIgnoreCase(stateName)) {
                return statesList.stateInstance;
            }
        }
        return null;
    }


}
