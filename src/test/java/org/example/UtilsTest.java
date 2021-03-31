package org.example;

import org.example.enums.Station;
import org.example.enums.Zone;
import org.example.model.TravelZone;
import org.example.utils.Utils;
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

class UtilsTest {

    static Set<Zone> zoneOneSet;
    static Set<Zone> zoneTwoSet;
    static Set<Zone> zoneThreeSet;
    static Set<Zone> zoneFourSet;
    static Set<Zone> zoneSetWithZoneTwoAndThree;
    static Set<Zone> zoneSetWithZoneThreeAndFour;

    @BeforeAll
    static void init(){

        zoneOneSet = Collections.unmodifiableSet(
                new HashSet<>(Arrays.asList(Zone.ONE)));
        zoneTwoSet = Collections.unmodifiableSet(
                new HashSet<>(Arrays.asList(Zone.TWO)));
        zoneThreeSet = Collections.unmodifiableSet(
                new HashSet<>(Arrays.asList(Zone.THREE)));
        zoneFourSet = Collections.unmodifiableSet(
                new HashSet<>(Arrays.asList(Zone.FOUR)));
        zoneSetWithZoneTwoAndThree = Collections.unmodifiableSet(
                new HashSet<>(Arrays.asList(Zone.TWO, Zone.THREE)));
        zoneSetWithZoneThreeAndFour = Collections.unmodifiableSet(
                new HashSet<>(Arrays.asList(Zone.THREE, Zone.FOUR)));
    }

    @ParameterizedTest(name = "Should return a set containing Zone ONE for station {0}")
    @EnumSource(value = Station.class, names = {"A", "B"})
    void givenStationAorB_whenZoneCheck_ThenReturnZoneOne(Station station){
        //GIVEN
        Set<Zone> expectedZoneSet = Collections.unmodifiableSet(
                new HashSet<>(Arrays.asList(Zone.ONE)));

        //WHEN
        Set<Zone> actualZoneSet= Utils.getZonesByStation(station);

        //THEN
        Assertions.assertEquals(expectedZoneSet, actualZoneSet);
    }

    @ParameterizedTest(name = "Should return a set containing Zone TWO and Zone THREE for station {0}")
    @EnumSource(value = Station.class, names = {"C", "E"})
    void givenStationCorE_whenZoneCheck_ThenReturnZoneOneAndTwo(Station station){
        //GIVEN
        Set<Zone> expectedZoneSet = Collections.unmodifiableSet(
                new HashSet<>(Arrays.asList(Zone.TWO, Zone.THREE)));

        //WHEN
        Set<Zone> actualZoneSet= Utils.getZonesByStation(station);

        //THEN
        Assertions.assertEquals(expectedZoneSet, actualZoneSet);
    }

    @Test
    @DisplayName("Should return a set containing Zone TWO for station D")
    void givenStationD_whenZoneCheck_ThenReturnZoneTwo(){
        //GIVEN
        Set<Zone> expectedZoneSet = Collections.unmodifiableSet(
                new HashSet<>(Arrays.asList(Zone.TWO)));

        //WHEN
        Set<Zone> actualZoneSet= Utils.getZonesByStation(Station.D);

        //THEN
        Assertions.assertEquals(expectedZoneSet, actualZoneSet);
    }

    @Test
    @DisplayName("Should return a set containing Zone THREE and Zone FOUR for station F")
    void givenStationF_whenZoneCheck_ThenReturnZoneThreeAndZoneFour(){
        //GIVEN
        Set<Zone> expectedZoneSet = Collections.unmodifiableSet(
                new HashSet<>(Arrays.asList(Zone.THREE, Zone.FOUR)));

        //WHEN
        Set<Zone> actualZoneSet= Utils.getZonesByStation(Station.F);

        //THEN
        Assertions.assertEquals(expectedZoneSet, actualZoneSet);
    }

