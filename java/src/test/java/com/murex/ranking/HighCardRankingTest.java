package com.murex.ranking;

import com.murex.Hand;
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
        assertMatchingResult("Black wins. - with high card: Queen", "2H 3D 5S 9C QD", "2C 3H 4S 8C JH");
    }

    @Test
    public void
    HIGH_CARD_white_wins_Jack(){
        assertMatchingResult("White wins. - with high card: Jack", "2H 3D 5S 7C 9D", "2C 3H 4S 8C JH");
    }

    @Test
    public void
    HIGH_CARD_black_wins_with_9(){
        assertMatchingResult("Black wins. - with high card: 9", "2H 3D 5S 8C 9D", "2C 3H 4S 7C 8H");
    }

    private static void assertMatchingResult(String expected, String black, String white) {
        HighCardRanking highCardRanking = buildHighCardRanking(black, white);

        assertEquals(expected, highCardRanking.getMatchingResult().message());
    }


}