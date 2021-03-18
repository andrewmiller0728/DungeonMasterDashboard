package com.dungeonmaster.dashboard;

public class AbilityScores {

    private int[] scores;

    public AbilityScores() {
        Dice dice = new Dice();
        scores = new int[6];
        for (int i = 0; i < scores.length; i++) {
            scores[i] = dice.rollmDn(3, 6) + 1;
        }
    }

    public AbilityScores(int[] scores) {
        this.scores = scores;
    }

    public int getScore(Abilities ability) {
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

    public void setScore(Abilities ability, int score) {
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

    public int getModifier(Abilities ability) {
        return getScore(ability) / 2;
    }

    public void adjustScore(Abilities ability, int adjVal) {
        setScore(ability, getScore(ability) + adjVal);
    }
}
