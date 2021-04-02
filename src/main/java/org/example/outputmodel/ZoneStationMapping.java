package org.example.outputmodel;

import java.util.*;

public class ZoneStationMapping {

    private ZoneStationMapping() {
        throw new IllegalStateException("ZoneStationMapping class");
    }

    private static EnumMap<Station, Set<Zone>> stationZoneMap = new EnumMap<>(Station.class);

    static {
        stationZoneMap.put(Station.A, new HashSet<>(Arrays.asList(Zone.ONE)));
        stationZoneMap.put(Station.B, new HashSet<>(Arrays.asList(Zone.ONE)));
        stationZoneMap.put(Station.C, new HashSet<>(Arrays.asList(Zone.TWO, Zone.THREE)));
        stationZoneMap.put(Station.D, new HashSet<>(Arrays.asList(Zone.TWO)));
        stationZoneMap.put(Station.E, new HashSet<>(Arrays.asList(Zone.TWO, Zone.THREE)));
        stationZoneMap.put(Station.F, new HashSet<>(Arrays.asList(Zone.THREE, Zone.FOUR)));
        stationZoneMap.put(Station.G, new HashSet<>(Arrays.asList(Zone.FOUR)));
        stationZoneMap.put(Station.H, new HashSet<>(Arrays.asList(Zone.FOUR)));
        stationZoneMap.put(Station.I, new HashSet<>(Arrays.asList(Zone.FOUR)));

    }

    public static Set<Zone> getZonesByStation(Station station){
        return stationZoneMap.get(station);

    }

}
