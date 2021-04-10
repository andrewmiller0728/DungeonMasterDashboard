package com.dungeonmaster.dashboard;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class NonPlayerCharacter extends Character {

    public NonPlayerCharacter(
            String name,
            Texture icon,
            Zone zone,
            Vector2 loc,
            CharacterBackground background,
            int[] abilityScores,
            AlignmentX alignX,
            AlignmentY alignY,
            int speed
    ) {
        super(name, icon, zone, loc, background, abilityScores, alignX, alignY, speed);
    }

    public NonPlayerCharacter(String name, Texture icon, Zone zone, Vector2 loc) {
        super(name, icon, zone, loc);
    }
}
