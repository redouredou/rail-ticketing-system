package org.example.services;

import org.example.enums.Zone;
import org.example.model.Price;
import org.example.model.TravelZone;
import org.example.utils.Utils;
import org.example.enums.Station;
import org.example.model.Trip;
import java.util.*;
import java.util.stream.Collectors;

public class TripServicesImpl implements TripServices{
    @Override
    public Trip updateTrip(Trip trip) {
        Set<Zone> zonesOfStationStart = Utils.getZonesByStation(Station.getStationName(trip.getStationStart()));
        Set<Zone> zonesOfStationEnd = Utils.getZonesByStation(Station.getStationName(trip.getStationEnd()));

        Map<TravelZone, Price> costsByTravel = Utils.getCostByTravel(zonesOfStationStart, zonesOfStationEnd);
        List<TravelZone> travelZones = null;
        Optional<TravelZone> cheapestTZ = travelZones.stream().min(Comparator.comparing(TravelZone::getPrice));

//        Optional<Double> minimalCostOpt = costsByTravel.values().stream().min(Double::compare);
        Price minimalCost = travelZones.stream()
                .map(TravelZone::getPrice)
                .min(Comparator.naturalOrder())
                .orElse(Price.Free);

//        double minimalCost = 0.0;
//        if(minimalCostOpt.isPresent()){
//            minimalCost = minimalCostOpt.get();
//        }

        List<TravelZone> travelZoneList = Utils.keys(costsByTravel, minimalCost).collect(Collectors.toList());

        TravelZone travelZoneForNewTrip = getTravelZoneForNewTrip(travelZoneList);


        return new Trip.Builder(trip.getStationStart(), trip.getStationEnd(), trip.getStartedJourneyAt())
                .withCostInCents(minimalCost.convertEuroToCents())
                .withZoneFrom(travelZoneForNewTrip.getZoneStart())
                .withZoneTo(travelZoneForNewTrip.getZoneEnd())
                .build();
    }

    public TravelZone getTravelZoneForNewTrip(List<TravelZone> travelZoneList) {
        return travelZoneList.stream()
                .filter(TravelZone::startAndFinishInSameZone)
                .findFirst()
                .orElse(travelZoneList.get(0));
    }
//        TravelZone travelZoneForNewTrip = null;
//        Optional<TravelZone> travelZoneForNewTripOpt;
//
//        if(travelZoneList.toArray().length > 1){
//            travelZoneForNewTripOpt = travelZoneList
//                    .stream()
//                    .filter(travelZone -> travelZone.getZoneStart().equals(travelZone.getZoneEnd()))
//                    .findFirst();
//        }else{
//            travelZoneForNewTripOpt = travelZoneList
//                    .stream()
//                    .findFirst();
//        }
//
//        if(travelZoneForNewTripOpt.isPresent()){
//            travelZoneForNewTrip = travelZoneForNewTripOpt.get();
//        }
//
//        return travelZoneForNewTrip;
//    }
}
