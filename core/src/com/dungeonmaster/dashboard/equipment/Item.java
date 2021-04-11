package com.dungeonmaster.dashboard.equipment;

public class Item {

    private static int nextID = 0;

    private int id;
    private String name;
    private boolean equippable;
    private boolean consumable;
    private boolean consumed;
    private int tradeValue;
    private int weight;

    public Item() {
        id = nextID;
        nextID++;
        name = String.format("Item#%d", id);
        equippable = false;
        consumable = false;
        consumed = false;
        tradeValue = 0;
        weight = 0;
    }

    public Item(String name, int tradeValue, int weight) {
        id = nextID;
        nextID++;
        this.name = name;
        equippable = false;
        consumable = false;
        consumed = false;
        this.tradeValue = tradeValue;
        this.weight = weight;
    }

    public Item(String name, int tradeValue, int weight, boolean canEquip, boolean consumable) {
        id = nextID;
        nextID++;
        this.name = name;
        this.equippable = canEquip;
        this.consumable = consumable;
        consumed = false;
        this.tradeValue = tradeValue;
        this.weight = weight;
    }

    public void consume() {
        if (consumable && !consumed) {
            consumed = true;
        }
        else {
            throw new RuntimeException("Must consume a consumable and unconsumed Item.");
        }
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEquippable() {
        return equippable;
    }

    public boolean isConsumable() {
        return consumable;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public int getTradeValue() {
        return tradeValue;
    }

    public int getWeight() {
        return weight;
    }

}
