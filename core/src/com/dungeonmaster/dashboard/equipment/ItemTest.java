package com.dungeonmaster.dashboard.equipment;

public class ItemTest {

    public static void main(String[] args) {
        System.out.println("[Test]    Testing Inventory");

        // Set up Items for testing
        Item baseItem = new Item("Basic Item", 12, 10);
        Item equipItem = new Item("Equip Item", 42, 7, true, false);
        Item consumeItem = new Item("Consume Item", 5, 2, false, true);
        Item equipConsumeItem = new Item("Double Item", 58, 12, true, true);
        Item[] items = {baseItem, equipItem, consumeItem, equipConsumeItem};

        System.out.println("[Test]    Displaying Initial Inventory");
        printItemsAsTable(items, 14);

        // Modify Items as expected
        consumeItem.consume();
        consumeItem.setName("Consumed Item");
        equipConsumeItem.consume();
        equipConsumeItem.setName("Eaten Item");

        System.out.println("[Test]    Displaying Modified Inventory");
        printItemsAsTable(items, 14);

        // Try to do disallowed things
        try {
            equipItem.consume();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            consumeItem.consume();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("[Test]    Displaying Broken Inventory");
        printItemsAsTable(items, 14);
    }

    private static void printItemsAsTable(Item[] items, int columnWidth) {
        String cellFormat = "%" + columnWidth + "s";
        //region Print table header of Item variables
        System.out.printf(
                "Items:\n\t| " + cellFormat +
                        " | " + cellFormat +
                        " | " + cellFormat +
                        " | " + cellFormat +
                        " | " + cellFormat +
                        " | " + cellFormat +
                        " | " + cellFormat +
                        " |\n",
                "ID",
                "NAME",
                "EQUIPPABLE",
                "CONSUMABLE",
                "CONSUMED",
                "TRADE VALUE",
                "WEIGHT"
        );
        //endregion
        //region Print table horizontal bar between header row and data rows
        System.out.print("\t");
        for (int i = 0; i < ((columnWidth + 3) * 7) + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
        //endregion
        for (Item item : items) {
            System.out.printf(
                    "\t| %s | %s | %s | %s | %s | %s | %s |\n",
                    String.format(cellFormat, item.getID()),
                    String.format(cellFormat, item.getName()),
                    String.format(cellFormat, item.isEquippable()),
                    String.format(cellFormat, item.isConsumable()),
                    String.format(cellFormat, item.isConsumed()),
                    String.format(cellFormat, item.getTradeValue()),
                    String.format(cellFormat, item.getWeight())
            );
        }
    }

}
