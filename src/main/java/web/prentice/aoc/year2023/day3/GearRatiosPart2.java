package web.prentice.aoc.year2023.day3;

import lombok.extern.slf4j.Slf4j;
import web.prentice.aoc.utils.CalcUtils;
import web.prentice.aoc.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class GearRatiosPart2 {

    private static final Pattern PATTERN_MULTIPLY = Pattern.compile("\\*");
    private static final Pattern PATTERN_DIGIT = Pattern.compile("[0-9]+");


    public static int findAllPartNumbersAndSum(String filePath) {
        List<Integer> ints = findAllPartNumbers(filePath);
        return CalcUtils.sum(ints);
    }

    public static List<Integer> findAllPartNumbers(String filePath) {
        List<String> lines = FileUtils.readLines(filePath);
        return findStarSymbols(lines);
    }

    public static List<Integer> findStarSymbols(List<String> lines) {
        List<Integer> parts = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Matcher matcher = PATTERN_MULTIPLY.matcher(line);
            boolean reading = true;
            while (reading) {
                if (matcher.find()) {
                    String found = matcher.group();
                    log.info("Found symbol {}. Seeing if valid gear", found);
                    Integer gear = getGear(lines, i, found, matcher.start() - 1, matcher.end());
                    if (null != gear) {
                        parts.add(gear);
                    }
                } else {
                    break;
                }
            }
        }
        return parts;
    }

    private static Integer getGear(List<String> lines, int currentLine, String found, int start, int end) {
        String line = lines.get(currentLine);
        List<Integer> parts = new ArrayList<>();

        int startRegion = start < 0 ? 0 : start;
        int endRegion = end >= line.length() ? line.length() - 1 : end;
        int lineAbove = currentLine == 0 ? 0 : currentLine - 1;
        int lineBelow = currentLine == lines.size() - 1 ? lines.size() - 1 : currentLine + 1;

        for (int i = lineAbove; i <= lineBelow; i++) {
            String lineToCheck = lines.get(i);
            log.info("Validating gear by checking line number {}, line: {}", found, i, lineToCheck);
            Matcher matcher = PATTERN_DIGIT.matcher(lineToCheck);
            boolean reading = true;
            while (reading) {
                if (matcher.find()) {
                    String digits = matcher.group();
                    log.info("Found number {}", digits);
                    int startRegionDigit = matcher.start() + 1;
                    int endRegionDigit = matcher.end() - 1;
                    if (digits.equals("")){
                        continue;
                    }
                    if (startRegionDigit >= startRegion || endRegionDigit <= endRegion) {
                        parts.add(Integer.parseInt(digits));
                    } else {
                        reading = false;
                    }
                }else{
                    reading = false;
                }
            }
        }
        if (parts.size() == 2) {
            return parts.get(0) * parts.get(1);
        } else {
            return null;
        }
    }


}
