package com.dungeonmaster.dashboard;

public class Item {

    private String name;
    private int tradeValue;
    private int weight;

    public Item(String name, int value, int weight) {
        this.name = name;
        tradeValue = value;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }
}
