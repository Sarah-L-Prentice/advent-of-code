package web.prentice.aoc.year2023.day3;

import org.junit.jupiter.api.Test;
import web.prentice.aoc.utils.FileUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GearRatiosPart2Test {


    @Test
    void findGears() {
        String example = "src/test/java/year2023/day3/example-input-1";
        List<String> lines = FileUtils.readLines(example);
        var result = GearRatiosPart2.findStarSymbols(lines);
        assertThat(result).isEqualTo(List.of(16345, 451490));
    }
}