package com.dungeonmaster.dashboard.character;

import java.util.ArrayList;

public class CharacterSkills {

    // index is level - 1
    private static final int[] BONUSES = {2, 2, 2, 2, 3, 3, 3, 3, 4, 4};

    private Character character;
    private ArrayList<Skill> skills;

    public CharacterSkills() {
        this.character = null;
        skills = new ArrayList<>();
        addBasicSkillSet();
    }
    public CharacterSkills(Character character) {
        this.character = character;
        skills = new ArrayList<>();
        addBasicSkillSet();
    }

    private void addBasicSkillSet() {
        skills.add(new Skill(CharacterAbilityScores.Ability.STRENGTH, Skill.Name.ATHLETICS));
        skills.add(new Skill(CharacterAbilityScores.Ability.DEXTERITY, Skill.Name.ACROBATICS));
        skills.add(new Skill(CharacterAbilityScores.Ability.DEXTERITY, Skill.Name.SLEIGHT_OF_HAND));
        skills.add(new Skill(CharacterAbilityScores.Ability.DEXTERITY, Skill.Name.STEALTH));
        skills.add(new Skill(CharacterAbilityScores.Ability.INTELLIGENCE, Skill.Name.SCIENCE));
        skills.add(new Skill(CharacterAbilityScores.Ability.INTELLIGENCE, Skill.Name.HISTORY));
        skills.add(new Skill(CharacterAbilityScores.Ability.INTELLIGENCE, Skill.Name.INVESTIGATION));
        skills.add(new Skill(CharacterAbilityScores.Ability.INTELLIGENCE, Skill.Name.NATURE));
        skills.add(new Skill(CharacterAbilityScores.Ability.INTELLIGENCE, Skill.Name.RELIGION));
        skills.add(new Skill(CharacterAbilityScores.Ability.WISDOM, Skill.Name.ANIMAL_HANDLING));
        skills.add(new Skill(CharacterAbilityScores.Ability.WISDOM, Skill.Name.INSIGHT));
        skills.add(new Skill(CharacterAbilityScores.Ability.WISDOM, Skill.Name.PERCEPTION));
        skills.add(new Skill(CharacterAbilityScores.Ability.WISDOM, Skill.Name.MEDICINE));
        skills.add(new Skill(CharacterAbilityScores.Ability.WISDOM, Skill.Name.SURVIVAL));
        skills.add(new Skill(CharacterAbilityScores.Ability.CHARISMA, Skill.Name.DECEPTION));
        skills.add(new Skill(CharacterAbilityScores.Ability.CHARISMA, Skill.Name.INTIMIDATION));
        skills.add(new Skill(CharacterAbilityScores.Ability.CHARISMA, Skill.Name.PERFORMANCE));
        skills.add(new Skill(CharacterAbilityScores.Ability.CHARISMA, Skill.Name.PERSUASION));
    }

    public boolean addSkill(Skill skill) {
        if (!skills.contains(skill)) {
            skills.add(skill);
            return true;
        }
        else {
            return false;
        }
    }

    public Skill getSkill(Skill.Name skillName) {
        for (int i = 0; i < skills.size(); i++) {
            if (skills.get(i).getName() == skillName) {
                return skills.get(i);
            }
        }
        return null;
    }

    public int getSkillBonus(Skill.Name skillName) {
        int skillBonus = character.getAbilityScores().getModifier(getSkill(skillName).getRefAbility());
        if (getSkill(skillName).isProficient()) {
            skillBonus += BONUSES[character.getLevel() - 1];
        }
        if (getSkill(skillName).isExpert()) {
            skillBonus += BONUSES[character.getLevel() - 1];
        }
        return skillBonus;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

}
