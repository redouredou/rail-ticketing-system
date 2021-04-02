package org.example.inputmodel;

import java.util.Objects;

public class Tap implements Comparable<Tap>{

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

    @Override
    public int compareTo(Tap o) {
        return Integer.compare(unixTimestamp, o.unixTimestamp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tap tap = (Tap) o;
        return unixTimestamp == tap.unixTimestamp && customerId == tap.customerId && Objects.equals(station, tap.station);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unixTimestamp, customerId, station);
    }
}
