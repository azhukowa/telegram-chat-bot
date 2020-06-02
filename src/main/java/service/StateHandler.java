package service;

<<<<<<< HEAD
import model.Action;
import repository.IStateRepository;
import service.api.TestApiHandler;
import service.api.WeatherHandler;
import state.IState;
import state.impl.NewState;
import state.impl.TestApiState;
import state.impl.WeatherApiState;

import java.lang.reflect.InvocationTargetException;
=======
import model.Actions;
import model.StatesList;
import repository.StateRepository;
import state.State;
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db


public class StateHandler {

<<<<<<< HEAD
    private final TestApiHandler testApiHandler;
    private final WeatherHandler weatherHandler;
    private final IStateRepository stateRepository;

    public StateHandler(TestApiHandler testApiHandler, WeatherHandler weatherHandler, IStateRepository stateRepository) {
        this.testApiHandler = testApiHandler;
        this.weatherHandler = weatherHandler;
        this.stateRepository = stateRepository;
    }

    private IState createNewChat(long chatId) {
        IState currentState;
        currentState = new NewState();
        stateRepository.put(chatId, currentState.getStateName());
        return currentState;
    }

    public IState getCurrentState(long chatId) {

        IState currentState;

        if (stateRepository.get(chatId) == null) {

            currentState = createNewChat(chatId);

        } else {

            String chatState = new String(stateRepository.get(chatId));

            //если найденный в БД этап работает с апи (e.g.: TestApiState, WeatherApiState) - его экземпляр создается явно вместе с нужным ему хэндлером
            //в др. случаях - экземпляр создается по найденному имени класса с пустым конструктором
            if (chatState.equals(TestApiState.class.getName())) {
                currentState = new TestApiState(testApiHandler);
            } else if (chatState.equals(WeatherApiState.class.getName())) {
                currentState = new WeatherApiState(weatherHandler);
            } else {
                try {
                    currentState = (IState) Class.forName(chatState).getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }

=======
    private final StateRepository stateRepository;

    public StateHandler(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public State getCurrentState(long chatId){

        State currentState;

        if(stateRepository.get(chatId) == null){
            stateRepository.put(chatId, StatesList.NEW.getStateName());
            currentState = StatesList.NEW.getStateInstance();
        }
        else {
            byte[] chatState = stateRepository.get(chatId);
            currentState = StatesList.instanceFromString(new String(chatState));
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
        }

        return currentState;

    }

<<<<<<< HEAD
    public IState changeState(IState currentState, long chatId, String actionText) {

        IState newState;

        if (currentState.getActionsList().contains(Action.valueFromString(actionText)))
            newState = currentState.handleStateByAction(Action.valueFromString(actionText), testApiHandler, weatherHandler);
        else
            newState = currentState.handleStateByText(actionText);

=======

    public State changeStateOnAction(State currentState, long chatId, String actionText ){
        State newState = currentState.changeState(Actions.valueFromString(actionText));
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
        stateRepository.put(chatId, newState.getStateName());
        return newState;
    }

}
