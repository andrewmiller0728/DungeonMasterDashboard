package com.dungeonmaster.dashboard;

public class Command {

    public enum Action {
        SWITCH_SCREEN
    }

    private Action commandAction;

    private static int nextID = 1;
    private int id;

    public Command(Action action) {
        commandAction = action;
        id = nextID;
        nextID++;
    }

    public Action getCommandAction() {
        return commandAction;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Command #%d, %s", id, commandAction.toString());
    }
}
