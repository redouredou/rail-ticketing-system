package org.example.services;

import org.example.utils.Utils;
import org.example.enums.Station;
import org.example.model.Trip;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TripServicesImpl implements TripServices{
    @Override
    public Trip updateTrip(Trip trip) {
        Set<Integer> zoneFrom = Utils.checkZoneStation(Station.getStationName(trip.getStationStart()));
        Set<Integer> zoneTo = Utils.checkZoneStation(Station.getStationName(trip.getStationEnd()));

        Map<String, Double> costsByTravel = Utils.getCostByTravel(zoneFrom, zoneTo);

        Optional<Double> minimalCostOpt = costsByTravel.values().stream().min(Double::compare);

        double minimalCost = 0.0;
        if(minimalCostOpt.isPresent()){
            minimalCost = minimalCostOpt.get();
        }

        Stream<String> keyStream1 = Utils.keys(costsByTravel, minimalCost);

        List<String[]> stationFromToList = keyStream1
                .map(stationFromTo -> stationFromTo.split(" "))
                .collect(Collectors.toList());

        List<String[]> stationFromToSame = stationFromToList.stream().filter(elt -> elt[0].equals(elt[1])).collect(Collectors.toList());

        String[] stationFromTo;
        if(!stationFromToSame.isEmpty()){
            stationFromTo = stationFromToSame.get(0);
        }else{
            stationFromTo = stationFromToList.get(0);
        }

        return new Trip.Builder(trip.getStationStart(), trip.getStationEnd(), trip.getStartedJourneyAt())
                .withCostInCents(minimalCost * 100)
                .withZoneFrom(Integer.valueOf(stationFromTo[0]))
                .withZoneTo(Integer.valueOf(stationFromTo[1]))
                .build();
    }
}
