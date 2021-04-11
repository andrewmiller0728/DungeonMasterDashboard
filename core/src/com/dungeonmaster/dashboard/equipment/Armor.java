package com.dungeonmaster.dashboard.equipment;

public class Armor extends Item {

    public enum Location {
        BODY, HEAD, LEFT_ARM, RIGHT_ARM, TORSO, RIGHT_LEG, LEFT_LEG, RIGHT_FOOT, LEFT_FOOT, SHIELD, OTHER
    }

    public enum Size {
        LIGHT, MEDIUM, HEAVY, SHIELD, OTHER
    }

    private Location location;
    private Size size;
    private int ac;
    private boolean isStealthy;

    public Armor() {
        super();
        location = Location.OTHER;
        size = Size.OTHER;
        ac = 0;
        isStealthy = true;
    }

    public Armor(Item baseItem, int ac) {
        super(baseItem.getName(), baseItem.getTradeValue(), baseItem.getWeight(), baseItem.canEquip(), baseItem.isConsumable());
        location = Location.OTHER;
        size = Size.OTHER;
        this.ac = ac;
        isStealthy = true;
    }

    public Armor(Item baseItem, int ac, boolean isStealthy) {
        super(baseItem.getName(), baseItem.getTradeValue(), baseItem.getWeight(), baseItem.canEquip(), baseItem.isConsumable());
        location = Location.OTHER;
        size = Size.OTHER;
        this.ac = ac;
        this.isStealthy = isStealthy;
    }

    public Armor(Item baseItem, int ac, boolean isStealthy, Location location, Size size) {
        super(baseItem.getName(), baseItem.getTradeValue(), baseItem.getWeight(), baseItem.canEquip(), baseItem.isConsumable());
        this.location = location;
        this.size = size;
        this.ac = ac;
        this.isStealthy = isStealthy;
    }

    public Location getLocation() {
        return location;
    }

    public Size getSize() {
        return size;
    }

    public int getAc() {
        return ac;
    }

    public boolean isStealthy() {
        return isStealthy;
    }

}
