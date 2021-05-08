package com.dungeonmaster.dashboard.equipment;

public class ItemFactoryTest {

    public static void main(String[] args) {
        System.out.println("Testing Item Factory...");
        ItemFactory itemFactory = new ItemFactory();

        System.out.println("Testing Weapons...");
        testWeaponFactory(itemFactory);

        System.out.println("Testing Armor...");
        testArmorFactory(itemFactory);
    }

    private static void testWeaponFactory(ItemFactory itemFactory) {
        String[] baseWeaponNames = itemFactory.getBaseWeaponNames();

        System.out.print("\tBase weapons:\n");
        for (String name : baseWeaponNames) {
            System.out.printf("\t\t- %s\n", name);
        }

        int testWeaponIndex = (int) (Math.random() * baseWeaponNames.length);
        System.out.printf("\tLoading base weapon \"%s\":\n", baseWeaponNames[testWeaponIndex]);
        Weapon testBaseWeapon = itemFactory.getBaseWeapon(baseWeaponNames[testWeaponIndex]);
        System.out.printf("\t\t- %s\n", testBaseWeapon.toString());

        String testCustomName = "Bertha";
        CustomWeapon testCustomWeapon = itemFactory.createCustomWeapon(baseWeaponNames[testWeaponIndex], testCustomName);
        System.out.printf("\tCreating custom weapon from base weapon \"%s\":\n\t\t- %s\n", testBaseWeapon.getName(), testCustomWeapon.toString());
    }

    private static void testArmorFactory(ItemFactory itemFactory) {
        // List names of base armor types
        String[] baseArmorNames = itemFactory.getBaseArmorNames();
        System.out.print("\tBase armor types:\n");
        for (String name : baseArmorNames) {
            System.out.printf("\t\t- %s\n", name);
        }

        // Load and display a base armor set and its pieces
        String testBaseArmorName = baseArmorNames[(int) (Math.random() * baseArmorNames.length)];
        System.out.printf("\tUsing base armor set \"%s\" for testing.\n", testBaseArmorName);
        ArmorSet testBaseArmorSet = itemFactory.getBaseArmorSet(testBaseArmorName);
        System.out.printf("\tBase armor set \"%s\" contains:\n", testBaseArmorSet.getPiece(Armor.BodyPart.HEAD).getName());
        for (Armor.BodyPart bodyPart : Armor.BodyPart.values()) {
            Armor testBaseArmorPiece = testBaseArmorSet.getPiece(bodyPart);
            if (testBaseArmorPiece != null) {
                System.out.printf("\t\t- %s: %s\n", bodyPart.name(), testBaseArmorPiece);
            }
            else {
                System.out.printf("\t\t- %s: null\n", bodyPart.name());
            }
        }

        // Create a single custom armor piece
        Armor.BodyPart testBodyPart = Armor.BodyPart.values()[(int) (Math.random() * (Armor.BodyPart.values().length - 2))];
        System.out.printf("\tCreating custom %s armor piece using \"%s\" base:\n", testBodyPart.name(), testBaseArmorSet.getPiece(Armor.BodyPart.HEAD).getName());
        CustomArmor testCustomPiece = itemFactory.createCustomArmorPiece(testBaseArmorName, "Custom Armor Piece", testBodyPart);
        System.out.printf("\t\t- %s\n", testCustomPiece.toString());

        // Create a partial custom armor set
        Armor.BodyPart[] shirtBodyParts = {Armor.BodyPart.LEFT_ARM, Armor.BodyPart.TORSO, Armor.BodyPart.RIGHT_ARM};
        System.out.printf("\tCreating partial custom armor set using \"%s\" base:\n", testBaseArmorSet.getPiece(Armor.BodyPart.HEAD).getName());
        ArmorSet testPartialCustomSet = itemFactory.createPartialCustomArmorSet(testBaseArmorName, "Custom Shirt", shirtBodyParts);
        for (Armor.BodyPart bodyPart : testPartialCustomSet.getActiveBodyParts()) {
            System.out.printf("\t\t- %s: %s\n", bodyPart.name(), testPartialCustomSet.getCustomPiece(bodyPart).toString());
        }

        // Create a full custom armor set
        System.out.printf("\tCreating full custom armor set using \"%s\" base:\n", testBaseArmorSet.getPiece(Armor.BodyPart.HEAD).getName());
        ArmorSet testFullCustomSet = itemFactory.createFullCustomArmorSet(testBaseArmorName, "Full Custom Armor");
        for (Armor.BodyPart bodyPart : testFullCustomSet.getActiveBodyParts()) {
            testFullCustomSet.getCustomPiece(bodyPart).setCustomTradeValue(1000);
            testFullCustomSet.getCustomPiece(bodyPart).setCustomWeight(5000);
            System.out.printf("\t\t- %s: %s\n", bodyPart.name(), testFullCustomSet.getCustomPiece(bodyPart).toString());
        }
    }

    private static void testToolFactory(ItemFactory itemFactory) {

    }

    private static void testAmmoFactory(ItemFactory itemFactory) {

    }

    private static void testAidFactory(ItemFactory itemFactory) {

    }

    private static void testOtherItemFactory(ItemFactory itemFactory) {

    }

}
