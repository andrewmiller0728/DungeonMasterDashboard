package com.dungeonmaster.dashboard.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.dungeonmaster.dashboard.Dice;
import com.dungeonmaster.dashboard.equipment.*;
import com.dungeonmaster.dashboard.zone.Zone;

import java.util.ArrayList;

public class Character {

    public enum AlignmentX {
        LAWFUL, NEUTRAL, CHAOTIC
    }
    public enum AlignmentY {
        GOOD, NEUTRAL, EVIL
    }

    private String name;
    private Texture icon;
    private Zone zone;
    private Vector2 zonePosition;
    private int maxHitPoints;
    private int currHitPoints;
    private AbilityScores abilityScores;
    private CharacterSkills skills;
    private AlignmentX alignmentX;
    private AlignmentY alignmentY;
    private CharacterBackground background;
    private int speed;
    private int level;
    private int experience;
    private CharacterInventory inventory;

    public Character(
            String name,
            Texture icon,
            Zone zone,
            Vector2 position,
            int[] abilityScores,
            CharacterSkills skills,
            AlignmentX alignX,
            AlignmentY alignY,
            CharacterBackground background,
            int speed
    ) {
        this.name = name;
        this.icon = icon;
        this.zone = zone;
        zonePosition = position;

        this.abilityScores = new AbilityScores(abilityScores);
        this.skills = skills;
        alignmentX = alignX;
        alignmentY = alignY;
        this.background = background;

        maxHitPoints = 8 + (this.abilityScores.getScore(AbilityScores.Ability.CONSTITUTION) / 2);
        currHitPoints = maxHitPoints;

        this.speed = speed;

        level = 1;
        experience = 0;

        inventory = new CharacterInventory(this);
    }

    public Character(String name, Texture icon, Zone zone, Vector2 position) {
        this.name = name;
        this.icon = icon;
        this.zone = zone;
        zonePosition = position;

        abilityScores = new AbilityScores();
        skills = new CharacterSkills(this);
        //region Set AlignmentXY and Background
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
        //endregion

        maxHitPoints = 8 + (this.abilityScores.getScore(AbilityScores.Ability.CONSTITUTION) / 2);
        currHitPoints = maxHitPoints;

        speed = 30;

        level = 1;
        experience = 0;

        inventory = new CharacterInventory(this);
    }

    public int levelUp() {
        if (level != 10) {
            level++;
        }
        return level;
    }

    public Weapon equipWeapon(Weapon newWeapon) {
        return inventory.swapEquippedWeapon(newWeapon);
    }

    public Armor equipArmor(Armor newArmor) {
        return inventory.swapEquippedArmor(newArmor);
    }

    public boolean addItems(Item[] newItems) {
        for (Item item : newItems) {
            if (!addItem(item)) {
                return false;
            }
        }
        return true;
    }

    public boolean addItem(Item newItem) {
        if (newItem != null && getInventoryWeight() < getCarryLimit()) {
            inventory.add(newItem);
            return true;
        }
        else {
            return false;
        }
    }

    //region Methods to get Item lists from inventory
    public ArrayList<Weapon> getWeaponList() {
        return inventory.getWeaponList();
    }

    public ArrayList<Armor> getArmorList() {
        return inventory.getArmorList();
    }

    public ArrayList<Tool> getToolList() {
        return inventory.getToolList();
    }

    public ArrayList<Ammo> getAmmoList() {
        return inventory.getAmmoList();
    }

    public ArrayList<Aid> getAidList() {
        return inventory.getAidList();
    }

    public ArrayList<Item> getMiscItemList() {
        return inventory.getMiscItemList();
    }
    //endregion

    public boolean proposeTrade(Item yourItem, Item targetItem, Character target) {
        return inventory.contains(yourItem) && target.inventory.contains(targetItem)
                && target.getItemTradeValue(yourItem) > target.getItemTradeValue(targetItem);
    }

    public boolean trade(Item yourItem, Item targetItem, Character target) {
        if (proposeTrade(yourItem, targetItem, target)) {
            inventory.remove(yourItem);
            inventory.add(targetItem);
            target.inventory.remove(targetItem);
            target.inventory.add(yourItem);
            return true;
        }
        else {
            return false;
        }
    }

