package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.enums.Station;
import org.example.enums.TravelPrice;
import org.example.enums.Zone;
import org.example.model.RootInput;
import org.example.model.RootOutput;
import org.example.model.TravelZone;
import org.json.JSONException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class Utils {

    private Utils(){
        throw new IllegalArgumentException("Utility class");
    }

    public static RootInput parseJSONFile(String filename) throws JSONException {
        RootInput rootInput = null;
        try {
            ObjectMapper om = new ObjectMapper();
            rootInput = om.readValue(new File(filename), RootInput.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootInput;
    }

    public static void generateOutputFile(RootOutput rootOutput, String outfile) throws JSONException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(outfile), rootOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Zone> checkZoneStation(Station zoneStation){
        Set<Zone> zoneAffiliations = new HashSet<>();
        switch (zoneStation){
            case A:
            case B:
                zoneAffiliations.add(Zone.ONE);
                break;
            case C:
            case E:
                zoneAffiliations.add(Zone.TWO);
                zoneAffiliations.add(Zone.THREE);
                break;
            case D:
                zoneAffiliations.add(Zone.TWO);
                break;
            case F:
                zoneAffiliations.add(Zone.THREE);
                zoneAffiliations.add(Zone.FOUR);
                break;
            case G:
            case H:
            case I:
                zoneAffiliations.add(Zone.FOUR);
                break;
            default:
                throw new IllegalArgumentException("No recognized Station");
        }

        return zoneAffiliations;
    }

    public static Map<TravelZone, Double> getCostByTravel(Set<Zone> zoneFrom, Set<Zone> zoneTo){
        HashMap<TravelZone, Double> costsByTravel = new HashMap<>();

        zoneFrom.forEach( zoneFromElt ->
                    zoneTo.forEach( zoneToElt -> {
                        TravelZone travelZone = new TravelZone(zoneFromElt, zoneToElt);
                        costsByTravel.put(travelZone, TravelPrice.getTravelPriceMap().get(travelZone));
                    }));

        return costsByTravel;
    }

    public static <K, V> Stream<K> keys(Map<K, V> map, V value) {
        return map
                .entrySet()
                .stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey);
    }

    public static int convertEuroToCents(double number){
        return (int)(Math.round(number * 100));
    }
}
