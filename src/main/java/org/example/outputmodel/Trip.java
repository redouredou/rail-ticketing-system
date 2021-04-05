package org.example.outputmodel;
import java.util.*;

public class Trip {
    private final String stationStart;
    private final String stationEnd;
    private final int startedJourneyAt;
    private final int costInCents;
    private final int zoneFrom;
    private final int zoneTo;

    private Trip(Builder builder){
        this.stationStart = builder.stationStart;
        this.stationEnd = builder.stationEnd;
        this.startedJourneyAt = builder.startedJourneyAt;
        this.costInCents = builder.costInCents;
        this.zoneFrom = builder.zoneFrom;
        this.zoneTo = builder.zoneTo;
    }

    public String getStationStart() {
        return stationStart;
    }

    public String getStationEnd() {
        return stationEnd;
    }

    public int getStartedJourneyAt() {
        return startedJourneyAt;
    }

    public int getCostInCents() {
        return costInCents;
    }

    public int getZoneFrom() {
        return zoneFrom;
    }

    public int getZoneTo() {
        return zoneTo;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "stationStart='" + stationStart + '\'' +
                ", stationEnd='" + stationEnd + '\'' +
                ", startedJourneyAt=" + startedJourneyAt +
                ", costInCents=" + costInCents +
                ", zoneFrom=" + zoneFrom +
                ", zoneTo=" + zoneTo +
                '}';
    }

    public static Trip updateTrip(Trip trip) {

        List<TravelZone> travelsZone = TravelZone.getTravelsZoneByStations(Station.getStationByName(trip.stationStart), Station.getStationByName(trip.getStationEnd()));

        TravelZone travelZoneForNewTrip = TravelZone.getShortestTravelZone(travelsZone);

        return new Trip.Builder(trip.getStationStart(), trip.getStationEnd(), trip.getStartedJourneyAt())
                .withCostInCents(travelZoneForNewTrip.getPrice().convertEuroToCents())
                .withZoneFrom(travelZoneForNewTrip.getZoneStart().getZoneNumber())
                .withZoneTo(travelZoneForNewTrip.getZoneEnd().getZoneNumber())
                .build();
    }

    public static class Builder {

        private final String stationStart;
        private final String stationEnd;
        private final int startedJourneyAt;
        private int costInCents;
        private int zoneFrom;
        private int zoneTo;

        public Builder(String stationStart, String stationEnd, int startedJourneyAt) {
            this.stationStart = stationStart;
            this.stationEnd = stationEnd;
            this.startedJourneyAt = startedJourneyAt;
        }

        public Builder withCostInCents(int costInCents) {
            this.costInCents = costInCents;
            return this;
        }

        public Builder withZoneFrom(int zoneFrom) {
            this.zoneFrom = zoneFrom;
            return this;
        }

        public Builder withZoneTo(int zoneTo) {
            this.zoneTo = zoneTo;
            return this;
        }

        public Trip build() {
            return new Trip(this);
        }

    }
}

