package org.example;

import org.assertj.core.api.Assertions;
import org.example.outputmodel.Station;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class StationTest {

    @ParameterizedTest(name = "Should return {1} for the input station name {0}")
    @MethodSource("provideStationNames")
    void given_StationName_whenGetStationByName_ThenReturnTheStation(String stationName, Station expectedStation){
        //WHEN
        Station actualStation = Station.getStationByName(stationName);
        //THEN
        assertThat(expectedStation).isEqualTo(actualStation);

    }

    public static Stream<Arguments> provideStationNames(){

        return Stream.of(
                Arguments.of("A", Station.A),
                Arguments.of("B", Station.B),
                Arguments.of("C", Station.C),
                Arguments.of("D", Station.D),
                Arguments.of("E", Station.E),
                Arguments.of("F", Station.F),
                Arguments.of("G", Station.G),
                Arguments.of("H", Station.H),
                Arguments.of("I", Station.I)
        );
    }

    @Test
    @DisplayName("it should throw IllegalArgumentException when the station doesn't exist")
    void given_UnknownStationName_whenGetStationByName_ThenThrowIllegalArgumentException(){

        //GIVEN
        String stationName = "S";
        //WHEN
        assertThatThrownBy(() -> {
            Station actualStation = Station.getStationByName(stationName);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this enum doesn't exist");

    }
}
