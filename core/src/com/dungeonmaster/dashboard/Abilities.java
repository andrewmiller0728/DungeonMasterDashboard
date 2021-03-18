package com.dungeonmaster.dashboard;

public enum Abilities {

    STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA;

    public final static int SCORE_COUNT = 6;

    public static String[] getAbilityStrings() {
        String[] stringArray = new String[SCORE_COUNT];
        stringArray[0] = Abilities.STRENGTH.name();
        stringArray[1] = Abilities.DEXTERITY.name();
        stringArray[2] = Abilities.CONSTITUTION.name();
        stringArray[3] = Abilities.INTELLIGENCE.name();
        stringArray[4] = Abilities.WISDOM.name();
        stringArray[5] = Abilities.CHARISMA.name();
        return stringArray;
    }
}
