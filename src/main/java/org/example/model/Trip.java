package org.example.model;

public class Trip {
    private final String stationStart;
    private final String stationEnd;
    private final int startedJourneyAt;
    private final double costInCents;
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

    public double getCostInCents() {
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

    public static class Builder {

        private final String stationStart;
        private final String stationEnd;
        private final int startedJourneyAt;
        private double costInCents;
        private int zoneFrom;
        private int zoneTo;

        public Builder(String stationStart, String stationEnd, int startedJourneyAt) {
            if (stationStart == null || stationStart == null) {
                throw new IllegalArgumentException("TripBuilder arguments cannot be null");
            }
            this.stationStart = stationStart;
            this.stationEnd = stationEnd;
            this.startedJourneyAt = startedJourneyAt;
        }

        public Builder withCostInCents(double costInCents) {
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

