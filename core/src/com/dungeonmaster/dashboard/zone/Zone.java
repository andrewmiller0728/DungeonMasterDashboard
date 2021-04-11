package com.dungeonmaster.dashboard.zone;

import com.badlogic.gdx.math.Vector2;
import com.dungeonmaster.dashboard.character.Character;

import java.util.ArrayList;

public class Zone {

    public enum ZoneLabel {
        LANE_STADIUM, DRILLFIELD, UNIVERSITY_CITY, FOOD_LION, COOKOUT, FOXRIDGE, DOWNTOWN, OTHER;
    }

    private static final float UNITS_PER_TILE = 5f;

    private static int nextID = 1;

    private int id;
    private ZoneLabel label;
    private Vector2 worldMapCoords;
    private Vector2 dimensions;
    private ArrayList<Character> characters;

    public Zone(Vector2 worldMapCoords, Vector2 dimensions) {
        id = nextID;
        nextID++;
        label = ZoneLabel.OTHER;
        this.worldMapCoords = worldMapCoords;
        this.dimensions = dimensions;
        this.characters = null;
    }

    public Zone(Vector2 worldMapCoords, Vector2 dimensions, ZoneLabel label) {
        id = nextID;
        nextID++;
        this.label = label;
        this.worldMapCoords = worldMapCoords;
        this.dimensions = dimensions;
        this.characters = null;
    }

    public Zone(
            Vector2 worldMapCoords,
            Vector2 dimensions,
            ZoneLabel label,
            ArrayList<Character> characters
    ) {
        id = nextID;
        nextID++;
        this.label = label;
        this.worldMapCoords = worldMapCoords;
        this.dimensions = dimensions;
        this.characters = characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    public int getID() {
        return id;
    }

    public ZoneLabel getLabel() {
        return label;
    }

    public String getName() {
        String[] words = label.toString().toLowerCase().split("_");
        String name = "";
        for (int i = 0; i < words.length; i++) {
            name = name.concat(capitalize(words[i]));
            if (i < words.length - 1) {
                name = name.concat(" ");
            }
        }
        return name;
    }

    private static String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public Vector2 getWorldMapCoords() {
        return worldMapCoords;
    }

    public Vector2 getDimensions() {
        return dimensions;
    }

    public Vector2 getDimensionsInTiles() {
        Vector2 tempVector = dimensions.cpy();
        return tempVector.scl(1f / UNITS_PER_TILE);
    }

}
