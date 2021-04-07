package org.example;

import org.example.outputmodel.Price;
import org.example.outputmodel.Station;
import org.example.outputmodel.TravelZone;
import org.example.outputmodel.Zone;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class TravelZoneTest {


    @ParameterizedTest(name = "Should return {2} for the input stations {0} and {1}")
    @MethodSource("provideStationsAndTravelZoneList")
    void given_Stations_whenGetTravelsZoneByStations_ThenReturnTravelZoneList(Station stationStart, Station stationEnd, List<TravelZone> expectedTravelZoneList){
        //WHEN
        List<TravelZone> travelsByZone = TravelZone.getTravelsZoneByStations(stationStart, stationEnd);

        //THEN
        assertThat(travelsByZone)
                .hasSize(expectedTravelZoneList.size())
                .hasSameElementsAs(expectedTravelZoneList);
    }

    @ParameterizedTest(name = "Should return {2} for the input {1}")
    @MethodSource("provideTravelZoneList")
    void given_TravelZoneList_whenGetCheapestTravelZone_ThenReturnTravelZoneListWithCheapestPrice(List<TravelZone> travelZoneList, List<TravelZone> expectedList){

        //WHEN
        List<TravelZone> actualList = TravelZone.getCheapestTravelZone(travelZoneList);

        //THEN
        assertThat(actualList).isEqualTo(expectedList);
    }

    @ParameterizedTest(name = "Should return {1} for the input {0}")
    @MethodSource("provideTravelZoneListWithPrice")
    void given_TravelZoneList_whenGetMinimalCost_ThenReturnMinimalCost(List<TravelZone> travelZoneList, Price expectedPrice){

        //WHEN
        Price actualMinimalCost = TravelZone.getMinimalCost(travelZoneList);

        //THEN
        assertThat(actualMinimalCost).isEqualTo(expectedPrice);
    }

    @ParameterizedTest(name = "Should return {1} for the input {0}")
    @MethodSource("provideCheapestTravelZoneListWithSameStation")
    void given_TravelZoneListWithSameStations_whenGetClosestTravelZone_ThenReturnTravelZoneWithSameStation(List<TravelZone> travelZoneList, TravelZone expectedTravelZone){

        //WHEN
        TravelZone actualTravelZone = TravelZone.getShortestTravelZone(travelZoneList);

        //THEN
        assertThat(actualTravelZone).isEqualTo(expectedTravelZone);
    }

    @ParameterizedTest(name = "Should return {1} for the input {0}")
    @MethodSource("provideCheapestTravelZoneListWithoutSameStation")
    void given_TravelZoneListWithoutSameStations_whenGetClosestTravelZone_ThenReturnTravelZoneWithoutSameStation(List<TravelZone> travelZoneList, TravelZone expectedTravelZone){

        //WHEN
        TravelZone actualTravelZone = TravelZone.getShortestTravelZone(travelZoneList);

        //THEN
        assertThat(actualTravelZone).isEqualTo(expectedTravelZone);
    }

    public static Stream<Arguments> provideStationsAndTravelZoneList(){

        return Stream.of(
                Arguments.of(Station.A, Station.B, Arrays.asList(new TravelZone(Zone.ONE, Zone.ONE))),
                Arguments.of(Station.A, Station.C, Arrays.asList(new TravelZone(Zone.ONE, Zone.THREE), new TravelZone(Zone.ONE, Zone.TWO))),
                Arguments.of(Station.C, Station.G, Arrays.asList(new TravelZone(Zone.THREE, Zone.FOUR), new TravelZone(Zone.TWO, Zone.FOUR))),
                Arguments.of(Station.C, Station.F, Arrays.asList(new TravelZone(Zone.TWO, Zone.THREE), new TravelZone(Zone.TWO, Zone.FOUR), new TravelZone(Zone.THREE, Zone.THREE), new TravelZone(Zone.THREE, Zone.FOUR)))
        );
    }

    public static Stream<Arguments> provideTravelZoneListWithPrice(){

        return Stream.of(
                Arguments.of(Arrays.asList(new TravelZone(Zone.TWO, Zone.FOUR), new TravelZone(Zone.THREE, Zone.FOUR)), new Price(2.00))
        );
    }

    public static Stream<Arguments> provideTravelZoneList(){

        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new TravelZone(Zone.ONE, Zone.ONE),
                                new TravelZone(Zone.ONE, Zone.TWO),
                                new TravelZone(Zone.ONE, Zone.THREE),
                                new TravelZone(Zone.ONE, Zone.FOUR)),
                        Arrays.asList(
                                new TravelZone(Zone.ONE, Zone.ONE),
                                new TravelZone(Zone.ONE, Zone.TWO))
                        )
        );
    }

    public static Stream<Arguments> provideCheapestTravelZoneListWithSameStation(){

        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new TravelZone(Zone.ONE, Zone.ONE),
                                new TravelZone(Zone.ONE, Zone.TWO)),
                                new TravelZone(Zone.ONE, Zone.ONE)
        ));
    }

    public static Stream<Arguments> provideCheapestTravelZoneListWithoutSameStation(){

        return Stream.of(
                Arguments.of(
                        Arrays.asList(new TravelZone(Zone.ONE, Zone.TWO)), new TravelZone(Zone.ONE, Zone.TWO)
                ));
    }

}