    private int getItemTradeValue(Item item) {
        if (inventory.contains(item)) {
            return item.getTradeValue();
        }
        else {
            return -1;
        }
    }

    //region Methods to drop items from Inventory
    public void dropInventory() {
        inventory.clear();
    }

    public boolean dropItem(Item item) {
        if (item.getClass().equals(Weapon.class)) {
            return dropWeapon((Weapon) item);
        }
        else if (item.getClass().equals(Armor.class)) {
            return dropArmor((Armor) item);
        }
        else if (item.getClass().equals(Tool.class)) {
            return dropTool((Tool) item);
        }
        else if (item.getClass().equals(Ammo.class)) {
            return dropAmmo((Ammo) item);
        }
        else if (item.getClass().equals(Aid.class)) {
            return dropAid((Aid) item);
        }
        else if (inventory.getMiscItemList().contains(item)){
            inventory.getMiscItemList().remove(item);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean dropWeapon(Weapon weapon) {
        if (inventory.contains(weapon)) {
            inventory.remove(weapon);
            return true;
        }
        return false;
    }

    public boolean dropArmor(Armor armor) {
        if (inventory.contains(armor)) {
            inventory.remove(armor);
            return true;
        }
        return false;
    }

    public boolean dropTool(Tool tool) {
        if (inventory.contains(tool)) {
            inventory.remove(tool);
            return true;
        }
        return false;
    }

    public boolean dropAmmo(Ammo ammo) {
        if (inventory.contains(ammo)) {
            inventory.remove(ammo);
            return true;
        }
        return false;
    }

    public boolean dropAid(Aid aid) {
        if (inventory.contains(aid)) {
            inventory.remove(aid);
            return true;
        }
        return false;
    }
    //endregion

    public int useEquippedWeapon(Character target) {
        Dice dice = new Dice();
        if (dice.rollDn(20) >= target.getAC() && isInRange(target)) {
            target.takeDamage(inventory.useEquippedWeapon());
        }
        return 0;
    }

    public int useAid(Aid aid) {
        Dice dice = new Dice();
        if (inventory.useAid(aid)) {
            return heal(level);
        }
        else {
            return 0;
        }
    }

    private boolean isInRange(Character target) {
        return (zone.equals(target.zone)
                && zonePosition.dst(target.zonePosition) <= inventory.getEquippedWeapon().getWeapon().getRange().x);
    }

    public void takeDamage(int damage) {
        if (currHitPoints - damage > 0) {
            currHitPoints -= damage;
        }
        else {
            currHitPoints = 0;
        }
    }

    public int heal(int diceCount) {
        if (diceCount > level) {
            throw new IllegalArgumentException("Hit dice count cannot exceed character level");
        }
        Dice dice = new Dice();
        int initHitPoints = currHitPoints;
            for (int i = 0; i < diceCount; i++) {
                currHitPoints += dice.rollDn(8);
            }
            if (currHitPoints > maxHitPoints) {
                currHitPoints = maxHitPoints;
            }
        return currHitPoints - initHitPoints;
    }

    public boolean abilityCheck(AbilityScores.Ability ability, int dc) {
        Dice dice = new Dice();
        return (dice.rollDn(20) + abilityScores.getModifier(ability)) >= dc;
    }

    public boolean skillCheck(Skill.Name skillName, int dc) {
        Dice dice = new Dice();
        return (dice.rollDn(20) + getSkillBonus(skillName)) >= dc;
    }

    public int getSkillBonus(Skill.Name skillName) {
        return skills.getSkillBonus(skillName);
    }

    //region Getters
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

    public CharacterInventory getInventory() {
        return inventory;
    }

    public int getCarryLimit() {
        return abilityScores.getScore(AbilityScores.Ability.STRENGTH) * 15;
    }

    public int getInventoryWeight() {
        return inventory.getTotalWeight();
    }

    public int getAC() {
        return 10 + abilityScores.getScore(AbilityScores.Ability.DEXTERITY) + inventory.getACModifier();
    }
    //endregion

}
