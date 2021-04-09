package com.dungeonmaster.dashboard;

public class SwitchScreenCommand extends Command {

    public enum ScreenType {
        DASHBOARD, WORLD_MAP, ZONE, CHARACTER_LIST, CHARACTER
    }

    private final ScreenType newScreenType;

    public SwitchScreenCommand(ScreenType newScreenType) {
        super(Action.SWITCH_SCREEN);
        this.newScreenType = newScreenType;
    }

    public SwitchScreenCommand(ScreenType newScreenType, Object payload) {
        super(Action.SWITCH_SCREEN, payload);
        this.newScreenType = newScreenType;
    }

    public ScreenType getNewScreenType() {
        return newScreenType;
    }

}
