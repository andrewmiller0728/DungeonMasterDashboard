package com.dungeonmaster.dashboard.equipment;

public class InventoryTest {

    public static void main(String[] args) {
        System.out.println("[Test]    Testing Inventory");

        Item myItem = new Item("Item A", 5, 10);
        Item yourItem = new Item("Item B", 2, 15);
        Item ourItem = new Item("Item C", 30, 4);
        Weapon sword = new Weapon();

        Inventory bagOfHolding = new Inventory();
        bagOfHolding.add(myItem);
        bagOfHolding.add(yourItem);
        bagOfHolding.add(ourItem);
        bagOfHolding.add(sword);

        System.out.printf(
                "Inventory:\n" +
                        "\tTotal Trade Value = %d\n" +
                        "\tTotal Ammo = %d\n" +
                        "\tTotal Weight = %d\n",
                bagOfHolding.getTotalTradeValue(),
                bagOfHolding.getTotalAmmo(),
                bagOfHolding.getTotalWeight()
        );

        System.out.println(bagOfHolding.contains(sword));
    }

}
