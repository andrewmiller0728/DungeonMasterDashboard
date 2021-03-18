package com.dungeonmaster.dashboard;

import java.util.Random;

public class Dice {

    private static Random random;

    public Dice() {
        random = new Random();
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
