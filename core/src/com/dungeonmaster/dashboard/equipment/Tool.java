package com.dungeonmaster.dashboard.equipment;

public class Tool extends Item {

    private String description;

    public Tool() {
        super();
        this.description = "No description given.";
    }

    public Tool(Item baseItem, String description) {
        super(
                baseItem.getName(),
                baseItem.getTradeValue(),
                baseItem.getWeight(),
                baseItem.isEquippable(),
                baseItem.isConsumable()
        );
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