    @ParameterizedTest(name = "Should return a set containing Zone FOUR for station {0}")
    @EnumSource(value = Station.class, names = {"G", "H", "I"})
    void givenStationGorHorI_whenZoneCheck_ThenReturnZoneFour(Station station){
        //GIVEN
        Set<Zone> expectedZoneSet = Collections.unmodifiableSet(
                new HashSet<>(Arrays.asList(Zone.FOUR)));

        //WHEN
        Set<Zone> actualZoneSet= Utils.getZonesByStation(station);

        //THEN
        Assertions.assertEquals(expectedZoneSet, actualZoneSet);
    }

    @ParameterizedTest(name = "Should return a set containing TravelZone({0},{1}) and the price 2.40")
    @MethodSource("provideArgumentsZoneForPrice2_4")
    void given_SetZone_whenGetCostByTravel_ThenReturnMapWithPrice2_4(Set<Zone> zonesStart, Set<Zone> zonesEnd){
        //GIVEN
        Zone zoneStart = zonesStart.stream().findFirst().get();
        Zone zoneEnd = zonesEnd.stream().findFirst().get();
        Map<TravelZone, Double> expectedCostsByTravel = new HashMap<>();
        expectedCostsByTravel.put(new TravelZone(zoneStart, zoneEnd), 2.40);

        //WHEN
        Map<TravelZone, Double> actualCostsByTravel = Utils.getCostByTravel(zonesStart, zonesEnd);

        //THEN
        Assertions.assertEquals(expectedCostsByTravel, actualCostsByTravel);
    }

    @ParameterizedTest(name = "Should return a set containing TravelZone({0},{1}) and the price 2.80")
    @MethodSource("provideArgumentsZoneForPrice2_8")
    void given_SetZone_whenGetCostByTravel_ThenReturnMapWithPrice2_8(Set<Zone> zonesStart, Set<Zone> zonesEnd){
        //GIVEN
        Zone zoneStart = zonesStart.stream().findFirst().get();
        Zone zoneEnd = zonesEnd.stream().findFirst().get();
        Map<TravelZone, Double> expectedCostsByTravel = new HashMap<>();
        expectedCostsByTravel.put(new TravelZone(zoneStart, zoneEnd), 2.80);

        //WHEN
        Map<TravelZone, Double> actualCostsByTravel = Utils.getCostByTravel(zonesStart, zonesEnd);

        //THEN
        Assertions.assertEquals(expectedCostsByTravel, actualCostsByTravel);
    }

    @ParameterizedTest(name = "Should return a set containing TravelZone({0},{1}) and the price 2.00")
    @MethodSource("provideArgumentsZoneForPrice2_0")
    void given_SetZone_whenGetCostByTravel_ThenReturnMapWithPrice2_0(Set<Zone> zonesStart, Set<Zone> zonesEnd){
        //GIVEN
        Zone zoneStart = zonesStart.stream().findFirst().get();
        Zone zoneEnd = zonesEnd.stream().findFirst().get();
        Map<TravelZone, Double> expectedCostsByTravel = new HashMap<>();
        expectedCostsByTravel.put(new TravelZone(zoneStart, zoneEnd), 2.00);

        //WHEN
        Map<TravelZone, Double> actualCostsByTravel = Utils.getCostByTravel(zonesStart, zonesEnd);

        //THEN
        Assertions.assertEquals(expectedCostsByTravel, actualCostsByTravel);
    }

    @ParameterizedTest(name = "Should return a set containing TravelZone({0},{1}) and the price 2.00")
    @MethodSource("provideArgumentsZoneForPrice3_0")
    void given_SetZone_whenGetCostByTravel_ThenReturnMapWithPrice3_0(Set<Zone> zonesStart, Set<Zone> zonesEnd){
        //GIVEN
        Zone zoneStart = zonesStart.stream().findFirst().get();
        Zone zoneEnd = zonesEnd.stream().findFirst().get();
        Map<TravelZone, Double> expectedCostsByTravel = new HashMap<>();
        expectedCostsByTravel.put(new TravelZone(zoneStart, zoneEnd), 3.00);

        //WHEN
        Map<TravelZone, Double> actualCostsByTravel = Utils.getCostByTravel(zonesStart, zonesEnd);

        //THEN
        Assertions.assertEquals(expectedCostsByTravel, actualCostsByTravel);
    }

