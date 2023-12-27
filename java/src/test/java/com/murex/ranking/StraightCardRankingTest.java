package com.murex.ranking;

import com.murex.Hand;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StraightCardRankingTest {

    @Test
    public void it_should_return_a_no_matching_result_when_non_of_the_players_have_straight_cards() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 5C 9S KH");

        StraightCardRanking straightCardRanking = new StraightCardRanking(blackHand, whiteHand);
        assertEquals(aNoMatchResult(), straightCardRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_straight_cards_2_to_6() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 4C 5S 6H");

        StraightCardRanking straightCardRanking = new StraightCardRanking(blackHand, whiteHand);
        assertEquals(aMatchResult("White wins. - with straight cards"), straightCardRanking.getMatchingResult());
    }

    @Disabled
    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_straight_cards_4_to_8() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White", "4D 5H 6C 7S 8H");

        StraightCardRanking straightCardRanking = new StraightCardRanking(blackHand, whiteHand);
        assertEquals(aMatchResult("White wins. - with straight cards"), straightCardRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_straight_cards_7_to_Jack() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C 9D TH JS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 7C JH AD");

        StraightCardRanking straightCardRanking = new StraightCardRanking(blackHand, whiteHand);
        assertEquals(aMatchResult("Black wins. - with straight cards"), straightCardRanking.getMatchingResult());
    }

}