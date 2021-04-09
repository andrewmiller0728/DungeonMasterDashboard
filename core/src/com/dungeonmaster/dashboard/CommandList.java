package com.dungeonmaster.dashboard;

import java.util.ArrayList;

public class CommandList {

    private static final int MAX_BUFFER = 16;

    private static ArrayList<Command> commands = new ArrayList<>();
    private static int currIndex = 0;

    public CommandList() {
        // This space has been intentionally left blank.
    }

    public int getSize() {
        return commands.size();
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public Command getNextCommand() {
        if (currIndex < commands.size()) {
            Command out = commands.get(currIndex);
            currIndex++;
            if (currIndex > MAX_BUFFER - 1) {
                commands.remove(0);
                currIndex = MAX_BUFFER - 1;
            }
            return out;
        }
        else {
            return null;
        }
    }

    public Command peekNextCommand() {
        if (currIndex < commands.size()) {
            return commands.get(currIndex);
        }
        else {
            return null;
        }
    }

    public Command peekNextCommand(int index) {
        if (index < commands.size()) {
            return commands.get(index);
        }
        else {
            return null;
        }
    }

    public void undo() {
        if (currIndex > 1) {
            currIndex -= 2;
            while (commands.size() - 1 > currIndex) {
                commands.remove(commands.size() - 1);
            }
        }
    }

    public void printList() {
        System.out.println("Command List:");
        for (Command command : commands) {
            System.out.printf("    %s\n", command.toString());
        }
    }

}
