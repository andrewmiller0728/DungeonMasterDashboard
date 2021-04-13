package com.dungeonmaster.dashboard.equipment;

import com.dungeonmaster.dashboard.character.Character;

public class EquippedWeapon {

    private Character character;
    private Weapon weapon;

    public EquippedWeapon(Character character) {
        this.character = character;
        this.weapon = null;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Weapon swapWeapon(Weapon newWeapon) {
        // TODO: Character must have current weapon in inventory
        // TODO: If weapon consumes ammo, character must have ammo to equip
        // TODO: If weapon is a martial weapon, character must have skill
        Weapon temp = weapon;
        weapon = newWeapon;
        return temp;
    }

    public int getDamage() {
        if (weapon != null) {
            return weapon.getHitDice().rollDice();
        }
        else {
            return 0;
        }
    }

}
