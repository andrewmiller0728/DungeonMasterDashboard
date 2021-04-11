package com.dungeonmaster.dashboard.equipment;

import com.badlogic.gdx.math.Vector2;
import com.dungeonmaster.dashboard.Dice;

public class Weapon extends Item {

    public enum Category {
        MARTIAL, SIMPLE
    }

    public enum Type {
        MELEE, RANGED
    }

    public enum Size {
        SMALL, LARGE
    }

    private Category category;
    private Type type;
    private Size size;
    private Dice hitDice;
    private boolean consumesAmmo;
    private Vector2 range;
    private boolean isTwoHanded;

    public Weapon() {
        super();
        this.category = Category.SIMPLE;
        this.type = Type.MELEE;
        this.size = Size.SMALL;
        this.hitDice = new Dice(1, 1);
        this.consumesAmmo = false;
        this.range = new Vector2(5, 5);
        this.isTwoHanded = false;
    }

    public Weapon(Item baseItem, Dice hitDice, Type type, boolean consumesAmmo, Vector2 range) {
        super(baseItem.getName(), baseItem.getTradeValue(), baseItem.getWeight(), baseItem.canEquip(), baseItem.isConsumable());
        this.category = Category.SIMPLE;
        this.type = type;
        this.size = Size.SMALL;
        this.hitDice = hitDice;
        this.consumesAmmo = consumesAmmo;
        this.range = range;
        this.isTwoHanded = false;
    }

    public Weapon(Item baseItem, Category category, Type type, Size size, Dice hitDice, boolean consumesAmmo, Vector2 range, boolean isTwoHanded) {
        super(baseItem.getName(), baseItem.getTradeValue(), baseItem.getWeight(), baseItem.canEquip(), baseItem.isConsumable());
        this.category = category;
        this.type = type;
        this.size = size;
        this.hitDice = hitDice;
        this.consumesAmmo = consumesAmmo;
        this.range = range;
        this.isTwoHanded = isTwoHanded;
    }

    public Category getCategory() {
        return category;
    }

    public Type getType() {
        return type;
    }

    public Size getSize() {
        return size;
    }

    public Dice getHitDice() {
        return hitDice;
    }

    public boolean isConsumesAmmo() {
        return consumesAmmo;
    }

    public Vector2 getRange() {
        return range;
    }

    public boolean isTwoHanded() {
        return isTwoHanded;
    }

}
