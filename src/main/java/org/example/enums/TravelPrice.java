package org.example.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum TravelPrice {
    FROM_ZONE_1_TO_ZONE_1("1 1",2.40),
    FROM_ZONE_1_TO_ZONE_2("1 2",2.40),
    FROM_ZONE_1_TO_ZONE_3("1 3",2.80),
    FROM_ZONE_1_TO_ZONE_4("1 4",3.00),

    FROM_ZONE_2_TO_ZONE_1("2 1",2.40),
    FROM_ZONE_2_TO_ZONE_2("2 2",2.40),
    FROM_ZONE_2_TO_ZONE_3("2 3",2.80),
    FROM_ZONE_2_TO_ZONE_4("2 4",3.00),

    FROM_ZONE_3_TO_ZONE_1("3 1",2.80),
    FROM_ZONE_3_TO_ZONE_2("3 2",2.80),
    FROM_ZONE_3_TO_ZONE_3("3 3",2.00),
    FROM_ZONE_3_TO_ZONE_4("3 4",2.00),

    FROM_ZONE_4_TO_ZONE_1("4 1",3.00),
    FROM_ZONE_4_TO_ZONE_2("4 2",3.00),
    FROM_ZONE_4_TO_ZONE_3("4 3",2.00),
    FROM_ZONE_4_TO_ZONE_4("4 4",2.00);

    private final double price;
    private final String endStationsTravel;
    private static final Map<String, Double> travelPriceMap = Collections.unmodifiableMap(initMapping());

    TravelPrice(String endStationsTravel,double price){
        this.endStationsTravel = endStationsTravel;
        this.price = price;
    }

    public static Map<String, Double> getTravelPriceMap(){
        return travelPriceMap;
    }


    private static Map<String, Double> initMapping() {
        Map<String, Double> travelPriceMap = new HashMap<>();
        for (TravelPrice tp : TravelPrice.values()) {
            travelPriceMap.put(tp.endStationsTravel, tp.price);
        }
        return travelPriceMap;
    }
}
