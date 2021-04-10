package com.dungeonmaster.dashboard;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Zone {

    public enum ZoneLabel {
        LANE_STADIUM, DRILLFIELD, UNIVERSITY_CITY, FOOD_LION, COOKOUT, FOXRIDGE, OTHER
    }

    private static final float UNITS_PER_TILE = 5f;

    private static int nextID = 1;

    private int id;
    private ZoneLabel name;
    private Vector2 worldMapCoords;
    private Vector2 dimensions;
    private ArrayList<Character> characters;

    public Zone(Vector2 worldMapCoords, Vector2 dimensions) {
        id = nextID;
        nextID++;
        name = ZoneLabel.OTHER;
        this.worldMapCoords = worldMapCoords;
        this.dimensions = dimensions;
        this.characters = null;
    }

    public Zone(Vector2 worldMapCoords, Vector2 dimensions, ZoneLabel name) {
        id = nextID;
        nextID++;
        this.name = name;
        this.worldMapCoords = worldMapCoords;
        this.dimensions = dimensions;
        this.characters = null;
    }

    public Zone(Vector2 worldMapCoords, Vector2 dimensions, ZoneLabel name, ArrayList<Character> characters) {
        id = nextID;
        nextID++;
        this.name = name;
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

    public ZoneLabel getName() {
        return name;
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
