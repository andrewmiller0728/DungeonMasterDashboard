package com.dungeonmaster.dashboard;

import java.util.ArrayList;

public class ZoneList {

    private static ArrayList<Zone> zones = new ArrayList<>();

    public ZoneList() {
        // This space has been intentionally left blank.
    }

    public int getSize() {
        return zones.size();
    }

    public void addZone(Zone zone) {
        zones.add(zone);
    }

    public Zone getZone(int index) {
        return zones.get(index);
    }

}
