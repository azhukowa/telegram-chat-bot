package model;

public enum Action {

    SAY_HELLO("Start again"),
    WRITE_YES("Yes"),
    SAY_BYE("Bye"),
    GIVE_ANSWER("Fine"),
    TEST_API("test api"),
    WEATHER_API("weather api"),
    GO_BACK("go back");


    private final String name;

    Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //проверка переданной строки на наличие в enum
    public static boolean isActionInEnum(String actionText) {
        for (Action action : Action.values()) {
            if (action.getName().equalsIgnoreCase(actionText)) {
                return true;
            }
        }
        return false;
    }

    //поиск объекта по name
    public static Action valueFromString(String actionText) {
        for (Action action : Action.values()) {
            if (action.name.equalsIgnoreCase(actionText)) {
                return action;
            }
        }
        return null;
    }

}
