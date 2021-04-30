package com.dungeonmaster.dashboard.character;

import com.dungeonmaster.dashboard.Dice;

public class CharacterAbilityScores {

    public enum Ability {
        STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA;
    }

    public final static int SCORE_COUNT = 6;

    private final int[] scores;

    public CharacterAbilityScores() {
        Dice dice = new Dice();
        scores = new int[6];
        for (int i = 0; i < scores.length; i++) {
            scores[i] = dice.rollmDn(3, 6) + 1; // +1 because all characters are human
        }
    }

    public CharacterAbilityScores(int[] scores) {
        this.scores = scores;
    }

    public int getScore(Ability ability) {
        switch (ability) {
            case STRENGTH:
                return scores[0];
            case DEXTERITY:
                return scores[1];
            case CONSTITUTION:
                return scores[2];
            case INTELLIGENCE:
                return scores[3];
            case WISDOM:
                return scores[4];
            case CHARISMA:
                return scores[5];
            default:
                return -1;
        }
    }

    public int getScore(int index) {
        return scores[index];
    }

    public void setScore(Ability ability, int score) {
        switch (ability) {
            case STRENGTH:
                scores[0] = score;
            case DEXTERITY:
                scores[1] = score;
            case CONSTITUTION:
                scores[2] = score;
            case INTELLIGENCE:
                scores[3] = score;
            case WISDOM:
                scores[4] = score;
            case CHARISMA:
                scores[5] = score;
            default:
        }
    }

    public int getModifier(Ability ability) {
        return getScore(ability) / 2;
    }

    public void adjustScore(Ability ability, int adjVal) {
        setScore(ability, getScore(ability) + adjVal);
    }
}
