package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FourOfAKindRankingTest {

    private static FourOfAKindRanking buildFourOfAKindCardRanking(String blackCards, String whiteCards) {
        Hand blackHand = Hand.buildFrom("Black", blackCards);
        Hand whiteHand = Hand.buildFrom("White", whiteCards);

        return new FourOfAKindRanking(blackHand, whiteHand);
    }

    private static void assertNoMatchingResults(String blackCards, String whiteCards) {
        FourOfAKindRanking fourOfAKindRanking = buildFourOfAKindCardRanking(blackCards, whiteCards);
        assertEquals(aNoMatchResult(), fourOfAKindRanking.getMatchingResult());
    }

    private static void assertMatchingResult(String blackCards, String whiteCards, String expectedMessage) {
        FourOfAKindRanking fourOfAKindRanking = buildFourOfAKindCardRanking(blackCards, whiteCards);
        assertEquals(aMatchResult(expectedMessage), fourOfAKindRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_no_matching_result_when_neither_hands_have_full_house() {
        assertNoMatchingResults("3H 3S 4C AH AD", "4D 5S 7D JS AC");
    }

    @Test
    public void it_should_return_a_result_with_black_as_a_winner() {
        assertMatchingResult(
                "3H 3S 3C 3D AD",
                "4D 5S 7D JS AC",
                "Black wins. - with four of a kind");
    }

    @Test
    public void it_should_return_a_result_with_white_as_a_winner() {
        assertMatchingResult(
                "4D 5S 7D JS AC",
                "2H 4S 4C 4D 4H",
                "White wins. - with four of a kind");
    }

    @Test
    public void it_should_return_a_result_with_black_as_a_winner_with_higher_hand() {
        assertMatchingResult(
                "7D 7S 7H 7C AC",
                "2H 4S 4C 4D 4H",
                "Black wins. - with four of a kind and higher hand");
    }

    @Test
    public void it_should_return_a_result_with_white_as_a_winner_with_higher_hand() {
        assertMatchingResult(
                "7D 7S 7H 7C AC",
                "2H TS TC TD TH",
                "White wins. - with four of a kind and higher hand");
    }

}