package com.dungeonmaster.dashboard.equipment;

import com.badlogic.gdx.math.Vector2;
import com.dungeonmaster.dashboard.Dice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

// TODO: Build items from files on request
public class ItemFactory {

    private static final String BASE_WEAPONS_FILEPATH = "./item-data/weapons-5e-modern.txt";
    private static final String BASE_ARMOR_FILEPATH = "./item-data/armor-5e.txt";

    private final ArrayList<Weapon> baseWeapons;
    private final ArrayList<ArmorSet> baseArmorSets;
    private Armor baseShield;

    public ItemFactory() {
        baseWeapons = loadBaseWeapons();
        baseArmorSets = loadBaseArmorSets();
    }


    //region /* WEAPONS */
    // Returns a reference to the base weapon object
    public Weapon getBaseWeapon(String baseWeaponName) {
        for (Weapon weapon : baseWeapons) {
            if (weapon.getName().equals(baseWeaponName)) {
                return weapon;
            }
        }
        return null;
    }

    // Returns a custom weapon based on a base weapon
    public CustomWeapon createCustomWeapon(String baseWeaponName, String customWeaponName) {
        for (Weapon weapon : baseWeapons) {
            if (weapon.getName().equals(baseWeaponName)) {
                return new CustomWeapon(weapon, customWeaponName);
            }
        }
        return null;
    }

    // Get the list of names of the base weapons
    public String[] getBaseWeaponNames() {
        String[] baseWeaponNames = new String[baseWeapons.size()];
        for (int i = 0; i < baseWeapons.size(); i++) {
            baseWeaponNames[i] = baseWeapons.get(i).getName();
        }
        return baseWeaponNames;
    }

    // Load base weapon items from file
    private ArrayList<Weapon> loadBaseWeapons() {
        ArrayList<Weapon> tempBaseWeapons = new ArrayList<>();

        String line = null;
        BufferedReader bufferedReader = null;
        try {
            File baseWeaponsFile = new File(BASE_WEAPONS_FILEPATH);
            FileReader fileReader = new FileReader(baseWeaponsFile, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith("//")) {
                    String[] lineElements = line.split(", ");

                    Item baseItem = new Item(
                            lineElements[1],
                            Integer.parseInt(lineElements[8]),
                            Integer.parseInt(lineElements[7]),
                            true,
                            false
                    );

                    Dice baseDice = new Dice(
                            Integer.parseInt(lineElements[5].split("d")[0]),
                            Integer.parseInt(lineElements[5].split("d")[1])
                    );

                    Weapon.Category baseWeaponCategory = Weapon.Category.SIMPLE;
                    if (lineElements[2].equals("martial")) {
                        baseWeaponCategory = Weapon.Category.MARTIAL;
                    }

                    Weapon.WeaponType baseWeaponType = Weapon.WeaponType.MELEE;
                    if (lineElements[3].equals("ranged")) {
                        baseWeaponType = Weapon.WeaponType.RANGED;
                    }

                    Weapon.Size baseWeaponSize = Weapon.Size.SMALL;
                    if (lineElements[4].equals("large")) {
                        baseWeaponSize = Weapon.Size.LARGE;
                    }

                    Ammo.AmmoType baseAmmoType = Ammo.AmmoType.NONE;
                    switch (lineElements[9]) {
                        case "handgun":
                            baseAmmoType = Ammo.AmmoType.HANDGUN;
                            break;
                        case "shotgun":
                            baseAmmoType = Ammo.AmmoType.SHOTGUN;
                            break;
                        case "rifle":
                            baseAmmoType = Ammo.AmmoType.RIFLE;
                            break;
                        case "explosive":
                            baseAmmoType = Ammo.AmmoType.EXPLOSIVE;
                            break;
                    }

                    boolean usesAmmo = true;
                    if (baseAmmoType == Ammo.AmmoType.NONE) {
                        usesAmmo = false;
                    }

                    Vector2 baseRange = new Vector2(
                            Integer.parseInt(lineElements[6].split("/")[0]),
                            Integer.parseInt(lineElements[6].split("/")[1])
                    );

                    boolean isTwoHanded = true;
                    if (lineElements[10].equals("false")) {
                        isTwoHanded = false;
                    }

                    tempBaseWeapons.add(
                            new Weapon(
                                    baseItem,
                                    baseDice,
                                    baseWeaponCategory,
                                    baseWeaponType,
                                    baseWeaponSize,
                                    usesAmmo,
                                    baseAmmoType,
                                    baseRange,
                                    isTwoHanded
                            )
                    );
                }
            }
            bufferedReader.close();
        }
        catch (IOException e) {
            System.out.println("Error occurred while parsing base weapons file.");
            e.printStackTrace();
        }

