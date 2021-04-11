package com.dungeonmaster.dashboard.equipment;

import java.util.ArrayList;

public class Inventory {

    private static int nextID = 0;
    private int id;

    private ArrayList<Weapon> weapons;
    private ArrayList<Armor> armors;
    private ArrayList<Tool> tools;
    private ArrayList<Ammo> ammo;
    private ArrayList<Aid> aid;
    private ArrayList<Item> miscItems;

    public Inventory() {
        id = nextID;
        nextID++;

        weapons = new ArrayList<>();
        armors = new ArrayList<>();
        tools = new ArrayList<>();
        ammo = new ArrayList<>();
        aid = new ArrayList<>();
        miscItems = new ArrayList<>();
    }

    public boolean addItem(Item newItem) {
        if (newItem.getClass().equals(Weapon.class)) {
            weapons.add((Weapon) newItem);
            return true;
        }
        else if (newItem.getClass().equals(Armor.class)) {
            armors.add((Armor) newItem);
            return true;
        }
        else if (newItem.getClass().equals(Tool.class)) {
            tools.add((Tool) newItem);
            return true;
        }
        else if (newItem.getClass().equals(Ammo.class)) {
            ammo.add((Ammo) newItem);
            return true;
        }
        else if (newItem.getClass().equals(Aid.class)) {
            aid.add((Aid) newItem);
            return true;
        }
        else {
            miscItems.add(newItem);
            return true;
        }
    }

    //region Methods to set Item ArrayLists
    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    public void setArmors(ArrayList<Armor> armors) {
        this.armors = armors;
    }

    public void setTools(ArrayList<Tool> tools) {
        this.tools = tools;
    }

    public void setAmmo(ArrayList<Ammo> ammo) {
        this.ammo = ammo;
    }

    public void setAid(ArrayList<Aid> aid) {
        this.aid = aid;
    }

    public void setMiscItems(ArrayList<Item> miscItems) {
        this.miscItems = miscItems;
    }
    //endregion

    public int getTotalTradeValue() {
        ArrayList[] lists = {weapons, armors, tools, ammo, aid, miscItems};
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
        for (int i = 0; i < ammo.size(); i++) {
            totalAmmo += ammo.get(i).getQuantity();
        }
        return totalAmmo;
    }

    public int getTotalWeight() {
        ArrayList[] lists = {weapons, armors, tools, ammo, aid, miscItems};
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
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public ArrayList<Armor> getArmors() {
        return armors;
    }

    public ArrayList<Tool> getTools() {
        return tools;
    }

    public ArrayList<Ammo> getAmmo() {
        return ammo;
    }

    public ArrayList<Aid> getAid() {
        return aid;
    }

    public ArrayList<Item> getMiscItems() {
        return miscItems;
    }
    //endregion

}
