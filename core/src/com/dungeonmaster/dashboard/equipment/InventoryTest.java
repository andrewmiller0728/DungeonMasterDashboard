package com.dungeonmaster.dashboard.equipment;

public class InventoryTest {

    public static void main(String[] args) {
        System.out.println("[Test]    Testing Inventory");

        Item myItem = new Item("Item A", 5, 10);
        Item yourItem = new Item("Item B", 2, 15);
        Item ourItem = new Item("Item C", 30, 4);

        Inventory bagOfHolding = new Inventory();
        bagOfHolding.addItem(myItem);
        bagOfHolding.addItem(yourItem);
        bagOfHolding.addItem(ourItem);

        System.out.printf(
                "Inventory:\n" +
                        "\tTotal Trade Value = %d\n" +
                        "\tTotal Ammo = %d\n" +
                        "\tTotal Weight = %d\n",
                bagOfHolding.getTotalTradeValue(),
                bagOfHolding.getTotalAmmo(),
                bagOfHolding.getTotalWeight()
        );
    }

}
