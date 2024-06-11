package web.prentice.aoc.year2023.day1;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import web.prentice.aoc.common.Number;
import web.prentice.aoc.utils.FileUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
public class DocumentReader {

    private static final Pattern PATTERN = Pattern.compile("one|two|three|four|five|six|seven|eight|nine|[0-9]");

    public static List<String> readFile(String filepath){
        return FileUtils.readLines(filepath);
    }

    public static int readAndSumLines(List<String> lines){
        return lines.stream().map(line -> {
            int first = findFirstDigit(line);
            int last = findLastDigit(line);
            return combineDigits(first, last);
        }).reduce(0, Integer::sum);
    }

    public static int findFirstDigit(String line) {
        Matcher matcher = PATTERN.matcher(line);
        matcher.find();
        return translateNumber(matcher.group());
    }

    public static String matcherFind(Matcher matcher){
        if(matcher.find()){
            String numberFound = matcher.group();
            log.info("Found: {}", numberFound);
            return numberFound;
        } else{
            return StringUtils.EMPTY;
        }
    }

    private static int translateNumber(String numberFound){
        try{
            return Number.valueOf(numberFound.toUpperCase()).getInt();
        }catch(Exception e){
            return Integer.parseInt(numberFound);
        }
    }

    public static int findLastDigit(String line) {
        Matcher matcher = PATTERN.matcher(line);
        boolean loop = true;
        String found = StringUtils.EMPTY;
        while(loop){
            if(matcher.find()){
                found = matcher.group();
            }else{
                loop = false;
            }
        }
        return translateNumber(found);
    }

    public static Matcher getMatcher(String line){
        Pattern pattern = Pattern.compile("one|two|three|four|five|six|seven|eight|nine|[0-9]");
        return pattern.matcher(line);
    }


    public static int combineDigits(int first, int last) {
        String result = String.valueOf(first).concat(String.valueOf(last));
        return Integer.parseInt(result);
    }

    public static int combineDigits(String line) {
        int first = findFirstDigit(line);
        int last = findLastDigit(line);
        return combineDigits(first, last);
    }
}
