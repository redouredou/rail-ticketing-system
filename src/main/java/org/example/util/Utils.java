package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.outputmodel.RootOutput;
import org.example.inputmodel.RootInput;
import org.json.JSONException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Utils {

    private static final  Logger LOGGER = Logger.getLogger(Utils.class.getName());

    private Utils(){
        throw new IllegalArgumentException("Utility class");
    }

    public static RootInput parseJSONFile(String filename) throws JSONException {
        RootInput rootInput = null;
        try {
            ObjectMapper om = new ObjectMapper();
            rootInput = om.readValue(new File(filename), RootInput.class);
            LOGGER.info(" Input file processing ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootInput;
    }

    public static void generateOutputFile(RootOutput rootOutput, String outfile) throws JSONException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(outfile), rootOutput);
            LOGGER.info(" Generating output file ...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static <T> List<List<T>> partitionByPair(List<T> inputList) {
        final AtomicInteger counter = new AtomicInteger(0);
        return inputList
                .stream()
                .collect(Collectors.groupingBy(s -> counter.getAndIncrement()/2))
                .values()
                .stream()
                .collect(Collectors.toList());
    }
}
