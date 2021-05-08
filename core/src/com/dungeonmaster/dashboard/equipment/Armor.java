package com.dungeonmaster.dashboard.equipment;

public class Armor extends Item {

    public enum BodyPart {
        HEAD, LEFT_ARM, RIGHT_ARM, TORSO, RIGHT_LEG, LEFT_LEG, RIGHT_FOOT, LEFT_FOOT, SHIELD, OTHER
    }

    public enum Size {
        LIGHT, MEDIUM, HEAVY, SHIELD, OTHER
    }

    private BodyPart bodyPart;
    private Size size;
    private int ac;
    private int strengthReq;
    private boolean isStealthy;

    public Armor() {
        super();
        bodyPart = BodyPart.OTHER;
        size = Size.OTHER;
        ac = 0;
        strengthReq = 0;
        isStealthy = true;
    }

    public Armor(Item baseItem, int ac, boolean isStealthy, BodyPart bodyPart, Size size, int strengthReq) {
        super(baseItem.getName(), baseItem.getTradeValue(), baseItem.getWeight(), baseItem.isEquippable(), baseItem.isConsumable());
        this.bodyPart = bodyPart;
        this.size = size;
        this.ac = ac;
        this.strengthReq = strengthReq;
        this.isStealthy = isStealthy;
    }

    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public Size getSize() {
        return size;
    }

    public int getAC() {
        return ac;
    }

    public boolean isStealthy() {
        return isStealthy;
    }

    public int getStrengthReq() {
        return strengthReq;
    }

    @Override
    public String toString() {
        return "Armor{" +
                "bodyPart=" + bodyPart +
                ", size=" + size +
                ", ac=" + ac +
                ", strengthReq=" + strengthReq +
                ", isStealthy=" + isStealthy +
                '}';
    }
}