    @ParameterizedTest(name = "Should return a set containing two TravelZone({0},{1}) and two price 2.00 and 2.40")
    @MethodSource("provideArgumentsZoneForTwoPrices_2_4_and_2_0")
    void given_SetZone_whenGetCostByTravel_ThenReturnMapWithTwoPrices_2_4_and_2_0(Set<Zone> zonesStart, Set<Zone> zonesEnd){
        //GIVEN

        Map<TravelZone, Double> expectedCostsByTravel = new HashMap<>();
        expectedCostsByTravel.put(new TravelZone(Zone.TWO, Zone.FOUR), 3.00);
        expectedCostsByTravel.put(new TravelZone(Zone.THREE, Zone.FOUR), 2.00);

        //WHEN
        Map<TravelZone, Double> actualCostsByTravel = Utils.getCostByTravel(zonesStart, zonesEnd);

        //THEN
        Assertions.assertEquals(expectedCostsByTravel, actualCostsByTravel);
    }

    @ParameterizedTest(name = "Should return a set containing four TravelZone and several prices 2.00, 2.80 and 3.00")
    @MethodSource("provideArgumentsZoneForSeveralPrices_2_0_2_8_3_0")
    void given_SetZone_whenGetCostByTravel_ThenReturnMapWithSeveralPrices_2_0_2_8_3_0(Set<Zone> zonesStart, Set<Zone> zonesEnd){
        //GIVEN

        Map<TravelZone, Double> expectedCostsByTravel = new HashMap<>();
        expectedCostsByTravel.put(new TravelZone(Zone.TWO, Zone.THREE), 2.80);
        expectedCostsByTravel.put(new TravelZone(Zone.TWO, Zone.FOUR), 3.00);
        expectedCostsByTravel.put(new TravelZone(Zone.THREE, Zone.THREE), 2.00);
        expectedCostsByTravel.put(new TravelZone(Zone.THREE, Zone.FOUR), 2.00);

        //WHEN
        Map<TravelZone, Double> actualCostsByTravel = Utils.getCostByTravel(zonesStart, zonesEnd);

        //THEN
        Assertions.assertEquals(expectedCostsByTravel, actualCostsByTravel);
    }



    private static Stream<Arguments> provideArgumentsZoneForPrice2_4() {
        return Stream.of(
                Arguments.of(zoneOneSet, zoneOneSet),
                Arguments.of(zoneOneSet, zoneTwoSet),
                Arguments.of(zoneTwoSet, zoneOneSet),
                Arguments.of(zoneTwoSet, zoneTwoSet)
        );
    }

    private static Stream<Arguments> provideArgumentsZoneForPrice2_8() {
        return Stream.of(
                Arguments.of(zoneOneSet, zoneThreeSet),
                Arguments.of(zoneTwoSet, zoneThreeSet),
                Arguments.of(zoneThreeSet, zoneOneSet),
                Arguments.of(zoneThreeSet, zoneTwoSet)
        );
    }

    private static Stream<Arguments> provideArgumentsZoneForPrice2_0() {
        return Stream.of(
                Arguments.of(zoneThreeSet, zoneThreeSet),
                Arguments.of(zoneThreeSet, zoneFourSet),
                Arguments.of(zoneFourSet, zoneThreeSet),
                Arguments.of(zoneFourSet, zoneFourSet)
        );
    }

    private static Stream<Arguments> provideArgumentsZoneForPrice3_0() {
        return Stream.of(
                Arguments.of(zoneOneSet, zoneFourSet),
                Arguments.of(zoneTwoSet, zoneFourSet),
                Arguments.of(zoneFourSet, zoneOneSet),
                Arguments.of(zoneFourSet, zoneTwoSet)
        );
    }

    private static Stream<Arguments> provideArgumentsZoneForTwoPrices_2_4_and_2_0() {
        return Stream.of(
                Arguments.of(zoneSetWithZoneTwoAndThree, zoneFourSet)
        );
    }

    private static Stream<Arguments> provideArgumentsZoneForSeveralPrices_2_0_2_8_3_0() {
        return Stream.of(
                Arguments.of(zoneSetWithZoneTwoAndThree, zoneSetWithZoneThreeAndFour)
        );
    }
}
