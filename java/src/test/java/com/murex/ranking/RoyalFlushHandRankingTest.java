package com.murex.ranking;

import com.murex.Hand;
import org.junit.jupiter.api.Test;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.*;

class RoyalFlushHandRankingTest {
    private static void assertNonMatchingResult(String blackCards, String whiteCards) {
        RoyalFlushHandRanking handRanking = buildStraightFlushHandRanking(blackCards, whiteCards);
        assertEquals(aNoMatchResult(), handRanking.getMatchingResult());
    }

    private static void assertMatchingResult(String blackCards, String whiteCards, String message) {
        RoyalFlushHandRanking handRanking = buildStraightFlushHandRanking(blackCards, whiteCards);
        assertEquals(aMatchResult(message), handRanking.getMatchingResult());
    }

    private static RoyalFlushHandRanking buildStraightFlushHandRanking(String blackCards, String whiteCards) {
        Hand blackHand = Hand.buildFrom("Black", blackCards);
        Hand whiteHand = Hand.buildFrom("White", whiteCards);

        return new RoyalFlushHandRanking(blackHand, whiteHand);
    }

    @Test
    public void it_should_return_a_no_matching_result_when_non_of_the_players_have_straight_flush_cards() {
        assertNonMatchingResult("7H 8C TD KH AS", "2D 3H 5C 9S KH");
    }

    @Test
    public void it_should_return_a_no_matching_result_when_both_players_have_same_straight_flush_cards() {
        assertNonMatchingResult("7H 8H 9H TH JH", "7S 8S 9S TS JS");
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner() {
        assertNonMatchingResult("7H 8H 9H TH JH", "TS JS QS KS AS");
    }
}