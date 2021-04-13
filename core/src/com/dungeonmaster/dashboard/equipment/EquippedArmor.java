package com.dungeonmaster.dashboard.equipment;

import java.util.ArrayList;
import com.dungeonmaster.dashboard.character.Character;

public class EquippedArmor {

    private Character character;
    private Armor head;
    private Armor leftArm;
    private Armor rightArm;
    private Armor torso;
    private Armor leftLeg;
    private Armor rightLeg;
    private Armor leftFoot;
    private Armor rightFoot;
    private Armor shield;
    private ArrayList<Armor> others;

    public EquippedArmor(Character character) {
        this.character = character;
        head = null;
        leftArm = null;
        rightArm = null;
        torso = null;
        leftLeg = null;
        rightLeg = null;
        leftFoot = null;
        rightFoot = null;
        shield = null;
        others = new ArrayList<>();
    }

    public int getTotalACModifier() {
        int othersAC = 0;
        for (Armor other : others) {
            othersAC += other.getAC();
        }
        return (head.getAC()
                + leftArm.getAC()
                + rightArm.getAC()
                + torso.getAC()
                + leftLeg.getAC()
                + rightLeg.getAC()
                + leftFoot.getAC()
                + rightFoot.getAC()
                + shield.getAC()
                + othersAC)
                / (9 + others.size());
    }

    public Armor swapArmor(Armor newArmor) {
        Armor temp = null;
        if (newArmor.getLocation().equals(Armor.Location.HEAD)) {
            temp = head;
            head = newArmor;
        }
        else if (newArmor.getLocation().equals(Armor.Location.LEFT_ARM)) {
            temp = leftArm;
            leftArm = newArmor;
        }
        else if (newArmor.getLocation().equals(Armor.Location.RIGHT_ARM)) {
            temp = rightArm;
            rightArm = newArmor;
        }
        else if (newArmor.getLocation().equals(Armor.Location.TORSO)) {
            temp = torso;
            torso = newArmor;
        }
        else if (newArmor.getLocation().equals(Armor.Location.LEFT_LEG)) {
            temp = leftLeg;
            leftLeg = newArmor;
        }
        else if (newArmor.getLocation().equals(Armor.Location.RIGHT_LEG)) {
            temp = rightLeg;
            rightLeg = newArmor;
        }
        else if (newArmor.getLocation().equals(Armor.Location.LEFT_FOOT)) {
            temp = leftFoot;
            leftFoot = newArmor;
        }
        else if (newArmor.getLocation().equals(Armor.Location.RIGHT_FOOT)) {
            temp = rightFoot;
            rightFoot = newArmor;
        }
        else if (newArmor.getLocation().equals(Armor.Location.SHIELD)) {
            temp = shield;
            shield = newArmor;
        }
        else {
            others.add(newArmor);
        }
        return temp;
    }

    //region Getters and Setters for body part specific armor
    public Armor getHead() {
        return head;
    }

    public Armor swapHead(Armor newArmor) {
        if (newArmor.getLocation() == Armor.Location.HEAD) {
            Armor temp = this.head;
            this.head = newArmor;
            return temp;
        }
        else {
            return null;
        }
    }

    public Armor getLeftArm() {
        return leftArm;
    }

    public Armor swapLeftArm(Armor newArmor) {
        if (newArmor.getLocation() == Armor.Location.LEFT_ARM) {
            Armor temp = this.leftArm;
            this.leftArm = newArmor;
            return temp;
        }
        else {
            return null;
        }
    }

    public Armor getRightArm() {
        return rightArm;
    }

    public Armor swapRightArm(Armor newArmor) {
        if (newArmor.getLocation() == Armor.Location.RIGHT_ARM) {
            Armor temp = this.rightArm;
            this.rightArm = newArmor;
            return temp;
        }
        else {
            return null;
        }
    }

    public Armor getTorso() {
        return torso;
    }

    public Armor swapTorso(Armor newArmor) {
        if (newArmor.getLocation() == Armor.Location.TORSO) {
            Armor temp = this.torso;
            this.torso = newArmor;
            return temp;
        }
        else {
            return null;
        }
    }

    public Armor getLeftLeg() {
        return leftLeg;
    }

    public Armor setLeftLeg(Armor newArmor) {
        if (newArmor.getLocation() == Armor.Location.LEFT_LEG) {
            Armor temp = this.leftLeg;
            this.leftLeg = newArmor;
            return temp;
        }
        else {
            return null;
        }
    }

    public Armor getRightLeg() {
        return rightLeg;
    }

    public Armor swapRightLeg(Armor newArmor) {
        if (newArmor.getLocation() == Armor.Location.RIGHT_LEG) {
            Armor temp = this.rightLeg;
            this.rightLeg = newArmor;
            return temp;
        }
        else {
            return null;
        }
    }

    public Armor getLeftFoot() {
        return leftFoot;
    }

    public Armor swapLeftFoot(Armor newArmor) {
        if (newArmor.getLocation() == Armor.Location.LEFT_FOOT) {
            Armor temp = this.leftFoot;
            this.leftFoot = newArmor;
            return temp;
        }
        else {
            return null;
        }
    }

    public Armor getRightFoot() {
        return rightFoot;
    }

    public Armor swapRightFoot(Armor newArmor) {
        if (newArmor.getLocation() == Armor.Location.RIGHT_FOOT) {
            Armor temp = this.rightFoot;
            this.rightFoot = newArmor;
            return temp;
        }
        else {
            return null;
        }
    }

    public Armor getShield() {
        return shield;
    }

    public Armor swapShield(Armor newArmor) {
        if (newArmor.getLocation() == Armor.Location.SHIELD) {
            Armor temp = this.shield;
            this.shield = newArmor;
            return temp;
        }
        else {
            return null;
        }
    }
    //endregion

    public boolean equipOtherArmor(Armor newArmor) {
        if (!others.contains(newArmor)) {
            others.add(newArmor);
            return true;
        }
        return false;
    }

    public void removeOtherArmor(Armor armor) {
        others.remove(armor);
    }

}
