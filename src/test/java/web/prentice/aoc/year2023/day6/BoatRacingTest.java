package web.prentice.aoc.year2023.day6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoatRacingTest {

    @ParameterizedTest
    @CsvSource({
            " 7,  9,  4",
            "15, 40,  8",
            "30, 200, 9",

            })
    void calculateNumberWaysToBeatRecord(int time, int distance, int ways) {
        assertThat(BoatRacing.calculateNumberWaysToBeatRecord(new BoatRacing.Race(time, distance))).isEqualTo(ways);
    }

    @Test
    void calculateAllRaces(){
        List<BoatRacing.Race> races = List.of(
                new BoatRacing.Race(7, 9),
                new BoatRacing.Race(15, 40),
                new BoatRacing.Race(30, 200)
                );
        assertThat(BoatRacing.calculateRaces(races)).isEqualTo(288);
    }

    @Test
    void calculateAllRacesQ1(){
        List<BoatRacing.Race> races = List.of(
                new BoatRacing.Race(49, 263),
                new BoatRacing.Race(97, 1532),
                new BoatRacing.Race(94, 1378),
                new BoatRacing.Race(94, 1851)
        );
        assertThat(BoatRacing.calculateRaces(races)).isEqualTo(4403592);
    }

    @Test
    void calculateAllRacesQ2(){
        List<BoatRacing.Race> races = List.of(
                new BoatRacing.Race(49979494, 263153213781851L)
        );
        assertThat(BoatRacing.calculateRaces(races)).isEqualTo(38017587);
    }
}