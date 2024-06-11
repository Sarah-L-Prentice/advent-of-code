package web.prentice.aoc.year2023.day1;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class DocumentReaderTest {

    @ParameterizedTest
    @CsvSource({
            //part 1
            "1abc2,              1",
            "pqr3stu8vwx,        3",
            "a1b2c3d4e5f,        1",
            "treb7uchet,         7",
            //part 2
            "two1nine,           2",
            "eightwothree,       8",
            "abcone2threexyz,    1",
            "xtwone3four,        2",
            "4nineeightseven2,   4",
            "zoneight234,        1",
            "7pqrstsixteen,      7"
    }
    )
    void findFirstDigit(String line, int expected) {
        int result = DocumentReader.findFirstDigit(line);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            //Part 1
            "1abc2,              2",
            "pqr3stu8vwx,        8",
            "a1b2c3d4e5f,        5",
            "treb7uchet,         7",
            //Part 2
            "two1nine,           9",
            "eightwothree,       3",
            "abcone2threexyz,    3",
            "xtwone3four,        4",
            "4nineeightseven2,   2",
            "zoneight234,        4",
            "7pqrstsixteen,      6"
    })
    void findLastDigit(String line, int expected) {
        int result = DocumentReader.findLastDigit(line);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            //Part 1
            "1abc2,        12",
            "pqr3stu8vwx,  38",
            "a1b2c3d4e5f,  15",
            "treb7uchet,   77",
            //Part 2
            "two1nine,           29",
            "eightwothree,       83",
            "abcone2threexyz,    13",
            "xtwone3four,        24",
            "4nineeightseven2,   42",
            "zoneight234,        14",
            "7pqrstsixteen,      76"
    })
    void combineFirstAndLastDigit(String line, int expected) {
        int result = DocumentReader.combineDigits(line);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void readAndSumLinesProducesCorrectResultPartOne() {
        List<String> lines = List.of(
                "1abc2",
                "pqr3stu8vwx",
                "a1b2c3d4e5f",
                "treb7uchet"
        );
        int result = DocumentReader.readAndSumLines(lines);
        assertThat(result).isEqualTo(142);
    }

    @Test
    public void readAndSumLinesProducesCorrectResultPartTwo() {
        List<String> lines = List.of(
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen"
        );
        int result = DocumentReader.readAndSumLines(lines);
        assertThat(result).isEqualTo(281);
    }

    @Test
    public void example1() {
        var lines = DocumentReader.readFile("src/test/java/year2023/day1/example-input-1");
        int result = DocumentReader.readAndSumLines(lines);
        assertThat(result).isEqualTo(142);
    }

    @Test
    public void question2() {
        var lines = DocumentReader.readFile("src/test/java/year2023/day1/input");
        int result = DocumentReader.readAndSumLines(lines);
//        assertThat(result).isEqualTo(54561); Q1 answer
        assertThat(result).isEqualTo(54076);
    }


}