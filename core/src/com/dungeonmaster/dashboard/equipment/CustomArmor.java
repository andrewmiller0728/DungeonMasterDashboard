package com.dungeonmaster.dashboard.equipment;

public class CustomArmor {

    private Armor baseArmor;
    private String customName;
    private int customAC;
    private int customStrengthReq;
    private boolean customStealth;
    private int customWeight;
    private int customTradeValue;

    public CustomArmor(Armor baseArmor, String customName) {
        this.baseArmor = baseArmor;
        this.customName = customName;
        customAC = baseArmor.getAC();
        customStrengthReq = baseArmor.getStrengthReq();
        customStealth = baseArmor.isStealthy();
        customWeight = baseArmor.getWeight();
        customTradeValue = baseArmor.getTradeValue();
    }

    public void setCustomAC(int customAC) {
        this.customAC = customAC;
    }

    public void setCustomStrengthReq(int customStrengthReq) {
        this.customStrengthReq = customStrengthReq;
    }

    public void setCustomStealth(boolean customStealth) {
        this.customStealth = customStealth;
    }

    public void setCustomWeight(int customWeight) {
        this.customWeight = customWeight;
    }

    public void setCustomTradeValue(int customTradeValue) {
        this.customTradeValue = customTradeValue;
    }

    public Armor getBaseArmor() {
        return baseArmor;
    }

    public int getAC() {
        return customAC;
    }

    public int getStrengthReq() {
        return customStrengthReq;
    }

    public boolean isStealthy() {
        return customStealth;
    }

    public int getWeight() {
        return customWeight;
    }

    public int getTradeValue() {
        return customTradeValue;
    }

    @Override
    public String toString() {
        return "CustomArmor{" +
                "baseArmor=" + baseArmor +
                ", customName='" + customName + '\'' +
                ", customAC=" + customAC +
                ", customStrengthReq=" + customStrengthReq +
                ", customStealth=" + customStealth +
                ", customWeight=" + customWeight +
                ", customTradeValue=" + customTradeValue +
                '}';
    }
}
