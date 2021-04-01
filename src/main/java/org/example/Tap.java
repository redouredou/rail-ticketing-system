package org.example;

public class Tap{

    private int unixTimestamp;

    private int customerId;

    private String station;

    public int getUnixTimestamp() {
        return this.unixTimestamp;
    }

    public void setUnixTimestamp(int unixTimestamp) {
        this.unixTimestamp = unixTimestamp;
    }

    public int getCustomerId() {
        return this.customerId;

    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStation() {
        return this.station;
    }

    public void setStation(String station) {
        this.station = station; }

    @Override
    public String toString() {
        return "Tap{" +
                "unixTimestamp=" + unixTimestamp +
                ", customerId=" + customerId +
                ", station='" + station + '\'' +
                '}';
    }
}
