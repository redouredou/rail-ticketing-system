package org.example.outputmodel;

import org.example.inputmodel.Price;

import java.util.*;

public class TravelZone {
    private Zone zoneStart;
    private Zone zoneEnd;

    public TravelZone(Zone zoneStart, Zone zoneEnd){
        this.zoneStart = zoneStart;
        this.zoneEnd = zoneEnd;
    }

    public Zone getZoneStart() {
        return zoneStart;
    }

    public void setZoneStart(Zone zoneStart) {
        this.zoneStart = zoneStart;
    }

    public Zone getZoneEnd() {
        return zoneEnd;
    }

    public void setZoneEnd(Zone zoneEnd) {
        this.zoneEnd = zoneEnd;
    }

    public Price getPrice() {
        return TravelPrice.getTravelPriceMap().get(this);
    }

    public boolean startAndFinishInSameZone() {
        return zoneStart.equals(zoneEnd);
    }

    public static List<TravelZone> getTravelsZoneByStations(Station stationStart, Station stationEnd){
        List<TravelZone> travelsByZone = new ArrayList<>();
        ZoneStationMapping.getZonesByStation(stationStart).forEach(zoneStart ->
            ZoneStationMapping.getZonesByStation(stationEnd).forEach(zoneEnd -> travelsByZone.add(new TravelZone(zoneStart, zoneEnd)))
        );
        return travelsByZone;
    }

    @Override
    public String toString() {
        return "TravelZone{" +
                "zoneStart=" + zoneStart +
                ", zoneEnd=" + zoneEnd +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelZone that = (TravelZone) o;
        return zoneStart == that.zoneStart && zoneEnd == that.zoneEnd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(zoneStart, zoneEnd);
    }
}
