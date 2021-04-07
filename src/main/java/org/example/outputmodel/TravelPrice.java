package org.example.outputmodel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



public enum TravelPrice {

    FROM_ZONE_1_TO_ZONE_1(new TravelZone(Zone.ONE, Zone.ONE), new Price(2.40)),
    FROM_ZONE_1_TO_ZONE_2(new TravelZone(Zone.ONE, Zone.TWO),new Price(2.40)),
    FROM_ZONE_1_TO_ZONE_3(new TravelZone(Zone.ONE, Zone.THREE),new Price(2.80)),
    FROM_ZONE_1_TO_ZONE_4(new TravelZone(Zone.ONE, Zone.FOUR),new Price(3.00)),

    FROM_ZONE_2_TO_ZONE_1(new TravelZone(Zone.TWO, Zone.ONE),new Price(2.40)),
    FROM_ZONE_2_TO_ZONE_2(new TravelZone(Zone.TWO, Zone.TWO),new Price(2.40)),
    FROM_ZONE_2_TO_ZONE_3(new TravelZone(Zone.TWO, Zone.THREE),new Price(2.80)),
    FROM_ZONE_2_TO_ZONE_4(new TravelZone(Zone.TWO, Zone.FOUR),new Price(3.00)),

    FROM_ZONE_3_TO_ZONE_1(new TravelZone(Zone.THREE, Zone.ONE),new Price(2.80)),
    FROM_ZONE_3_TO_ZONE_2(new TravelZone(Zone.THREE, Zone.TWO),new Price(2.80)),
    FROM_ZONE_3_TO_ZONE_3(new TravelZone(Zone.THREE, Zone.THREE),new Price(2.00)),
    FROM_ZONE_3_TO_ZONE_4(new TravelZone(Zone.THREE, Zone.FOUR),new Price(2.00)),

    FROM_ZONE_4_TO_ZONE_1(new TravelZone(Zone.FOUR, Zone.ONE),new Price(3.00)),
    FROM_ZONE_4_TO_ZONE_2(new TravelZone(Zone.FOUR, Zone.TWO),new Price(3.00)),
    FROM_ZONE_4_TO_ZONE_3(new TravelZone(Zone.FOUR, Zone.THREE),new Price(2.00)),
    FROM_ZONE_4_TO_ZONE_4(new TravelZone(Zone.FOUR, Zone.FOUR),new Price(2.00));

    private final Price price;
    private final TravelZone travelZone;
    private static final Map<TravelZone, Price> travelPriceMap = Collections.unmodifiableMap(initMapping());


    TravelPrice(TravelZone travelZone,Price price){
        this.travelZone = travelZone;
        this.price = price;
    }

    public static Map<TravelZone, Price> getTravelPriceMap(){
        return travelPriceMap;
    }


    private static Map<TravelZone, Price> initMapping() {
        Map<TravelZone, Price> travelPriceMap = new HashMap<>();
        for (TravelPrice tp : TravelPrice.values()) {
            travelPriceMap.put(tp.travelZone, tp.price);
        }
        return travelPriceMap;
    }
}
