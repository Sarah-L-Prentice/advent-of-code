package web.prentice.aoc.year2023.day4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import web.prentice.aoc.utils.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.*;

class ScratchcardsTest {

    public static Stream<Arguments> readCards() {
        return Stream.of(
                Arguments.of("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53", List.of(41, 48, 83, 86, 17)),
                Arguments.of("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19", List.of(13, 32, 20, 16, 61)),
                Arguments.of("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1", List.of(1, 21, 53, 59, 44)),
                Arguments.of("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83", List.of(41, 92, 73, 84, 69)),
                Arguments.of("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36", List.of(87, 83, 26, 28, 32)),
                Arguments.of("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11", List.of(31, 18, 13, 56, 72))
        );
    }

    @ParameterizedTest
    @MethodSource("readCards")
    void readCardNumbers(String line, List<Integer> expected) {
        assertThat(Scratchcards.readCard(line)).isEqualTo(expected);
    }

    public static Stream<Arguments> readWinningNumberCard() {
        return Stream.of(
                Arguments.of("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53", List.of(83, 86, 6, 31, 17, 9, 48, 53)),
                Arguments.of("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19", List.of(61, 30, 68, 82, 17, 32, 24, 19)),
                Arguments.of("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1", List.of(69, 82, 63, 72, 16, 21, 14, 1)),
                Arguments.of("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83", List.of(59, 84, 76, 51, 58, 5, 54, 83)),
                Arguments.of("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36", List.of(88, 30, 70, 12, 93, 22, 82, 36)),
                Arguments.of("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11", List.of(74, 77, 10, 23, 35, 67, 36, 11))
        );
    }

    @ParameterizedTest
    @MethodSource("readWinningNumberCard")
    void readWinningNumbers(String line, List<Integer> expected) {
        assertThat(Scratchcards.readWinningNumbers(line)).isEqualTo(expected);
    }

    @Test
    void exampleScoreCards(){
        var input = FileUtils.readLines("src/test/java/year2023/day4/example-input-1");
        assertThat(Scratchcards.scoreCards(input)).isEqualTo(13);
    }

    @Test
    void q1(){
        var input = FileUtils.readLines("src/test/java/year2023/day4/input");
        assertThat(Scratchcards.scoreCards(input)).isEqualTo(25174);
    }
    @Test
    void q2(){
        var input = FileUtils.readLines("src/test/java/year2023/day4/example-input-1");
        assertThat(Scratchcards.scoreCardsPart2(input)).isEqualTo(30);
    }

    @ParameterizedTest
    @CsvSource( {
            "1, 1",
            "2, 2" ,
            "3, 4",
            "4, 8",
            "5, 16",
            "6, 32",
            "7, 64",
            "8, 128"})
    void scoreTheCard(int count, int expected) {
        assertThat(Scratchcards.scoreTheCard(count)).isEqualTo(expected);

    }
}