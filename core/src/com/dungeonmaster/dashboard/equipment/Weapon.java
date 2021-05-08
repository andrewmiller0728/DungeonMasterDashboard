package com.dungeonmaster.dashboard.equipment;

import com.badlogic.gdx.math.Vector2;
import com.dungeonmaster.dashboard.Dice;

public class Weapon extends Item {

    public enum Category {
        MARTIAL, SIMPLE
    }

    public enum WeaponType {
        MELEE, RANGED
    }

    public enum Size {
        SMALL, LARGE
    }

    private Category category;
    private WeaponType weaponType;
    private Ammo.AmmoType ammoType;
    private Size size;
    private Dice hitDice;
    private boolean consumesAmmo;
    private Vector2 range;
    private boolean isTwoHanded;

    public Weapon() {
        super();
        this.category = Category.SIMPLE;
        this.weaponType = WeaponType.MELEE;
        this.size = Size.SMALL;
        this.hitDice = new Dice(1, 1);
        this.consumesAmmo = false;
        this.ammoType = Ammo.AmmoType.NONE;
        this.range = new Vector2(5, 5);
        this.isTwoHanded = false;
    }

    public Weapon(Item baseItem, Dice hitDice, WeaponType weaponType, boolean consumesAmmo, Ammo.AmmoType ammoType, Vector2 range) {
        super(baseItem.getName(), baseItem.getTradeValue(), baseItem.getWeight(), baseItem.isEquippable(), baseItem.isConsumable());
        this.category = Category.SIMPLE;
        this.weaponType = weaponType;
        this.size = Size.SMALL;
        this.hitDice = hitDice;
        this.consumesAmmo = consumesAmmo;
        this.ammoType = ammoType;
        this.range = range;
        this.isTwoHanded = false;
    }

    public Weapon(Item baseItem, Dice hitDice, Category category, WeaponType weaponType, Size size, boolean consumesAmmo, Ammo.AmmoType ammoType, Vector2 range, boolean isTwoHanded) {
        super(baseItem.getName(), baseItem.getTradeValue(), baseItem.getWeight(), baseItem.isEquippable(), baseItem.isConsumable());
        this.category = category;
        this.weaponType = weaponType;
        this.size = size;
        this.hitDice = hitDice;
        this.consumesAmmo = consumesAmmo;
        this.ammoType = ammoType;
        this.range = range;
        this.isTwoHanded = isTwoHanded;
    }

    public Category getCategory() {
        return category;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public Size getSize() {
        return size;
    }

    public Dice getHitDice() {
        return hitDice;
    }

    public boolean consumesAmmo() {
        return consumesAmmo;
    }

    public Ammo.AmmoType getAmmoType() {
        return ammoType;
    }

    public Vector2 getRange() {
        return range;
    }

    public boolean isTwoHanded() {
        return isTwoHanded;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "category=" + category +
                ", weaponType=" + weaponType +
                ", ammoType=" + ammoType +
                ", size=" + size +
                ", hitDice=" + hitDice +
                ", consumesAmmo=" + consumesAmmo +
                ", range=" + range +
                ", isTwoHanded=" + isTwoHanded +
                '}';
    }
}
