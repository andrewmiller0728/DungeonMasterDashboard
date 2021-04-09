package com.dungeonmaster.dashboard;

public class CommandListTest {

    public static void main(String[] args) {
        CommandList commandList = new CommandList();
        ClassA classA = new ClassA();
        ClassB classB = new ClassB();

        System.out.println("----- START -----");

        commandList.addCommand(new SwitchScreenCommand(SwitchScreenCommand.ScreenType.ZONE));
        commandList.addCommand(new SwitchScreenCommand(SwitchScreenCommand.ScreenType.CHARACTER_LIST));
        commandList.addCommand(new SwitchScreenCommand(SwitchScreenCommand.ScreenType.CHARACTER));
        classA.addCommand(new SwitchScreenCommand(SwitchScreenCommand.ScreenType.ZONE));
        classA.addCommand(new SwitchScreenCommand(SwitchScreenCommand.ScreenType.CHARACTER_LIST));
        classA.addCommand(new SwitchScreenCommand(SwitchScreenCommand.ScreenType.CHARACTER));
        classB.addCommand(new SwitchScreenCommand(SwitchScreenCommand.ScreenType.ZONE));
        classB.addCommand(new SwitchScreenCommand(SwitchScreenCommand.ScreenType.CHARACTER_LIST));
        classB.addCommand(new SwitchScreenCommand(SwitchScreenCommand.ScreenType.CHARACTER));

        System.out.println("----- PEEKING -----");
        for (int i = 0; i < commandList.getSize(); i++) {
            System.out.println(commandList.peekNextCommand(i));
        }

        System.out.println("----- GETTING -----");
        for (int i = 0; i < commandList.getSize(); i++) {
            System.out.println(commandList.getNextCommand());
        }
        System.out.println(commandList.getNextCommand());

        System.out.println("----- UNDO GETTING -----");
        commandList.undo();
        System.out.println(commandList.peekNextCommand());
        commandList.undo();
        System.out.println(commandList.peekNextCommand());
        commandList.undo();
        System.out.println(commandList.peekNextCommand());

        System.out.println("----- PEEKING -----");
        for (int i = 0; i < commandList.getSize(); i++) {
            System.out.println(commandList.peekNextCommand(i));
        }

        System.out.println("----- END -----");
    }

    private static class ClassA {
        private final CommandList commandListA;

        public ClassA() {
            commandListA = new CommandList();
        }

        public void addCommand(Command command) {
            commandListA.addCommand(command);
        }
    }

    private static class ClassB {
        private final CommandList commandListB;

        public ClassB() {
            commandListB = new CommandList();
        }

        public void addCommand(Command command) {
            commandListB.addCommand(command);
        }
    }

}
