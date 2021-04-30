package com.dungeonmaster.dashboard.character;

public class Skill {

    public enum Name {
        ATHLETICS, ACROBATICS, SLEIGHT_OF_HAND,
        STEALTH, SCIENCE, HISTORY, INVESTIGATION,
        NATURE, RELIGION, ANIMAL_HANDLING,
        INSIGHT, PERCEPTION, MEDICINE,
        SURVIVAL, DECEPTION, INTIMIDATION,
        PERFORMANCE, PERSUASION
    }

    private CharacterAbilityScores.Ability refAbility;
    private Name name;
    private boolean isProficient;
    private boolean isExpert;

    public Skill(CharacterAbilityScores.Ability refAbility, Name name) {
        this.refAbility = refAbility;
        this.name = name;
        isProficient = false;
        isExpert = false;
    }

    public Skill(CharacterAbilityScores.Ability refAbility, Name name, boolean isProficient, boolean isExpert) {
        this.refAbility = refAbility;
        this.name = name;
        this.isProficient = isProficient;
        this.isExpert = isExpert;
    }

    public void setProficient(boolean proficient) {
        isProficient = proficient;
    }

    public void setExpert(boolean expert) {
        isExpert = expert;
    }

    public boolean isProficient() {
        return isProficient;
    }

    public boolean isExpert() {
        return isExpert;
    }

    public CharacterAbilityScores.Ability getRefAbility() {
        return refAbility;
    }

    public Name getName() {
        return name;
    }

}
