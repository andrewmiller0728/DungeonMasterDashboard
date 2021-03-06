package com.dungeonmaster.dashboard;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Character {

    private String name;
    private Texture icon;
    private Zone zone;
    private Vector2 zonePosition;
    private int maxHitPoints;
    private int currHitPoints;
    private CharacterBackground background;
    private AbilityScores abilityScores;
    private AlignmentX alignmentX;
    private AlignmentY alignmentY;
    private ArrayList<Item> inventory;
    private int speed;
    private int level;
    private int experience;

    public Character(
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
        this.name = name;
        this.icon = icon;
        this.zone = zone;
        zonePosition = loc;
        this.background = background;
        this.abilityScores = new AbilityScores(abilityScores);
        alignmentX = alignX;
        alignmentY = alignY;

        maxHitPoints = 8 + (this.abilityScores.getScore(Abilities.CONSTITUTION) / 2);
        currHitPoints = maxHitPoints;

        inventory = new ArrayList<>();
        this.speed = speed;

        level = 1;
        experience = 0;
    }

    public Character(String name, Texture icon, Zone zone, Vector2 loc) {
        this.name = name;
        this.icon = icon;
        this.zone = zone;
        zonePosition = loc;
        this.abilityScores = new AbilityScores();

        Dice dice = new Dice();
        // Set alignmentX
        switch (dice.rollDn(3)) {
            case 1:
                alignmentX = AlignmentX.LAWFUL;
                break;
            case 3:
                alignmentX = AlignmentX.CHAOTIC;
                break;
            default:
                alignmentX = AlignmentX.NEUTRAL;
                break;
        }
        // Set alignmentY
        switch (dice.rollDn(3)) {
            case 1:
                alignmentY = AlignmentY.GOOD;
                break;
            case 3:
                alignmentY = AlignmentY.EVIL;
                break;
            default:
                alignmentY = AlignmentY.NEUTRAL;
                break;
        }
        // Set background
        switch (dice.rollDn(12)) {
            case 1:
                background = CharacterBackground.ACOLYTE;
                break;
            case 2:
                background = CharacterBackground.ATHLETE;
                break;
            case 3:
                background = CharacterBackground.CHARLATAN;
                break;
            case 4:
                background = CharacterBackground.CRIMINAL;
                break;
            case 5:
                background = CharacterBackground.ENTERTAINER;
                break;
            case 6:
                background = CharacterBackground.HEALER;
                break;
            case 7:
                background = CharacterBackground.LABORER;
                break;
            case 8:
                background = CharacterBackground.NOBLE;
                break;
            case 9:
                background = CharacterBackground.SCHOLAR;
                break;
            case 10:
                background = CharacterBackground.SURVIVALIST;
                break;
            case 11:
                background = CharacterBackground.SOLDIER;
                break;
            case 12:
                background = CharacterBackground.TRAVELER;
                break;
            default:
                background = CharacterBackground.URCHIN;
                break;
        }

        maxHitPoints = 8 + (this.abilityScores.getScore(Abilities.CONSTITUTION) / 2);
        currHitPoints = maxHitPoints;

        inventory = new ArrayList<>();
        speed = 30;

        level = 1;
        experience = 0;
    }


    // Getters

    public Texture getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public CharacterBackground getBackground() {
        return background;
    }

    public AbilityScores getAbilityScores() {
        return abilityScores;
    }

    public Zone getZone() {
        return zone;
    }

    public Vector2 getZonePosition() {
        return zonePosition;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public int getCurrHitPoints() {
        return currHitPoints;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public AlignmentX getAlignmentX() {
        return alignmentX;
    }

    public AlignmentY getAlignmentY() {
        return alignmentY;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    // Combat

    public void takeDamage(int hp) {
        currHitPoints -= hp;
    }


    // Movement

    public void move(Vector2 moveVector) {
        zonePosition.add(moveVector);
    }


    // Abilities

    public boolean abilityCheck(Abilities ability, int dc) {
        Dice dice = new Dice();
        return (dice.rollDn(20) + abilityScores.getModifier(ability)) >= dc;
    }


    // Inventory

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

}
