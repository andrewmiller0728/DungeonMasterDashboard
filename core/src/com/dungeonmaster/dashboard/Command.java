package com.dungeonmaster.dashboard;

public class Command {

    public enum Action {
        SWITCH_SCREEN
    }

    private Action commandAction;
    private Object payload;

    private static int nextID = 1;
    private int id;

    public Command(Action action) {
        commandAction = action;
        id = nextID;
        nextID++;
        payload = null;
    }

    public Command(Action action, Object payload) {
        commandAction = action;
        id = nextID;
        nextID++;
        this.payload = payload;
    }

    public Action getCommandAction() {
        return commandAction;
    }

    public int getID() {
        return id;
    }

    public Object getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return String.format("Command #%d, %s", id, commandAction.toString());
    }
}
