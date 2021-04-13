package com.dungeonmaster.dashboard.equipment;

import java.util.ArrayList;

public class Inventory {

    private static int nextID = 0;
    private int id;

    private ArrayList<Weapon> weaponList;
    private ArrayList<Armor> armorList;
    private ArrayList<Tool> toolList;
    private ArrayList<Ammo> ammoList;
    private ArrayList<Aid> aidList;
    private ArrayList<Item> miscItemList;

    public Inventory() {
        id = nextID;
        nextID++;

        weaponList = new ArrayList<>();
        armorList = new ArrayList<>();
        toolList = new ArrayList<>();
        ammoList = new ArrayList<>();
        aidList = new ArrayList<>();
        miscItemList = new ArrayList<>();
    }

    public void add(Item newItem) {
        if (newItem.getClass().equals(Weapon.class)) {
            weaponList.add((Weapon) newItem);
        }
        else if (newItem.getClass().equals(Armor.class)) {
            armorList.add((Armor) newItem);
        }
        else if (newItem.getClass().equals(Tool.class)) {
            toolList.add((Tool) newItem);
        }
        else if (newItem.getClass().equals(Ammo.class)) {
            ammoList.add((Ammo) newItem);
        }
        else if (newItem.getClass().equals(Aid.class)) {
            aidList.add((Aid) newItem);
        }
        else {
            miscItemList.add(newItem);
        }
    }

    public void remove(Item item) {
        if (item.getClass().equals(Weapon.class)) {
            weaponList.remove(item);
        }
        else if (item.getClass().equals(Armor.class)) {
            armorList.remove(item);
        }
        else if (item.getClass().equals(Tool.class)) {
            toolList.remove(item);
        }
        else if (item.getClass().equals(Ammo.class)) {
            ammoList.remove(item);
        }
        else if (item.getClass().equals(Aid.class)) {
            aidList.remove(item);
        }
        else {
            miscItemList.remove(item);
        }
    }

    public void clear() {
        weaponList.clear();
        armorList.clear();
        toolList.clear();
        ammoList.clear();
        aidList.clear();
        miscItemList.clear();
    }

    public boolean contains(Item item) {
        if (item.getClass().equals(Weapon.class)) {
            return weaponList.contains(item);
        }
        else if (item.getClass().equals(Armor.class)) {
            return armorList.contains(item);
        }
        else if (item.getClass().equals(Tool.class)) {
            return toolList.contains(item);
        }
        else if (item.getClass().equals(Ammo.class)) {
            return ammoList.contains(item);
        }
        else if (item.getClass().equals(Aid.class)) {
            return aidList.contains(item);
        }
        else {
            return miscItemList.contains(item);
        }
    }

    public int getTotalTradeValue() {
        ArrayList[] lists = {weaponList, armorList, toolList, ammoList, aidList, miscItemList};
        int totalTradeValue = 0;
        for (ArrayList list : lists) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getClass().equals(Item.class)) { // TODO: I think this is how this works
                    Item temp = (Item) list.get(i);
                    totalTradeValue += temp.getTradeValue();
                }
            }
        }
        return totalTradeValue;
    }

    public int getTotalAmmo() {
        int totalAmmo = 0;
        for (Ammo unit : ammoList) {
            totalAmmo += unit.getQuantity();
        }
        return totalAmmo;
    }

    public int getTotalWeight() {
        ArrayList[] lists = {weaponList, armorList, toolList, ammoList, aidList, miscItemList};
        int totalWeight = 0;
        for (ArrayList list : lists) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getClass().equals(Item.class)) { // TODO: I think this is how this works
                    Item temp = (Item) list.get(i);
                    totalWeight += temp.getWeight();
                }
            }
        }
        return totalWeight;
    }

    public int getID() {
        return id;
    }

    //region Methods to get Item ArrayLists
    public ArrayList<Weapon> getWeaponList() {
        return weaponList;
    }

    public ArrayList<Armor> getArmorList() {
        return armorList;
    }

    public ArrayList<Tool> getToolList() {
        return toolList;
    }

    public ArrayList<Ammo> getAmmoList() {
        return ammoList;
    }

    public ArrayList<Aid> getAidList() {
        return aidList;
    }

    public ArrayList<Item> getMiscItemList() {
        return miscItemList;
    }
    //endregion

    //region Methods to set Item ArrayLists
    public void setWeaponList(ArrayList<Weapon> weaponList) {
        this.weaponList = weaponList;
    }

    public void setArmorList(ArrayList<Armor> armorList) {
        this.armorList = armorList;
    }

    public void setToolList(ArrayList<Tool> toolList) {
        this.toolList = toolList;
    }

    public void setAmmoList(ArrayList<Ammo> ammoList) {
        this.ammoList = ammoList;
    }

    public void setAidList(ArrayList<Aid> aidList) {
        this.aidList = aidList;
    }

    public void setMiscItemList(ArrayList<Item> miscItemList) {
        this.miscItemList = miscItemList;
    }
    //endregion

}
