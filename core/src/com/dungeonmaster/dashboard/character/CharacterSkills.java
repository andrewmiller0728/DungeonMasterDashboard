package com.dungeonmaster.dashboard.character;

import java.util.ArrayList;

public class CharacterSkills {

    // index is level - 1
    private static final int[] BONUSES = {2, 2, 2, 2, 3, 3, 3, 3, 4, 4};

    private Character character;
    private ArrayList<Skill> skills;

    public CharacterSkills(Character character) {
        this.character = character;
        skills = new ArrayList<>();
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

}
