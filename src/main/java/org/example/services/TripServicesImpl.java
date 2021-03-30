package org.example.services;

import org.example.enums.Zone;
import org.example.model.TravelZone;
import org.example.utils.Utils;
import org.example.enums.Station;
import org.example.model.Trip;
import java.util.*;
import java.util.stream.Collectors;

public class TripServicesImpl implements TripServices{
    @Override
    public Trip updateTrip(Trip trip) {
        Set<Zone> zonesOfStationStart = Utils.checkZoneStation(Station.getStationName(trip.getStationStart()));
        Set<Zone> zonesOfStationEnd = Utils.checkZoneStation(Station.getStationName(trip.getStationEnd()));

        Map<TravelZone, Double> costsByTravel = Utils.getCostByTravel(zonesOfStationStart, zonesOfStationEnd);

        Optional<Double> minimalCostOpt = costsByTravel.values().stream().min(Double::compare);

        double minimalCost = 0.0;
        if(minimalCostOpt.isPresent()){
            minimalCost = minimalCostOpt.get();
        }

        List<TravelZone> travelZoneList = Utils.keys(costsByTravel, minimalCost).collect(Collectors.toList());

        TravelZone travelZoneForNewTrip = getTravelZoneForNewTrip(travelZoneList);


        return new Trip.Builder(trip.getStationStart(), trip.getStationEnd(), trip.getStartedJourneyAt())
                .withCostInCents(minimalCost * 100)
                .withZoneFrom(travelZoneForNewTrip.getZoneStart())
                .withZoneTo(travelZoneForNewTrip.getZoneEnd())
                .build();
    }

    public TravelZone getTravelZoneForNewTrip(List<TravelZone> travelZoneList){
        TravelZone travelZoneForNewTrip = null;
        Optional<TravelZone> travelZoneForNewTripOpt;

        if(travelZoneList.toArray().length > 1){
            travelZoneForNewTripOpt = travelZoneList
                    .stream()
                    .filter(travelZone -> travelZone.getZoneStart().equals(travelZone.getZoneEnd()))
                    .findFirst();
        }else{
            travelZoneForNewTripOpt = travelZoneList
                    .stream()
                    .findFirst();
        }

        if(travelZoneForNewTripOpt.isPresent()){
            travelZoneForNewTrip = travelZoneForNewTripOpt.get();
        }

        return travelZoneForNewTrip;
    }
}
