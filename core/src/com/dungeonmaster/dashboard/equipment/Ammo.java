package com.dungeonmaster.dashboard.equipment;

public class Ammo extends Item {

    public enum AmmoType {
        HANDGUN, RIFLE, SHOTGUN, EXPLOSIVE, NONE
    }

    private int quantity;
    private AmmoType type;

    public Ammo(AmmoType type) {
        super();
        quantity = 1;
        this.type = type;
    }

    public Ammo(Item baseItem, int quantity, AmmoType type) {
        super(
                baseItem.getName(),
                baseItem.getTradeValue(),
                baseItem.getWeight(),
                baseItem.isEquippable(),
                baseItem.isConsumable()
        );
        this.quantity = quantity;
        this.type = type;
    }

    public void consumeAmmo() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public AmmoType getType() {
        return type;
    }

    public boolean isEmpty() {
        return type == AmmoType.NONE || quantity == 0;
    }

    public boolean isType(AmmoType type) {
        return this.type == type;
    }

}
