package com.murex.ranking;

import com.murex.Hand;
import org.junit.jupiter.api.Test;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreeOfAKindRankingTest {

    private static ThreeOfAKindRanking buildThreeOfAKindRanking(String blackHandCards, String whiteHandCards) {
        Hand blackHand = Hand.buildFrom("Black", blackHandCards);
        Hand whiteHand = Hand.buildFrom("White", whiteHandCards);
        return new ThreeOfAKindRanking(blackHand, whiteHand);
    }

    private static void assertNoMatchingResult(String blackHandCards, String whiteHandCards) {
        assertEquals(aNoMatchResult(), buildThreeOfAKindRanking(blackHandCards, whiteHandCards).getMatchingResult());
    }

    private static void assertMatchingResult(String blackHandCards, String whiteHandCards, String message) {
        assertEquals(aMatchResult(message), buildThreeOfAKindRanking(blackHandCards, whiteHandCards).getMatchingResult());
    }

    @Test
    public void it_should_return_a_non_matching_result_when_both_hands_have_no_three_cards_with_same_kind() {
        assertNoMatchingResult("7H 8C TD KH AS", "2D 3H 5C 9S KH");
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_three_cards_of_9() {
        assertMatchingResult(
                "7H 7C TD KH AS",
                "2D 9H 9C 9S KH",
                "White wins. - with three of a kind: NINE");
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_three_cards_of_10() {
        assertMatchingResult(
                "7H 7C TD KH AS",
                "2D 9H TC TS TH",
                "White wins. - with three of a kind: TEN");
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_three_cards_of_7() {
        assertMatchingResult(
                "7H 7C 7D KH AS",
                "2D 9H 8C TS KH",
                "Black wins. - with three of a kind: SEVEN");
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_three_cards_of_Ace_over_white_having_three_cards_of_9() {
        assertMatchingResult(
                "2H 3C AD AH AS",
                "2D 9H 9C 9S KH",
                "Black wins. - with three of a kind: ACE");
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_three_cards_of_Jack_over_black_having_three_cards_of_8() {
        assertMatchingResult(
                "2H 3C 8D 8H 8S",
                "2D JH JC JS KH",
                "White wins. - with three of a kind: JACK");
    }

}