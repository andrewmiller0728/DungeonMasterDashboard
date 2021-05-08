package com.dungeonmaster.dashboard.equipment;

import com.badlogic.gdx.math.Vector2;
import com.dungeonmaster.dashboard.Dice;

public class CustomWeapon {

    private Weapon baseWeapon;
    private String customName;
    private Dice customHitDice;
    private Vector2 customRange;
    private int customWeight;
    private int customTradeValue;
    private Ammo.AmmoType customAmmoType;

    public CustomWeapon(Weapon baseWeapon, String customName) {
        this.baseWeapon = baseWeapon;
        this.customName = customName;
        customHitDice = null;
        customRange = null;
        customWeight = -1;
        customTradeValue = -1;
        customAmmoType = null;
    }


    // Set custom parameters as needed
    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public void setCustomHitDice(Dice customHitDice) {
        this.customHitDice = customHitDice;
    }

    public void setCustomRange(Vector2 customRange) {
        this.customRange = customRange;
    }

    public void setCustomWeight(int customWeight) {
        this.customWeight = customWeight;
    }

    public void setCustomTradeValue(int customTradeValue) {
        this.customTradeValue = customTradeValue;
    }

    public void setCustomAmmoType(Ammo.AmmoType customAmmoType) {
        this.customAmmoType = customAmmoType;
    }


    // Get base weapon for base weapon data
    public Weapon getBaseWeapon() {
        return baseWeapon;
    }


    // Get custom weapon data
    public Dice getHitDice() {
        return customHitDice == null ? baseWeapon.getHitDice() : customHitDice;
    }

    public Ammo.AmmoType getAmmoType() {
        return customAmmoType == null ? baseWeapon.getAmmoType() : customAmmoType;
    }

    public Vector2 getRange() {
        return customRange == null ? baseWeapon.getRange() : customRange;
    }

    public int getWeight() {
        return customWeight == -1 ? baseWeapon.getWeight() : customWeight;
    }

    public int getTradeValue() {
        return customTradeValue == -1 ? baseWeapon.getTradeValue() : customTradeValue;
    }

    @Override
    public String toString() {
        return "CustomWeapon{" +
                "baseWeapon=" + baseWeapon +
                ", customName='" + customName + '\'' +
                ", customHitDice=" + customHitDice +
                ", customRange=" + customRange +
                ", customWeight=" + customWeight +
                ", customTradeValue=" + customTradeValue +
                ", customAmmoType=" + customAmmoType +
                '}';
    }

}
