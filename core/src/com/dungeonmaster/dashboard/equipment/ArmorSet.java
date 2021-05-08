package com.dungeonmaster.dashboard.equipment;

import java.util.ArrayList;

public class ArmorSet {

    private ArrayList<Armor> set;
    private ArrayList<CustomArmor> customSet;

    public ArmorSet() {
        set = new ArrayList<>();
        customSet = new ArrayList<>();
    }

    // add armor piece to set
    public boolean addPiece(Armor newPiece) {
        if (isBodyPartEmpty(newPiece.getBodyPart())) {
            set.add(newPiece);
            return true;
        }
        return false;
    }

    public boolean addPiece(CustomArmor newPiece) {
        if (isBodyPartEmpty(newPiece.getBaseArmor().getBodyPart())) {
            customSet.add(newPiece);
            return true;
        }
        return false;
    }

    // remove armor piece from set
    public Armor removePiece(Armor armorPiece) {
        if (set.contains(armorPiece)) {
            set.remove(armorPiece);
            return armorPiece;
        }
        return null;
    }

    public CustomArmor removeCustomPiece(CustomArmor armorPiece) {
        if (customSet.contains(armorPiece)) {
            customSet.remove(armorPiece);
            return armorPiece;
        }
        return null;
    }

    // remove armor piece from body part
    public Armor removePiece(Armor.BodyPart bodyPart) {
        for (Armor setPiece : set) {
            if (setPiece.getBodyPart() == bodyPart) {
                set.remove(setPiece);
                return setPiece;
            }
        }
        return null;
    }

    public CustomArmor removeCustomPiece(Armor.BodyPart bodyPart) {
        for (CustomArmor setPiece : customSet) {
            if (setPiece.getBaseArmor().getBodyPart() == bodyPart) {
                customSet.remove(setPiece);
                return setPiece;
            }
        }
        return null;
    }

    // get armor piece by body part
    public Armor getPiece(Armor.BodyPart bodyPart) {
        for (Armor setPiece : set) {
            if (setPiece.getBodyPart() == bodyPart) {
                return setPiece;
            }
        }
        return null;
    }

    public CustomArmor getCustomPiece(Armor.BodyPart bodyPart) {
        for (CustomArmor setPiece : customSet) {
            if (setPiece.getBaseArmor().getBodyPart() == bodyPart) {
                return setPiece;
            }
        }
        return null;
    }

    public boolean isBodyPartEmpty(Armor.BodyPart bodyPart) {
        for (Armor setPiece : set) {
            if (setPiece.getBodyPart() == bodyPart) {
                return false;
            }
        }
        for (CustomArmor setPiece : customSet) {
            if (setPiece.getBaseArmor().getBodyPart() == bodyPart) {
                return false;
            }
        }
        return true;
    }

    public Armor.BodyPart[] getActiveBodyParts() {
        Armor.BodyPart[] activeBodyParts = new Armor.BodyPart[set.size() + customSet.size()];
        int i = 0;
        for (Armor.BodyPart bodyPart : Armor.BodyPart.values()) {
            if (!isBodyPartEmpty(bodyPart)) {
                activeBodyParts[i] = bodyPart;
                i++;
            }
        }
        return activeBodyParts;
    }

}
