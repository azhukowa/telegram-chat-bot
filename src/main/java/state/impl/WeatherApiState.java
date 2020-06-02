package state.impl;

import model.Action;
import service.api.TestApiHandler;
import service.api.WeatherHandler;
import state.IState;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;
import static model.Action.GO_BACK;

public class WeatherApiState implements IState {

    WeatherHandler weatherHandler;

    public WeatherApiState(WeatherHandler weatherHandler) {
        this.weatherHandler = weatherHandler;
    }

    private final ArrayList<Action> actionsList = new ArrayList<>(asList(GO_BACK));
    private String returnText = ">>> ВВЕДИТЕ АДРЕС ";

    @Override
    public String getStateName() {
        return getClass().getName();
    }


    @Override
    public String returnText() {
        return returnText;
    }


    @Override
    public ArrayList<Action> getActionsList() {
        return actionsList;
    }

    @Override
    public IState handleStateByAction(Action action, TestApiHandler testApiHandler, WeatherHandler weatherHandler) {

        switch (action) {
            case GO_BACK:
                return new NewState();
        }

        return this;

    }

    @Override
    public IState handleStateByText(String address) {

        if (checkWithRegExp(address)) {
            this.returnText = makeRequest(address);
        } else {
            this.returnText = "Error: input string contains illegal characters or invalid length";
        }

        return this;
    }


    private String makeRequest(String address) {
        return weatherHandler.processAddress(address);
    }


    private static boolean checkWithRegExp(String address) {
        //Строка [2-250] символов. Допустимы: кириллица, латиница, цифры, пробелы и символы  - , . / " ( ) №  #
        Pattern p = Pattern.compile("^[А-яЁё\\w\\s\\d\\-,(./\")№#]{2,250}$");
        Matcher m = p.matcher(address);
        return m.matches();
    }

}
