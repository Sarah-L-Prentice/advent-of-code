package web.prentice.aoc.year2023.day3;

import org.junit.jupiter.api.Test;
import web.prentice.aoc.utils.FileUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GearRatiosTest {


    @Test
    void exampleFindValidParts(){
        String example = "src/test/java/year2023/day3/example-input-1";
        List<Integer> result = GearRatios.findDigits(FileUtils.readLines(example));
        List<Integer> expected = List.of(467, 35, 633, 617, 592, 755, 664, 598);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void exampleFindValidPartsAndSum(){
        String example = "src/test/java/year2023/day3/example-input-1";
        int result = GearRatios.findAllPartNumbersAndSum(example);
        assertThat(result).isEqualTo(4361);
    }

    @Test
    void q1(){
        String example = "src/test/java/year2023/day3/input";
        int result = GearRatios.findAllPartNumbersAndSum(example);
        assertThat(result).isEqualTo(517021);
    }

}