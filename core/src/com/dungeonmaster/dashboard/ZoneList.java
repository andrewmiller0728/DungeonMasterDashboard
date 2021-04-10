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

    public Zone getZone(Zone.ZoneLabel name) {
        for (int i = 0; i < zones.size(); i++) {
            if (zones.get(i).getLabel() == (name)) {
                return zones.get(i);
            }
        }
        return null;
    }

    public void printList() {
        System.out.println("Zone List:");
        for (int i = 0; i < zones.size(); i++) {
            System.out.printf(
                    "\tZone %d: \"%s\"",
                    zones.get(i).getID(),
                    zones.get(i).getLabel().toString()
            );
        }
    }

}
