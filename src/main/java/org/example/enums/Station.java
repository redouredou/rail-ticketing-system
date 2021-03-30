package org.example.enums;

public enum Station {
    A("A"), B("B"), C("C"), D("D"), E("E"), F("F"), G("G"), H("H"), I("I");


    private String stationValue;

    Station(String stationValue) {
        this.stationValue = stationValue;
    }

    public String getStationValue() {
        return this.stationValue;
    }


    public static Station getStationName(String stationName) {
        for (Station station : Station.values()) {
            if (station.getStationValue().equals(stationName)) {
                return station;
            }
        }
        return null;
    }
}
