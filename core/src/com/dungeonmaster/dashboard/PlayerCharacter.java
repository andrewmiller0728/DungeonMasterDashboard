package com.dungeonmaster.dashboard;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class PlayerCharacter extends Character {

    private String playerName;
    private String ideals;
    private String bonds;
    private String flaws;

    public PlayerCharacter(
            String playerName,
            String charName,
            Texture icon,
            Vector3 loc,
            Background background,
            int[] abilityScores,
            AlignmentX alignX,
            AlignmentY alignY,
            int speed,
            String ideals,
            String bonds,
            String flaws
    ) {
        super(charName, icon, loc, background, abilityScores, alignX, alignY, speed);
        this.playerName = playerName;
        this.ideals = ideals;
        this.bonds = bonds;
        this.flaws = flaws;
    }

    public String getPlayerName() {
        return playerName;
    }
}
