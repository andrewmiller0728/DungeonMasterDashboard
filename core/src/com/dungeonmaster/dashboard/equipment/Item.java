package com.dungeonmaster.dashboard.equipment;

public class Item {

    private static int nextID = 0;

    private int id;
    private String name;
    private boolean canEquip;
    private boolean consumable;
    private int tradeValue;
    private int weight;

    public Item() {
        id = nextID;
        nextID++;
        name = String.format("Item#%d", id);
        canEquip = false;
        consumable = false;
        tradeValue = 0;
        weight = 0;
    }

    public Item(String name, int tradeValue, int weight) {
        id = nextID;
        nextID++;
        this.name = name;
        canEquip = false;
        consumable = false;
        this.tradeValue = tradeValue;
        this.weight = weight;
    }

    public Item(String name, int tradeValue, int weight, boolean canEquip, boolean consumable) {
        id = nextID;
        nextID++;
        this.name = name;
        this.canEquip = canEquip;
        this.consumable = consumable;
        this.tradeValue = tradeValue;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean canEquip() {
        return canEquip;
    }

    public boolean isConsumable() {
        return consumable;
    }

    public int getTradeValue() {
        return tradeValue;
    }

    public int getWeight() {
        return weight;
    }

}
