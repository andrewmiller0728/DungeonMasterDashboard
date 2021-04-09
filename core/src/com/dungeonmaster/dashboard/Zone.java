package com.dungeonmaster.dashboard;

import com.badlogic.gdx.math.Vector2;

public class Zone {

    private static final float UNITS_PER_TILE = 5f;

    private static int nextID = 1;

    private int id;
    private String name;
    private Vector2 worldMapCoords;
    private Vector2 dimensions;

    public Zone(Vector2 worldMapCoords, Vector2 dimensions) {
        id = nextID;
        nextID++;
        name = String.format("%d", id);
        this.worldMapCoords = worldMapCoords;
        this.dimensions = dimensions;
    }

    public Zone(Vector2 worldMapCoords, Vector2 dimensions, String name) {
        id = nextID;
        nextID++;
        this.name = name;
        this.worldMapCoords = worldMapCoords;
        this.dimensions = dimensions;
    }

    public String getName() {
        return name;
    }

    public Vector2 getWorldMapCoords() {
        return worldMapCoords;
    }

    public Vector2 getDimensions() {
        return dimensions;
    }

    public Vector2 getDimensionsInTiles() {
        return dimensions.scl(1f / UNITS_PER_TILE);
    }

}
