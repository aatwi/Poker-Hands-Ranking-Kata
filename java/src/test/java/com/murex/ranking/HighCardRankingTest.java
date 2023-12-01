package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HighCardRankingTest {

    private static HighCardRanking buildHighCardRanking(String black, String white) {
        return new HighCardRanking(Hand.buildFrom("Black", black), Hand.buildFrom("White", white));
    }

    private static void assertMatchingResult(String blackCards, String whiteCards, String expected) {
        HighCardRanking highCardRanking = buildHighCardRanking(blackCards, whiteCards);

        assertEquals(Result.aMatchResult(expected), highCardRanking.getMatchingResult());
    }

    @Test
    public void
    HIGH_CARD_white_wins_with_Ace() {
        assertMatchingResult("2H 3D 5S 9C KD", "2C 3H 4S 8C AH", "White wins. - with high card: Ace");
    }

    @Test
    public void
    HIGH_CARD_black_wins_with_Queen() {
        assertMatchingResult("2H 3D 5S 9C QD", "2C 3H 4S 8C JH", "Black wins. - with high card: Queen");
    }

    @Test
    public void
    HIGH_CARD_white_wins_Jack() {
        assertMatchingResult("2H 3D 5S 7C 9D", "2C 3H 4S 8C JH", "White wins. - with high card: Jack");
    }

    @Test
    public void
    HIGH_CARD_black_wins_with_9() {
        assertMatchingResult("2H 3D 5S 8C 9D", "2C 3H 4S 7C 8H", "Black wins. - with high card: 9");
    }


}