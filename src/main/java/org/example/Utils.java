package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import java.io.File;

public class Utils {

    private Utils(){
        throw new IllegalArgumentException("Utility class");
    }

    public static Root parseJSONFile(String filename) throws JSONException {
        Root root = null;
        try {
            ObjectMapper om = new ObjectMapper();
            root = om.readValue(new File(filename), Root.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return root;
    }
}
