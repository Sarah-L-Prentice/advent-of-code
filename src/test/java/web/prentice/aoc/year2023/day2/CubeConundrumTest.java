package web.prentice.aoc.year2023.day2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CubeConundrumTest {

    @ParameterizedTest
    @CsvSource(delimiter = '|', textBlock = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green                     | 1
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue           | 2
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red   | 3
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red   | 4
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green                     | 5
            """
            )
    void extractGameId(String input, int expected){
        assertThat(CubeConundrum.extractGameId(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|', textBlock = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green                     | true
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue           | true
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red   | false
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red   | false
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green                     | true
            """
    )
    void isPossibleWithMaxCubes(String input, boolean expected){
        boolean result =
        CubeConundrum.isPossibleRed().test(input) &&
        CubeConundrum.isPossibleBlue().test(input) &&
        CubeConundrum.isPossibleGreen().test(input);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void readAndSumAllLinesExample1(){
        String filepath = "src/test/java/year2023/day2/example-input-1";
        int result = CubeConundrum.readAndSumLines(filepath);
        assertThat(result).isEqualTo(8);
    }

    @Test
    void question1(){
        String filepath = "src/test/java/year2023/day2/input";
        int result = CubeConundrum.readAndSumLines(filepath);
        assertThat(result).isEqualTo(8);
    }

    @Test
    void exampleQuestion2(){
        String filepath = "src/test/java/year2023/day2/example-input-1";
        int result = CubeConundrum.readAndSumLinesQ2(filepath);
        assertThat(result).isEqualTo(2286);
    }

    @Test
    void question2(){
        String filepath = "src/test/java/year2023/day2/input";
        int result = CubeConundrum.readAndSumLinesQ2(filepath);
        assertThat(result).isEqualTo(84538);
    }

}