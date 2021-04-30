package com.dungeonmaster.dashboard.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.dungeonmaster.dashboard.zone.Zone;

public class PlayerCharacter extends Character {

    private String playerName;
    private String ideals;
    private String bonds;
    private String flaws;

    public PlayerCharacter(
            String playerName,
            String charName,
            Texture icon,
            Zone zone,
            Vector2 loc,
            int[] abilityScores,
            CharacterSkills skills,
            AlignmentX alignX,
            AlignmentY alignY,
            CharacterBackground background,
            int speed,
            String ideals,
            String bonds,
            String flaws
    ) {
        super(charName, icon, zone, loc, abilityScores, skills, alignX, alignY, background, speed);
        this.playerName = playerName;
        this.ideals = ideals;
        this.bonds = bonds;
        this.flaws = flaws;
    }

    public String getPlayerName() {
        return playerName;
    }
}
