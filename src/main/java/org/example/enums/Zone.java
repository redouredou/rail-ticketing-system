package org.example.enums;

public enum Zone {
    ONE(1),TWO(2),THREE(3),FOUR(4);

    final int zoneNumber;
    Zone(int zoneNumber){
        this.zoneNumber = zoneNumber;
    }

    public int getZoneNumber() {
        return this.zoneNumber;
    }
}
