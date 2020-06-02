package state;

import model.Action;
import service.api.DadataHandler;
import service.api.TestApiHandler;
import service.api.WeatherHandler;

import java.util.ArrayList;


public interface IState {

    String getStateName();

    String returnText();

    ArrayList<Action> getActionsList();

    IState handleStateByAction(Action action, TestApiHandler testApiHandler, WeatherHandler weatherHandler);

    IState handleStateByText(String text);

}
