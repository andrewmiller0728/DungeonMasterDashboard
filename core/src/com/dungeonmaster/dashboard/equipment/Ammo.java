package com.dungeonmaster.dashboard.equipment;

public class Ammo extends Item {

    private int quantity;

    public Ammo() {
        super();
        quantity = 1;
    }

    public Ammo(Item baseItem, int quantity) {
        super(
                baseItem.getName(),
                baseItem.getTradeValue(),
                baseItem.getWeight(),
                baseItem.canEquip(),
                baseItem.isConsumable()
        );
        this.quantity = quantity;
    }

    public void consumeAmmo() {
        if (quantity >= 0) {
            quantity--;
        }
    }

    public void consumeAmmo(int count) {
        if (quantity - count >= 0) {
            quantity -= count;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isEmpty() {
        return quantity == 0;
    }

}
