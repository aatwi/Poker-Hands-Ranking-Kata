package com.murex.ranking;

import com.murex.Hand;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.*;

class StraightFlushHandRankingTest {

    private static void assertNonMatchingResult(String blackCards, String whiteCards) {
        StraightFlushHandRanking handRanking = buildStraightFlushHandRanking(blackCards, whiteCards);
        assertEquals(aNoMatchResult(), handRanking.getMatchingResult());
    }

    private static void assertMatchingResult(String blackCards, String whiteCards, String message) {
        StraightFlushHandRanking handRanking = buildStraightFlushHandRanking(blackCards, whiteCards);
        assertEquals(aMatchResult(message), handRanking.getMatchingResult());
    }
    private static StraightFlushHandRanking buildStraightFlushHandRanking(String blackCards, String whiteCards) {
        Hand blackHand = Hand.buildFrom("Black", blackCards);
        Hand whiteHand = Hand.buildFrom("White", whiteCards);

        return new StraightFlushHandRanking(blackHand, whiteHand);
    }

    @Test
    public void it_should_return_a_no_matching_result_when_non_of_the_players_have_straight_flush_cards() {
        assertNonMatchingResult("7H 8C TD KH AS", "2D 3H 5C 9S KH");
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner() {
        assertMatchingResult(
                "7H 8C TD KH AS",
                "2D 3D 4D 5D 6D",
                "White wins. - with straight flush");
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner() {
        assertMatchingResult(
                "2D 3D 4D 5D 6D",
                "7H 8C TD KH AS",
                "Black wins. - with straight flush");
    }

    @Disabled
    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_with_higher_hand() {
        assertMatchingResult(
                "7H 8H 9H TH JH",
                "2D 3D 4D 5D 6D",
                "Black wins. - with straight flush and higher hand");
    }
}