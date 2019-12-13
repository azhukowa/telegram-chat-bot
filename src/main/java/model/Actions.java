package model;

public enum Actions {

    SAY_HELLO("Start again"),
    WRITE_YES("Yes"),
    SAY_BYE("Bye"),
    GIVE_ANSWER("Fine");

    private String actionName;

    Actions(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

    //проверка введенной строки на наличие в enum
    public static boolean isActionInEnum(String value) {
        for (Actions c : Actions.values()) {
            if (c.getActionName().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    //поиск значения по строке
    public static Actions valueFromString(String text) {
        for (Actions action : Actions.values()) {
            if (action.actionName.equalsIgnoreCase(text)) {
                return action;
            }
        }
        return null;
    }

}

