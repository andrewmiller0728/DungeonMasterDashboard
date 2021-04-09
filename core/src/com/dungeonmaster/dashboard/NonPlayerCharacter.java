package com.dungeonmaster.dashboard;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class NonPlayerCharacter extends Character {

    public NonPlayerCharacter(
            String name,
            Texture icon,
            Vector3 loc,
            CharacterBackground background,
            int[] abilityScores,
            AlignmentX alignX,
            AlignmentY alignY,
            int speed
    ) {
        super(name, icon, loc, background, abilityScores, alignX, alignY, speed);
    }

    public NonPlayerCharacter(String name, Texture icon, Vector3 loc) {
        super(name, icon, loc);
    }
}
