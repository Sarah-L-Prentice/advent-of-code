package web.prentice.aoc.year2023.day3;

import lombok.extern.slf4j.Slf4j;
import web.prentice.aoc.utils.CalcUtils;
import web.prentice.aoc.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class GearRatios {

    private static final Pattern PATTERN = Pattern.compile("[0-9]+");


    public static int findAllPartNumbersAndSum(String filePath) {
        List<Integer> ints = findAllPartNumbers(filePath);
        return CalcUtils.sum(ints);
    }

    public static List<Integer> findAllPartNumbers(String filePath) {
        List<String> lines = FileUtils.readLines(filePath);
        return findDigits(lines);
    }

    public static List<Integer> findDigits(List<String> lines) {
        List<Integer> parts = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Matcher matcher = PATTERN.matcher(line);
            boolean reading = true;
            while (reading) {
                if (matcher.find()) {
                    String found = matcher.group();
                    log.info("Found number {}", found);
                    if (isValidPart(lines, i, found, matcher.start() - 1, matcher.end())) {
                        parts.add(Integer.parseInt(found));
                    }
                } else {
                    break;
                }
            }
        }
        return parts;
    }

    private static boolean isValidPart(List<String> lines, int currentLine, String found, int start, int end) {
        String line = lines.get(currentLine);

        int startRegion = start < 0 ? 0 : start;
        int endRegion = end >= line.length() ? line.length()-1 : end;
        int lineAbove = currentLine == 0 ? 0 : currentLine - 1;
        int lineBelow = currentLine == lines.size()-1 ? lines.size()-1 : currentLine + 1;

        for (int i = lineAbove; i <= lineBelow; i++) {
            String lineToCheck = lines.get(i);
            log.info("Validating {} is a part: Checking line number {}, line: {}", found, i, lineToCheck);
            for (int j = startRegion; j <= endRegion; j++) {
                char c = lineToCheck.charAt(j);
                if (isSymbol(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isSymbol(Character character) {
        return character != '.' && !Character.isDigit(character);
    }
}
