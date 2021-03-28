package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tap{
    @JsonProperty("unixTimestamp")
    int unixTimestamp;

    @JsonProperty("customerId")
    int customerId;

    @JsonProperty("station")
    String station;

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

}
