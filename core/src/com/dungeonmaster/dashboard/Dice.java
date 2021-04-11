package com.dungeonmaster.dashboard;

import java.util.Random;

public class Dice {

    private static Random random;
    private int quantity;
    private int sides;

    public Dice() {
        random = new Random();
        this.quantity = 1;
        this.sides = 1;
    }

    public Dice(int quantity, int sides) {
        random = new Random();
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

}
