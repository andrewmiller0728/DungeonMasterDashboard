package com.dungeonmaster.dashboard;

public class Armor extends Item {

    private int ac;

    public Armor(String name, int value, int weight, int ac) {
        super(name, value, weight);
        this.ac = ac;
    }

}
