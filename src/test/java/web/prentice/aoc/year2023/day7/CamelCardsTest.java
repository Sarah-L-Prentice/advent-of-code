package web.prentice.aoc.year2023.day7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static web.prentice.aoc.year2023.day7.CamelCards.Card.*;

class CamelCardsTest {

    @Test
    void ordersCardByOrdinal(){
        List<CamelCards.Card> list = new ArrayList<>();
        list.add(TWO);
        list.add(QUEEN);
        list.add(FIVE);
        list.add(ACE);
        list.add(JACK);
        Collections.sort(list);
        assertThat(list).isEqualTo(List.of(ACE, QUEEN, JACK, FIVE, TWO));
        assertThat(ACE.ordinal()).isEqualTo(0);
        assertThat(KING.ordinal()).isEqualTo(1);
        assertThat(QUEEN.ordinal()).isEqualTo(2);
    }

    @Test
    void cardOrdinals(){
        assertThat(ACE.ordinal()).isEqualTo(0);
        assertThat(KING.ordinal()).isEqualTo(1);
        assertThat(QUEEN.ordinal()).isEqualTo(2);
        assertThat(JACK.ordinal()).isEqualTo(3);
        assertThat(TEN.ordinal()).isEqualTo(4);
        assertThat(NINE.ordinal()).isEqualTo(5);
        assertThat(EIGHT.ordinal()).isEqualTo(6);
        assertThat(SEVEN.ordinal()).isEqualTo(7);
        assertThat(SIX.ordinal()).isEqualTo(8);
        assertThat(FIVE.ordinal()).isEqualTo(9);
        assertThat(FOUR.ordinal()).isEqualTo(10);
        assertThat(THREE.ordinal()).isEqualTo(11);
        assertThat(TWO.ordinal()).isEqualTo(12);
    }

    @ParameterizedTest
    @CsvSource({
            "ACE  , ACE, ACE, ACE, ACE  , true",
            "THREE, THREE, THREE, THREE, THREE  , true",
            "ACE  , ACE, ACE, ACE, QUEEN, false"
    })
    void fiveOfAKindHandCanBeDerived(CamelCards.Card c1, CamelCards.Card c2, CamelCards.Card c3, CamelCards.Card c4, CamelCards.Card c5, boolean expected){
        assertThat(CamelCards.Hand.FIVE_OF_A_KIND.derive().test(List.of(c1, c2, c3, c4, c5))).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "ACE, ACE, ACE, ACE, ACE  , false",
            "ACE, ACE, ACE, QUEEN, QUEEN  , false",
            "TWO, THREE, THREE, THREE, TWO, false",
            "ACE, ACE, ACE, ACE, QUEEN, true"
    })
    void fourOfAKindHandCanBeDerived(CamelCards.Card c1, CamelCards.Card c2, CamelCards.Card c3, CamelCards.Card c4, CamelCards.Card c5, boolean expected){
        assertThat(CamelCards.Hand.FOUR_OF_A_KIND.derive().test(List.of(c1, c2, c3, c4, c5))).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "ACE, ACE, ACE, ACE, ACE  , false",
            "ACE, ACE, ACE, QUEEN, QUEEN  , true",
            "ACE, ACE, ACE, QUEEN, JACK  , false",
            "ACE, ACE, ACE, ACE, QUEEN, false",
            "TWO, THREE, THREE, THREE, TWO, true",
    })
    void fullHouseHandCanBeDerived(CamelCards.Card c1, CamelCards.Card c2, CamelCards.Card c3, CamelCards.Card c4, CamelCards.Card c5, boolean expected){
        assertThat(CamelCards.Hand.FULL_HOUSE.derive().test(List.of(c1, c2, c3, c4, c5))).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "ACE, ACE, ACE, ACE, ACE  , false",
            "ACE, ACE, ACE, QUEEN, QUEEN  , false",
            "ACE, ACE, ACE, QUEEN, JACK  , true",
            "ACE, ACE, ACE, ACE, QUEEN, false",
            "TWO, THREE, THREE, THREE, TWO, false",
            "TEN, THREE, THREE, THREE, JACK, true",
    })
    void threeOfAKindHandCanBeDerived(CamelCards.Card c1, CamelCards.Card c2, CamelCards.Card c3, CamelCards.Card c4, CamelCards.Card c5, boolean expected){
        assertThat(CamelCards.Hand.THREE_OF_A_KIND.derive().test(List.of(c1, c2, c3, c4, c5))).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "ACE, ACE, ACE, ACE, ACE  , false",
            "ACE, ACE, ACE, QUEEN, QUEEN  , false",
            "ACE, ACE, ACE, QUEEN, JACK  , false",
            "ACE, ACE, ACE, ACE, QUEEN, false",
            "TWO, THREE, THREE, THREE, TWO, false",
            "TEN, THREE, THREE, THREE, JACK, false",
            "TWO, THREE, FOUR, THREE, TWO, true",
    })
    void twoPairHandCanBeDerived(CamelCards.Card c1, CamelCards.Card c2, CamelCards.Card c3, CamelCards.Card c4, CamelCards.Card c5, boolean expected){
        assertThat(CamelCards.Hand.TWO_PAIR.derive().test(List.of(c1, c2, c3, c4, c5))).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "ACE, ACE, ACE, ACE, ACE  , false",
            "ACE, ACE, ACE, QUEEN, QUEEN  , false",
            "ACE, ACE, ACE, QUEEN, JACK  , false",
            "ACE, ACE, ACE, ACE, QUEEN, false",
            "TWO, THREE, THREE, THREE, TWO, false",
            "TEN, THREE, THREE, THREE, JACK, false",
            "TWO, THREE, FOUR, THREE, TWO, false",
            "ACE, TWO, THREE, ACE, FOUR, true",
    })
    void onePairHandCanBeDerived(CamelCards.Card c1, CamelCards.Card c2, CamelCards.Card c3, CamelCards.Card c4, CamelCards.Card c5, boolean expected){
        assertThat(CamelCards.Hand.ONE_PAIR.derive().test(List.of(c1, c2, c3, c4, c5))).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "ACE, ACE, ACE, ACE, ACE       , false",
            "ACE, ACE, ACE, QUEEN, QUEEN   , false",
            "ACE, ACE, ACE, QUEEN, JACK    , false",
            "ACE, ACE, ACE, ACE, QUEEN     , false",
            "TWO, THREE, THREE, THREE, TWO , false",
            "TEN, THREE, THREE, THREE, JACK, false",
            "TWO, THREE, FOUR, THREE, TWO  , false",
            "ACE, TWO, THREE, ACE, FOUR    , false",
            "TWO, THREE, FOUR, FIVE, SIX   , true",
    })
    void highCardHandCanBeDerived(CamelCards.Card c1, CamelCards.Card c2, CamelCards.Card c3, CamelCards.Card c4, CamelCards.Card c5, boolean expected){
        assertThat(CamelCards.Hand.HIGH_CARD.derive().test(List.of(c1, c2, c3, c4, c5))).isEqualTo(expected);
    }


    @Test
    void readLine() {
        assertThat(CamelCards.readLine("94J8A 16")).isEqualTo(new CamelCards.CamelCard(List.of(NINE, FOUR, JACK, EIGHT, ACE), 16));
        assertThat(CamelCards.readLine("JK59A 722")).isEqualTo(new CamelCards.CamelCard(List.of(JACK, KING, FIVE, NINE, ACE), 722));
    }
}