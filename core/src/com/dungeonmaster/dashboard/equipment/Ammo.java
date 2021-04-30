package com.dungeonmaster.dashboard.equipment;

public class Ammo extends Item {

    private int quantity;

    // TODO: AMMO TYPES (HANDGUN, RIFLE, SHOTGUN, ROCKET)

    public Ammo() {
        super();
        quantity = 1;
    }

    public Ammo(Item baseItem, int quantity) {
        super(
                baseItem.getName(),
                baseItem.getTradeValue(),
                baseItem.getWeight(),
                baseItem.isEquippable(),
                baseItem.isConsumable()
        );
        this.quantity = quantity;
    }

    public void consumeAmmo() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isEmpty() {
        return quantity == 0;
    }

}
