package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.outputmodel.RootOutput;
import org.example.outputmodel.TravelPrice;
import org.example.inputmodel.Price;
import org.example.inputmodel.RootInput;
import org.example.outputmodel.TravelZone;
import org.example.outputmodel.Zone;
import org.json.JSONException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

    public static Map<TravelZone, Price> getCostByTravel(Set<Zone> zoneFrom, Set<Zone> zoneTo){
        HashMap<TravelZone, Price> costsByTravel = new HashMap<>();

        zoneFrom.forEach( zoneFromElt ->
                    zoneTo.forEach( zoneToElt -> {
                        TravelZone travelZone = new TravelZone(zoneFromElt, zoneToElt);
                        costsByTravel.put(travelZone, TravelPrice.getTravelPriceMap().get(travelZone));
                    }));

        return costsByTravel;
    }


    public static <T> List<List<T>> partitionBasedOnSize(List<T> inputList, int size) {
        final AtomicInteger counter = new AtomicInteger(0);
        return inputList
                .stream()
                .collect(Collectors.groupingBy(s -> counter.getAndIncrement()/size))
                .values()
                .stream()
                .collect(Collectors.toList());
    }
}
