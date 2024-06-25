package web.prentice.aoc.year2023.day5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class SeedsExampleTest {

    @ParameterizedTest
    @CsvSource( delimiter = '|', textBlock = """
            50 98 2  | ''
            52 50 48 | 55=57, 79=81
            """
    )
    void readLineToCreateMap(String input, String output){
        HashMap<Long, Long> map = new HashMap<>();
        Seeds.readLineToCreateMap(map, input, List.of(79L, 14L, 55L, 13L));
        assertThat(map.toString()).isEqualTo("{" + output + "}");
    }

    @Test
    void readLinesToCreateMapFilled() {
        var lines = List.of("50 98 2", "52 50 48");
        var map = Seeds.readLinesToCreateMap(lines, List.of(79L, 14L, 55L, 13L));
        assertThat(map.toString()).isEqualTo("{55=57, 13=13, 14=14, 79=81}");
        assertThat(map.size()).isEqualTo(4);
    }

//    @Test //takes 15 mins
//    void printSeedQ1(){
//        assertThat(Seeds.printSeedToLocationAndFindMin(Seeds.SEEDS)).isEqualTo(177942185L);
//    }

    @Test
    void getFullRange(){
        var result = Seeds.getFullRange(List.of(79L, 14L, 55L, 13L));
        assertThat(result).hasSize(27);
        assertThat(result.toString()).isEqualTo("[79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67]");
    }

//    @Test //java heap space
//    void printSeedQ2(){
//        var seeds = Seeds.getFullRange(Seeds.SEEDS);
//        assertThat(Seeds.printSeedToLocationAndFindMin(seeds)).isEqualTo(177942185L);
//    }
}