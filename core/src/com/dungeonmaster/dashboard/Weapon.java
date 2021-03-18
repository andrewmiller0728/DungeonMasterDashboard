package com.dungeonmaster.dashboard;

public class Weapon extends Item {

    private int damage;
    private int shortRange;
    private int longRange;

    public Weapon(String name, int value, int weight, int damage, int shortRange, int longRange) {
        super(name, value, weight);
        this.damage = damage;
        this.shortRange = shortRange;
        this.longRange = longRange;
    }

}
