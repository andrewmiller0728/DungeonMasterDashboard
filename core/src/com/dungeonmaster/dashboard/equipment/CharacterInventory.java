package com.dungeonmaster.dashboard.equipment;

import com.dungeonmaster.dashboard.character.Character;

public class CharacterInventory extends Inventory {

    private Character character;
    private EquippedWeapon equippedWeapon;
    private EquippedArmor equippedArmor;

    public CharacterInventory(Character character) {
        this.character = character;
        equippedWeapon = new EquippedWeapon(character);
        equippedArmor = new EquippedArmor(character);
    }

    public int useEquippedWeapon() {
        if (equippedWeapon.getWeapon() != null) {
            if (equippedWeapon.getWeapon().getWeaponType() == Weapon.Type.RANGED && super.getTotalAmmo() > 0) {
                if (character.getInventory().getTotalAmmo() > 0) {
                    useAmmo();
                }
            }
            else if (super.getTotalAmmo() == 0) {
                return 0;
            }
            return equippedWeapon.getDamage();
        }
        else {
            return 0;
        }
    }

    private boolean useAmmo() {
        for (int i = 0; i < super.getAmmoList().size(); i++) {
            Ammo ammo = super.getAmmoList().get(i);
            if (!ammo.isEmpty()) {
                ammo.consumeAmmo();
                if (ammo.isEmpty()) {
                    super.getAmmoList().remove(i);
                }
                return true;
            }
        }
        return false;
    }

    public boolean useAid(Aid aid) {
        int aidIndex = super.getAidList().indexOf(aid);
        if (aidIndex >= 0) {
            super.getAidList().get(aidIndex).use();
            return true;
        }
        else {
            return false;
        }
    }

    public int getACModifier() {
        return equippedArmor.getTotalACModifier();
    }

    public Weapon swapEquippedWeapon(Weapon newWeapon) {
        return equippedWeapon.swapWeapon(newWeapon);
    }

    public Armor swapEquippedArmor(Armor newArmor) {
        return equippedArmor.swapArmor(newArmor);
    }

    public EquippedWeapon getEquippedWeapon() {
        return equippedWeapon;
    }

}
