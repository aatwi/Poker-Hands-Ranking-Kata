package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HighCardRankingTest {

    @Test
    public void
    HIGH_CARD_white_wins_with_Ace(){
        HighCardRanking highCardRanking = buildHighCardRanking("2H 3D 5S 9C KD", "2C 3H 4S 8C AH");
        String expected = "White wins. - with high card: Ace";

        assertEquals(expected, highCardRanking.getMatchingResult().message());
    }

    private static HighCardRanking buildHighCardRanking(String black, String white) {

        return new HighCardRanking(Hand.buildFrom("Black", black), Hand.buildFrom("White", white));
    }

    @Test
    public void
    HIGH_CARD_black_wins_with_Queen(){
        assertMatchingResult("2H 3D 5S 9C QD", "2C 3H 4S 8C JH", "Black wins. - with high card: Queen");
    }

    @Test
    public void
    HIGH_CARD_white_wins_Jack(){
        assertMatchingResult("2H 3D 5S 7C 9D", "2C 3H 4S 8C JH", "White wins. - with high card: Jack");
    }

    @Test
    public void
    HIGH_CARD_black_wins_with_9(){
        assertMatchingResult("2H 3D 5S 8C 9D", "2C 3H 4S 7C 8H", "Black wins. - with high card: 9");
    }

    private static void assertMatchingResult(String blackCards, String whiteCards, String expected) {
        HighCardRanking highCardRanking = buildHighCardRanking(blackCards, whiteCards);

        assertEquals(Result.aMatchResult(expected), highCardRanking.getMatchingResult());
    }


}