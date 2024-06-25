package web.prentice.aoc.year2023.day7;

import org.apache.commons.lang3.tuple.Pair;
import web.prentice.aoc.utils.FileUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.groupingBy;


public class CamelCards {

    public record CamelCard(List<Card> cards, int bid){
    }


    public static long determineTotalWinnings(String filepath){
       List<String> lines =  FileUtils.readLines(filepath);
       List<CamelCard> camelCards = lines.stream().map(line -> readLine(line)).toList();
       return 1l;
    }

    public static CamelCard readLine(String line){
        String[] split =  line.split(" ");
        int bid = Integer.parseInt(split[1]);
        var cards = split[0].chars().mapToObj(c -> (char) c).map(character -> Card.fromString(character.toString())).toList();
        return new CamelCard(cards, bid);
    }

    public enum Card {

        ACE("A"),
        KING("K"),
        QUEEN("Q"),
        JACK("J"),
        TEN("T"),
        NINE("9"),
        EIGHT("8"),
        SEVEN("7"),
        SIX("6"),
        FIVE("5"),
        FOUR("4"),
        THREE("3"),
        TWO("2"),
        ;

        public final String card;

        Card(String card) {
            this.card = card;
        }

        public static Card fromString(String text) {
            for (Card c : Card.values()) {
                if (c.card.equalsIgnoreCase(text)) {
                    return c;
                }
            }
            return null;
        }
    }

    public enum Hand {

        FIVE_OF_A_KIND() {
            @Override
            public Predicate<List<Card>> derive() {
                return cards -> cards.stream().distinct().count() == 1;
            }
        },
        FOUR_OF_A_KIND() {
            @Override
            public Predicate<List<Card>> derive() {
                return cards -> {
                    Map<Integer, List<Card>> map = cards.stream().collect(groupingBy(Card::ordinal));
                    return map.size() == 2 && map.values().stream().filter(list -> list.size() == 4).count() == 1;
                };
            }
        },
        FULL_HOUSE() {
            @Override
            public Predicate<List<Card>> derive() {
                return cards -> {
                    Map<Integer, List<Card>> map = cards.stream().collect(groupingBy(Card::ordinal));
                    return map.size() == 2 && map.values().stream().filter(list -> list.size() >= 2 && list.size() <= 3).count() == 2;
                };
            }
        },
        THREE_OF_A_KIND() {
            @Override
            public Predicate<List<Card>> derive() {
                return cards -> {
                    Map<Integer, List<Card>> map = cards.stream().collect(groupingBy(Card::ordinal));
                    return map.size() == 3 && map.values().stream().filter(list -> list.size() == 3).count() == 1;
                };
            }
        },
        TWO_PAIR() {
            @Override
            public Predicate<List<Card>> derive() {
                return cards -> {
                    Map<Integer, List<Card>> map = cards.stream().collect(groupingBy(Card::ordinal));
                    return map.size() == 3 && map.values().stream().filter(list -> list.size() == 2).count() == 2;
                };
            }
        },
        ONE_PAIR() {
            @Override
            public Predicate<List<Card>> derive() {
                return cards -> {
                    Map<Integer, List<Card>> map = cards.stream().collect(groupingBy(Card::ordinal));
                    return map.size() == 4 && map.values().stream().filter(list -> list.size() == 2).count() == 1;
                };
            }
        },
        HIGH_CARD() {
            @Override
            public Predicate<List<Card>> derive() {
                return cards -> cards.stream().distinct().count() == 5;
            }
        };

        public abstract Predicate<List<Card>> derive();
    }


}
