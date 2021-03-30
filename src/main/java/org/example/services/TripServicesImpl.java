package org.example.services;

import org.example.Utils;
import org.example.enums.Station;
import org.example.enums.Zone;
import org.example.model.Trip;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TripServicesImpl implements TripServices{
    @Override
    public Trip updateTrip(Trip trip) {
        Set<Integer> zoneFrom = Utils.checkZoneStation(Station.getStationName(trip.getStationStart()));
        Set<Integer> zoneTo = Utils.checkZoneStation(Station.getStationName(trip.getStationEnd()));


        HashMap<String, Double> costsWithStations = new HashMap();

        zoneFrom.forEach( zoneFromElt -> {
                    zoneTo.forEach( zoneToElt -> {
                        boolean isFromZoneOneOrTwoToZoneOneOrTwo =  (zoneFromElt.equals(Zone.ONE.getZoneNumber())
                                || zoneFromElt.equals(Zone.TWO.getZoneNumber()))
                                && (zoneToElt.equals(Zone.ONE.getZoneNumber())
                                || zoneToElt.equals(Zone.TWO.getZoneNumber()));

                        boolean isFromZoneThreeOrFourToZoneThreeOrFour = (zoneFromElt.equals(Zone.THREE.getZoneNumber())
                                || zoneFromElt.equals(Zone.FOUR.getZoneNumber()))
                                && (zoneToElt.equals(Zone.THREE.getZoneNumber())
                                || zoneToElt.equals(Zone.FOUR.getZoneNumber()));

                        boolean isFromZoneThreeToZoneOneOrTwo = zoneFromElt.equals(Zone.THREE.getZoneNumber())
                                && (zoneToElt.equals(Zone.ONE.getZoneNumber())
                                || zoneToElt.equals(Zone.TWO.getZoneNumber()));

                        boolean isFromZoneFourToZoneOneOrTwo = zoneFromElt.equals(Zone.FOUR.getZoneNumber())
                                && (zoneToElt.equals(Zone.ONE.getZoneNumber())
                                || zoneToElt.equals(Zone.TWO.getZoneNumber()));

                        boolean isFromZoneOneOrTwoToZoneThree = (zoneFromElt.equals(Zone.ONE.getZoneNumber())
                                || zoneFromElt.equals(Zone.TWO.getZoneNumber()))
                                && zoneToElt.equals(Zone.THREE.getZoneNumber());

                        boolean isFromZoneOneOrTwoToZoneFour = (zoneFromElt.equals(Zone.ONE.getZoneNumber())
                                || zoneFromElt.equals(Zone.TWO.getZoneNumber()))
                                && zoneToElt.equals(Zone.FOUR.getZoneNumber());



                        if(isFromZoneOneOrTwoToZoneOneOrTwo)
                        {
                            costsWithStations.put(zoneFromElt+" "+zoneToElt, 2.40);
                        }
                        if(isFromZoneThreeOrFourToZoneThreeOrFour)
                        {
                            costsWithStations.put(zoneFromElt+" "+zoneToElt, 2.00);
                        }
                        if(isFromZoneThreeToZoneOneOrTwo)
                        {
                            costsWithStations.put(zoneFromElt+" "+zoneToElt, 2.80);
                        }
                        if(isFromZoneFourToZoneOneOrTwo)
                        {
                            costsWithStations.put(zoneFromElt+" "+zoneToElt, 3.00);
                        }
                        if(isFromZoneOneOrTwoToZoneThree)
                        {
                            costsWithStations.put(zoneFromElt+" "+zoneToElt, 2.80);
                        }
                        if(isFromZoneOneOrTwoToZoneFour)
                        {
                            costsWithStations.put(zoneFromElt+" "+zoneToElt, 3.00);
                        }

                    });
                }
        );

        Double minimalCost = costsWithStations.values().stream().min(Double::compare).get();

        Stream<String> keyStream1 = Utils.keys(costsWithStations, minimalCost);
        List<String[]> stationFromToList = keyStream1
                .map(stationFromTo -> stationFromTo.split(" ")).collect(Collectors.toList());

        List<String[]> stationFromToSame = stationFromToList.stream().filter(elt -> elt[0].equals(elt[1])).collect(Collectors.toList());

        String[] stationFromTo;
        if(stationFromToSame.size() != 0){
            stationFromTo = stationFromToSame.get(0);
        }else{
            stationFromTo = stationFromToList.get(0);
        }

        Trip newTrip = new Trip.Builder(trip.getStationStart(), trip.getStationEnd(), trip.getStartedJourneyAt())
                    .withCostInCents(minimalCost * 100)
                    .withZoneFrom(Integer.valueOf(stationFromTo[0]))
                    .withZoneTo(Integer.valueOf(stationFromTo[1]))
                    .build();

        return newTrip;
    }
}
