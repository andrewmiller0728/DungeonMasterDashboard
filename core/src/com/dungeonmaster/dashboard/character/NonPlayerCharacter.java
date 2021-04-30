package com.dungeonmaster.dashboard.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.dungeonmaster.dashboard.zone.Zone;

public class NonPlayerCharacter extends Character {

    public NonPlayerCharacter(
            String name,
            Texture icon,
            Zone zone,
            Vector2 loc,
            int[] abilityScores,
            CharacterSkills skills,
            AlignmentX alignX,
            AlignmentY alignY,
            CharacterBackground background,
            int speed
    ) {
        super(name, icon, zone, loc, abilityScores, skills, alignX, alignY, background, speed);
    }

    public NonPlayerCharacter(String name, Texture icon, Zone zone, Vector2 loc) {
        super(name, icon, zone, loc);
    }
}
