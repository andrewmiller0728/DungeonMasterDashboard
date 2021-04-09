package com.dungeonmaster.dashboard;

public class SwitchScreenCommand extends Command {

    public enum ScreenType {
        DASHBOARD, WORLD_MAP, ZONE, CHARACTER_LIST, CHARACTER
    }

    private final ScreenType newScreenType;
    private final Object payload;

    public SwitchScreenCommand(ScreenType newScreenType) {
        super(Action.SWITCH_SCREEN);
        this.newScreenType = newScreenType;
        payload = null;
    }

    public SwitchScreenCommand(ScreenType newScreenType, Object payload) {
        super(Action.SWITCH_SCREEN);
        this.newScreenType = newScreenType;
        this.payload = payload;
    }

    public ScreenType getNewScreenType() {
        return newScreenType;
    }

    public Object getPayload() {
        return payload;
    }

}
