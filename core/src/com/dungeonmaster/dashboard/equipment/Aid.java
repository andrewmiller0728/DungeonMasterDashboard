package com.dungeonmaster.dashboard.equipment;

public class Aid extends Item {

    private float quality; // A multiplier available so some aid can be better than others
    private int uses; // Number of times the aid item can be used before it is consumed

    public Aid() {
        super();
        quality = 1.0f;
        uses = 1;
    }

    public Aid(Item baseItem, int uses) {
        super(
                baseItem.getName(),
                baseItem.getTradeValue(),
                baseItem.getWeight(),
                baseItem.isEquippable(),
                baseItem.isConsumable()
        );
        quality = 1.0f;
        this.uses = uses;
    }

    public Aid(Item baseItem, int uses, float quality) {
        super(
                baseItem.getName(),
                baseItem.getTradeValue(),
                baseItem.getWeight(),
                baseItem.isEquippable(),
                baseItem.isConsumable()
        );
        this.quality = quality;
        this.uses = uses;
    }

    public void use() {
        if (uses > 0) {
            uses--;
        }
        else {
            throw new RuntimeException("No Aid uses remaining");
        }
    }

    public void use(int count) {
        if (uses - count >= 0) {
            uses -= count;
        }
        else {
            throw new RuntimeException("Not enough Aid uses remaining");
        }
    }

    public boolean isEmpty() {
        return uses == 0;
    }

    public float getQuality() {
        return quality;
    }

    public int getRemainingUses() {
        return uses;
    }

}
