package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Test;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StraightCardRankingTest {

    @Test
    public void it_should_return_a_no_matching_result_when_non_of_the_players_have_straight_cards() {
        assertMatchingResult("7H 8C TD KH AS", "2D 3H 5C 9S KH", aNoMatchResult(), "White wins. - with straight cards and higher cards");
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_straight_cards_2_to_6() {
        assertMatchingResult("7H 8C TD KH AS", "2D 3H 4C 5S 6H", aMatchResult("White wins. - with straight cards"), "White wins. - with straight cards");
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_straight_cards_8_to_Q() {
        assertMatchingResult("7H 8C TD KH AS", "8D 9H TC JS QH", aMatchResult("White wins. - with straight cards"), "White wins. - with straight cards");
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_straight_cards_7_to_Jack() {
        assertMatchingResult("7H 8C 9D TH JS", "2D 3H 7C JH AD", aMatchResult("Black wins. - with straight cards"), "Black wins. - with straight cards");
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_straight_cards_3_to_7() {
         assertMatchingResult("3H 4C 5D 6H 7S", "2D 3H 7C JH AD", aMatchResult("Black wins. - with straight cards"), "Black wins. - with straight cards");
    }

    private static void assertMatchingResult(String blackCards, String whiteCards, Result expected, String s) {
        Hand blackHand = Hand.buildFrom("Black", blackCards);
        Hand whiteHand = Hand.buildFrom("White", whiteCards);

        StraightCardRanking straightCardRanking = new StraightCardRanking(blackHand, whiteHand);
        assertEquals(expected, straightCardRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_straight_and_higher_cards() {
        assertMatchingResult("7H 8C 9D TH JS", "2D 3H 4C 5S 6H", aMatchResult("Black wins. - with straight cards and higher cards"), "Black wins. - with straight cards and higher cards");
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_straight_and_higher_cards() {
        assertMatchingResult("4H 5C 6D 7H 8S", "6D 7H 8C 9S TH", aMatchResult("White wins. - with straight cards and higher cards"), "White wins. - with straight cards and higher cards");
    }


}