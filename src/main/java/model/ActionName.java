package model;

import java.util.ArrayList;

public enum ActionName {

    SAY_HELLO("Start again"),
    WRITE_YES("Yes"),
    SAY_BYE("Bye"),
    GIVE_ANSWER("Fine");

    private String actionName;

    ActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

    //проверка введенной строки на наличие в enum
    public static boolean isActionInEnum(String value) {
        for (ActionName c : ActionName.values()) {
            if (c.getActionName().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    //поиск значения по строке
    public static ActionName valueFromString(String text) {
        for (ActionName action : ActionName.values()) {
            if (action.actionName.equalsIgnoreCase(text)) {
                return action;
            }
        }
        return null;
    }

}

