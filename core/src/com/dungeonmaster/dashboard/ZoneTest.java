package com.dungeonmaster.dashboard;

import com.badlogic.gdx.math.Vector2;

public class ZoneTest {

    public static void main(String[] args) {
        Zone zoneA = new Zone(
                new Vector2(100, 200),
                new Vector2(400, 800)
        );
        System.out.println("\n[Test]    Testing Zone A");
        System.out.printf("\t             getName() -> %s\n", zoneA.getName());
        System.out.printf("\t   getWorldMapCoords() -> %s\n", zoneA.getWorldMapCoords().toString());
        System.out.printf("\t       getDimensions() -> %s\n", zoneA.getDimensions().toString());
        System.out.printf("\tgetDimensionsInTiles() -> %s\n", zoneA.getDimensionsInTiles().toString());

        Zone zoneB = new Zone(
                new Vector2(100, 200),
                new Vector2(400, 800),
                "Zone B"
        );
        System.out.println("[Test]    Testing Zone B");
        System.out.printf("\t             getName() -> %s\n", zoneB.getName());
        System.out.printf("\t   getWorldMapCoords() -> %s\n", zoneB.getWorldMapCoords().toString());
        System.out.printf("\t       getDimensions() -> %s\n", zoneB.getDimensions().toString());
        System.out.printf("\tgetDimensionsInTiles() -> %s\n", zoneB.getDimensionsInTiles().toString());
    }

}
