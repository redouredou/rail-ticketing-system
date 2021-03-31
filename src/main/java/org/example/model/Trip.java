package org.example.model;

import org.example.enums.Zone;

public class Trip {
    private final String stationStart;
    private final String stationEnd;
    private final int startedJourneyAt;
    private final int costInCents;
    private final Zone zoneFrom;
    private final Zone zoneTo;

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

    public Zone getZoneFrom() {
        return zoneFrom;
    }

    public Zone getZoneTo() {
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

    public static class Builder {

        private final String stationStart;
        private final String stationEnd;
        private final int startedJourneyAt;
        private int costInCents;
        private Zone zoneFrom;
        private Zone zoneTo;

        public Builder(String stationStart, String stationEnd, int startedJourneyAt) {
            this.stationStart = stationStart;
            this.stationEnd = stationEnd;
            this.startedJourneyAt = startedJourneyAt;
        }

        public Builder withCostInCents(int costInCents) {
            this.costInCents = costInCents;
            return this;
        }

        public Builder withZoneFrom(Zone zoneFrom) {
            this.zoneFrom = zoneFrom;
            return this;
        }

        public Builder withZoneTo(Zone zoneTo) {
            this.zoneTo = zoneTo;
            return this;
        }

        public Trip build() {
            return new Trip(this);
        }

    }
}

