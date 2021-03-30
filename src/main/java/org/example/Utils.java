package org.example;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.enums.Station;
import org.example.enums.Zone;
import org.example.model.RootInput;
import org.example.model.RootOutput;
import org.json.JSONException;
import java.io.File;
import java.io.IOException;
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
            //Convert object to JSON string and save into file directly
            mapper.writeValue(new File(outfile), rootOutput);

            /*//Convert object to JSON string
            String jsonInString = mapper.writeValueAsString(user);
            System.out.println(jsonInString);

            //Convert object to JSON string and pretty print
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
            System.out.println(jsonInString);*/


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static Set<Integer> checkZoneStation(Station zoneStation){
        Set<Integer> zoneAffiliations = new HashSet<>();
        switch (zoneStation){
            case A:
            case B:
                zoneAffiliations.add(Zone.ONE.getZoneNumber());
                break;
            case C:
            case E:
                zoneAffiliations.add(Zone.TWO.getZoneNumber());
                zoneAffiliations.add(Zone.THREE.getZoneNumber());
                break;
            case D:
                zoneAffiliations.add(Zone.TWO.getZoneNumber());
                break;
            case F:
                zoneAffiliations.add(Zone.THREE.getZoneNumber());
                zoneAffiliations.add(Zone.FOUR.getZoneNumber());
                break;
            case G:
            case H:
            case I:
                zoneAffiliations.add(Zone.FOUR.getZoneNumber());
                break;
            default:
                throw new IllegalArgumentException("No recognized Station");
        }

        return zoneAffiliations;
    }

    public static <K, V> Stream<K> keys(Map<K, V> map, V value) {
        return map
                .entrySet()
                .stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey);
    }
}
