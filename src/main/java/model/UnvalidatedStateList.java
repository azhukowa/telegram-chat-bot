package model;

import state.impl.WeatherApiState;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class UnvalidatedStateList {

    //contains a list of states for which checks for incoming actions are not needed
    private static final ArrayList<String> stateList = new ArrayList<>(asList(
            WeatherApiState.class.getName()
    ));

    public static ArrayList<String> getStateList() {
        return stateList;
    }

}