        return tempBaseWeapons;
    }
    //endregion


    // ARMOR //

    public ArmorSet createFullCustomArmorSet(String baseArmorName, String customName) {
        ArmorSet baseArmorSet = getBaseArmorSet(baseArmorName);
        ArmorSet fullCustomArmorSet = new ArmorSet();
        for (Armor.BodyPart bodyPart : Armor.BodyPart.values()) {
            if (!baseArmorSet.isBodyPartEmpty(bodyPart)) {
                fullCustomArmorSet.addPiece(new CustomArmor(baseArmorSet.getPiece(bodyPart), customName));
            }
        }
        return fullCustomArmorSet;
    }

    public ArmorSet createPartialCustomArmorSet(String baseArmorName, String customName, Armor.BodyPart[] bodyParts) {
        ArmorSet baseArmorSet = getBaseArmorSet(baseArmorName);
        ArmorSet partialCustomArmorSet = new ArmorSet();
        for (Armor.BodyPart bodyPart : bodyParts) {
            partialCustomArmorSet.addPiece(new CustomArmor(baseArmorSet.getPiece(bodyPart), customName));
        }
        return partialCustomArmorSet;
    }

    public CustomArmor createCustomArmorPiece(String baseArmorName, String customName, Armor.BodyPart bodyPart) {
        return new CustomArmor(getBaseArmorSet(baseArmorName).getPiece(bodyPart), customName);
    }

    public ArmorSet getBaseArmorSet(String baseArmorName) {
        for (ArmorSet set : baseArmorSets) {
            if (set.getPiece(Armor.BodyPart.HEAD).getName().equals(baseArmorName)) {
                return set;
            }
        }
        return null;
    }

    public String[] getBaseArmorNames() {
        String[] baseArmorNames = new String[baseArmorSets.size()];
        for (int i = 0; i < baseArmorSets.size(); i++) {
            baseArmorNames[i] = baseArmorSets.get(i).getPiece(Armor.BodyPart.HEAD).getName();
        }
        return baseArmorNames;
    }

    private ArrayList<ArmorSet> loadBaseArmorSets() {
        ArrayList<ArmorSet> tempBaseArmorSets = new ArrayList<>();

        String line = null;
        BufferedReader bufferedReader = null;
        try {
            File baseWeaponsFile = new File(BASE_ARMOR_FILEPATH);
            FileReader fileReader = new FileReader(baseWeaponsFile, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith("//")) {
                    String[] lineElements = line.split(", ");

                    Item baseItem = new Item(
                            lineElements[1],
                            Integer.parseInt(lineElements[6]),
                            Integer.parseInt(lineElements[5]),
                            true,
                            false
                    );

                    Armor.Size tempBaseArmorSize = Armor.Size.OTHER;
                    switch (lineElements[2]) {
                        case "light":
                            tempBaseArmorSize = Armor.Size.LIGHT;
                            break;
                        case "medium":
                            tempBaseArmorSize = Armor.Size.MEDIUM;
                            break;
                        case "heavy":
                            tempBaseArmorSize = Armor.Size.HEAVY;
                            break;
                        case "shield":
                            tempBaseArmorSize = Armor.Size.SHIELD;
                            break;
                    }

                    // Create one of each body part (w/o shield or other) in an armor set
                    if (tempBaseArmorSize == Armor.Size.SHIELD) {
                        baseShield = new Armor(
                                baseItem,
                                Integer.parseInt(lineElements[3]),
                                lineElements[4].equals("true"),
                                Armor.BodyPart.SHIELD,
                                tempBaseArmorSize,
                                Integer.parseInt(lineElements[7])
                        );
                    }
                    else if (tempBaseArmorSize != Armor.Size.OTHER) {
                        ArmorSet tempBaseArmorSet = new ArmorSet();
                        for (Armor.BodyPart bodyPart : Armor.BodyPart.values()) {
                            if (bodyPart != Armor.BodyPart.SHIELD && bodyPart != Armor.BodyPart.OTHER) {
                                Armor tempBaseArmorPiece = new Armor(
                                        baseItem,
                                        Integer.parseInt(lineElements[3]),
                                        lineElements[4].equals("true"),
                                        bodyPart,
                                        tempBaseArmorSize,
                                        Integer.parseInt(lineElements[7])
                                );
                                tempBaseArmorSet.addPiece(tempBaseArmorPiece);
                            }
                        }
                        tempBaseArmorSets.add(tempBaseArmorSet);
                    }
                }
            }
            bufferedReader.close();
        }
        catch (IOException e) {
            System.out.println("Error occurred while parsing base armor file.");
            e.printStackTrace();
        }

        return tempBaseArmorSets;
    }


    // TOOLS //

    // Create Tool
    //   - lots of different tool options in 5e
    public Tool createTool() {
        return null;
    }


    // AMMO //

    // Create Ammo
    //   - handgun, rifle, shotgun, explosive
    public Ammo createHandgunAmmo() {
        return null;
    }

    public Ammo createRifleAmmo() {
        return null;
    }

    public Ammo createShotgunAmmo() {
        return null;
    }

    public Ammo createExplosiveAmmo() {
        return null;
    }


    // AID //

    // Create Aid
    //   - single use
    //   - multi use
    public Aid createSingleUseAid() {
        return null;
    }

    public Aid createMultiUseAid() {
        return null;
    }


    // OTHER ITEMS //

    // Create Other item
    public Item createItem() {
        return null;
    }

}
