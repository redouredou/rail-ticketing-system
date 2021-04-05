package org.example;

import org.example.outputmodel.Station;
import org.example.outputmodel.Zone;
import org.example.outputmodel.ZoneStationMapping;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.*;

class ZoneStationMappingTest {

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
        Set<Zone> actualZoneSet= ZoneStationMapping.getZonesByStation(station);

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
        Set<Zone> actualZoneSet= ZoneStationMapping.getZonesByStation(station);

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
        Set<Zone> actualZoneSet= ZoneStationMapping.getZonesByStation(Station.D);

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
        Set<Zone> actualZoneSet= ZoneStationMapping.getZonesByStation(Station.F);

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
        Set<Zone> actualZoneSet= ZoneStationMapping.getZonesByStation(station);

        //THEN
        Assertions.assertEquals(expectedZoneSet, actualZoneSet);
    }

}
