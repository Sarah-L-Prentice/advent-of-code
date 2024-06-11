package web.prentice.aoc.year2023.day2;

import org.apache.commons.lang3.StringUtils;
import web.prentice.aoc.utils.FileUtils;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CubeConundrum {

    private static int MAX_RED = 12;
    private static int MAX_GREEN = 13;
    private static int MAX_BLUE = 14;

    //only extract digit ID if preceded by "Game "
    private static final Pattern PATTERN_EXTRACT_GAME_ID = Pattern.compile("(?<=Game )[0-9]+");

    private static final Pattern PATTERN_EXTRACT_RED = Pattern.compile("[0-9]+(?= red)");
    private static final Pattern PATTERN_EXTRACT_GREEN = Pattern.compile("[0-9]+(?= green)");
    private static final Pattern PATTERN_EXTRACT_BLUE = Pattern.compile("[0-9]+(?= blue)");

    public static List<String> readFile(String filepath){
        return FileUtils.readLines(filepath);
    }

    public static int readAndSumLines(String filePath){
        List<String> lines = readFile(filePath);
        return lines.stream()
                .filter(isPossibleRed())
                .filter(isPossibleGreen())
                .filter(isPossibleBlue())
                .map(CubeConundrum::extractGameId)
                .reduce(0, Integer::sum);
    }

    public static int extractGameId(String line) {
        var matcher = PATTERN_EXTRACT_GAME_ID.matcher(line);
        matcher.find();
        return Integer.parseInt(matcher.group());
    }

    public static Predicate<String> isPossibleRed() {
        return line -> isPossible(PATTERN_EXTRACT_RED, line, MAX_RED);
    }

    public static Predicate<String> isPossibleGreen() {
        return line -> isPossible(PATTERN_EXTRACT_GREEN, line, MAX_GREEN);
    }

    public static Predicate<String> isPossibleBlue() {
        return line -> isPossible(PATTERN_EXTRACT_BLUE, line, MAX_BLUE);
    }

    public static int greatestNumberRed(String line){
        return greatestNumber(PATTERN_EXTRACT_RED, line);
    }

    public static int greatestNumberGreen(String line){
        return greatestNumber(PATTERN_EXTRACT_GREEN, line);
    }

    public static int greatestNumberBlue(String line){
        return greatestNumber(PATTERN_EXTRACT_BLUE, line);
    }

    public static boolean isPossible(Pattern pattern, String line, int max) {
        Matcher matcher = pattern.matcher(line);
        boolean isPossible = true;
        while(isPossible){
            if(matcher.find()){
                String found = matcher.group();
                int foundInt = Integer.parseInt(found);
                if(foundInt > max){
                    isPossible = false;
                }
            }else{
                break;
            }
        }
        return isPossible;
    }

    public static int greatestNumber(Pattern pattern, String line) {
        Matcher matcher = pattern.matcher(line);
        int max = 0;
        while(true){
            if(matcher.find()){
                String found = matcher.group();
                int foundInt = Integer.parseInt(found);
                if(foundInt > max){
                    max = foundInt;
                }
            }else{
                break;
            }
        }
        return max;
    }
    public static int readAndSumLinesQ2(String filePath){
        List<String> lines = readFile(filePath);
        return lines.stream()
                .map(line -> greatestNumberRed(line) * greatestNumberGreen(line) * greatestNumberBlue(line))
                .reduce(0, Integer::sum);
    }
}
