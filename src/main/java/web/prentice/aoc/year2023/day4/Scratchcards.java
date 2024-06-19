package web.prentice.aoc.year2023.day4;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import web.prentice.aoc.utils.CalcUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Scratchcards {

    private static final Pattern PATTERN_EXTRACT_CARD_NUMBERS = Pattern.compile("(?<=: )[0-9 ]+(?= \\|)");
    private static final Pattern PATTERN_EXTRACT_WINNING_NUMBERS = Pattern.compile("(?<=\\| )[0-9 ]+");


    public static Integer scoreCardsPart2(List<String> cards) {
        List<Integer> numberOfScratchCards = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {
            String card = cards.get(i);
            int matches = findNumberOfMatches(card);
            numberOfScratchCards.add(2);
            System.out.println(card + " matches: " +  matches);

            for (int j = i + 1; j < i + matches + 1; j++) {
                System.out.println(" j = " + j);
                String nextCard = cards.get(j);
                int nextMatches = findNumberOfMatches(nextCard);
                System.out.println("Copy of: " + nextCard + " with matches: " +  nextMatches);
                numberOfScratchCards.add(1);
                numberOfScratchCards.add(nextMatches);
            }
        }
        return numberOfScratchCards.size();
    }

    public static Integer findNumberOfMatches(String scratchCard){
        List<Integer> card = readCard(scratchCard);
        List<Integer> winning = readWinningNumbers(scratchCard);
        return ListUtils.intersection(card, winning).size();
    }


    public static Integer scoreCards(List<String> lines) {
        List<Integer> scores = new ArrayList<>();
        for (String line : lines) {
            List<Integer> card = readCard(line);
            List<Integer> winning = readWinningNumbers(line);
            List<Integer> matches = ListUtils.intersection(card, winning);
            scores.add(scoreTheCard(matches.size()));
        }
        return CalcUtils.sum(scores);
    }

    public static int scoreTheCard(int totalWinningNumbers) {
        if (totalWinningNumbers == 1) {
            return 1;
        }
        if (totalWinningNumbers == 0) {
            return 0;
        }
        int score = 1;
        for (int i = 1; i < totalWinningNumbers; i++) {
            score = score * 2;
        }
        return score;
    }

    public static List<Integer> readCard(String line) {
        return readPatternAndCollectInts(line, PATTERN_EXTRACT_CARD_NUMBERS);
    }


    public static List<Integer> readWinningNumbers(String line) {
        return readPatternAndCollectInts(line, PATTERN_EXTRACT_WINNING_NUMBERS);
    }

    public static List<Integer> readPatternAndCollectInts(String line, Pattern pattern) {
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        String card = matcher.group().trim().replace("  ", " ");
        return Arrays.stream(card.split(" ")).map(val -> Integer.parseInt(val)).toList();
    }


}
