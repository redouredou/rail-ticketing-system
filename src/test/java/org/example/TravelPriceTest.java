package org.example;

import org.example.inputmodel.Price;
import org.example.outputmodel.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

class TravelPriceTest {

    @ParameterizedTest(name = "Should return the Price {1} for {0}")
    @MethodSource("provideTravelZoneAndExpectedPrice")
    void given_TravelZone_whenGetTravelPriceMap_ThenReturnThePrice(TravelZone travelZone, Price expectedPrice){
        //WHEN
        Map<TravelZone, Price> travelPriceMap = TravelPrice.getTravelPriceMap();

        //THEN
        Assertions.assertEquals(expectedPrice, travelPriceMap.get(travelZone));
    }


    private static Stream<Arguments> provideTravelZoneAndExpectedPrice() {

        return Stream.of(
                Arguments.of(new TravelZone(Zone.ONE, Zone.ONE), new Price(2.40)),
                Arguments.of(new TravelZone(Zone.ONE, Zone.TWO), new Price(2.40)),
                Arguments.of(new TravelZone(Zone.ONE, Zone.THREE), new Price(2.80)),
                Arguments.of(new TravelZone(Zone.ONE, Zone.FOUR), new Price(3.00)),

                Arguments.of(new TravelZone(Zone.TWO, Zone.ONE), new Price(2.40)),
                Arguments.of(new TravelZone(Zone.TWO, Zone.TWO), new Price(2.40)),
                Arguments.of(new TravelZone(Zone.TWO, Zone.THREE), new Price(2.80)),
                Arguments.of(new TravelZone(Zone.TWO, Zone.FOUR), new Price(3.00)),

                Arguments.of(new TravelZone(Zone.THREE, Zone.ONE), new Price(2.80)),
                Arguments.of(new TravelZone(Zone.THREE, Zone.TWO), new Price(2.80)),
                Arguments.of(new TravelZone(Zone.THREE, Zone.THREE), new Price(2.00)),
                Arguments.of(new TravelZone(Zone.THREE, Zone.FOUR), new Price(2.00)),

                Arguments.of(new TravelZone(Zone.FOUR, Zone.ONE), new Price(3.00)),
                Arguments.of(new TravelZone(Zone.FOUR, Zone.TWO), new Price(3.00)),
                Arguments.of(new TravelZone(Zone.FOUR, Zone.THREE), new Price(2.00)),
                Arguments.of(new TravelZone(Zone.FOUR, Zone.FOUR), new Price(2.00))
        );
    }
}
