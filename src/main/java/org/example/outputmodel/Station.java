package org.example.outputmodel;

import java.util.Arrays;

public enum Station {
    A("A"), B("B"), C("C"), D("D"), E("E"), F("F"), G("G"), H("H"), I("I");


    private final String stationValue;

    Station(String stationValue) {
        this.stationValue = stationValue;
    }

    public String getStationValue() {
        return this.stationValue;
    }


    public static Station getStationByName(String stationName) {
            return Arrays.stream(Station.values())
                    .filter(station -> station.name().equals(stationName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("this enum doesn't exist"));
    }

    @Override
    public String toString() {
        return "Station{" +
                "stationValue='" + stationValue + '\'' +
                '}';
    }
}
