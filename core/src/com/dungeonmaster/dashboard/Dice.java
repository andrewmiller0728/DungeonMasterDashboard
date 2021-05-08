package com.dungeonmaster.dashboard;

import java.util.Random;

public class Dice {

    private static final Random random = new Random();
    private int quantity;
    private int sides;

    public Dice() {
        this.quantity = 1;
        this.sides = 1;
    }

    public Dice(int quantity, int sides) {
        this.quantity = quantity;
        this.sides = sides;
    }

    public int rollDice() {
        return rollmDn(quantity, sides);
    }

    public int rollDn(int sides) {
        return random.nextInt(sides) + 1;
    }

    public int rollmDn(int mDice, int nSides) {
        int sum = 0;
        for (int i = 0; i < mDice; i++) {
            sum += random.nextInt(nSides) + 1;
        }
        return sum;
    }

    @Override
    public String toString() {
        return String.format("%dd%d", quantity, sides);
    }
}
