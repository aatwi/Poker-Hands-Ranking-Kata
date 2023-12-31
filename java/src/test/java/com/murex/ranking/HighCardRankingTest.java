package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.HighHand;
import org.junit.jupiter.api.Test;

import static com.murex.Hand.buildFrom;
import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HighCardRankingTest {

    private static HighCardRanking buildHighCardRanking(String black, String white) {
        return new HighCardRanking(buildFrom("Black", black), buildFrom("White", white));
    }

    private static void assertMatchingResult(String blackCards, String whiteCards, String expected) {
        HighCardRanking highCardRanking = buildHighCardRanking(blackCards, whiteCards);

        assertEquals(Result.aMatchResult(expected), highCardRanking.getMatchingResult());
    }

    @Test
    public void
    it_should_return_a_no_matching_result_when_there_is_no_high_hand() {
        assertEquals(aNoMatchResult(), buildHighCardRanking("2H 3D 5S 9C KD", "2D 3S 5C 9D KC").getMatchingResult());
    }

    @Test
    public void
    HIGH_CARD_white_wins_with_Ace() {
        assertMatchingResult("2H 3D 5S 9C KD", "2C 3H 4S 8C AH", "White wins. - with high card: ACE");
    }

    @Test
    public void
    HIGH_CARD_black_wins_with_Queen() {
        assertMatchingResult("2H 3D 5S 9C QD", "2C 3H 4S 8C JH", "Black wins. - with high card: QUEEN");
    }

    @Test
    public void
    HIGH_CARD_white_wins_Jack() {
        assertMatchingResult("2H 3D 5S 7C 9D", "2C 3H 4S 8C JH", "White wins. - with high card: JACK");
    }

    @Test
    public void
    HIGH_CARD_black_wins_with_9() {
        assertMatchingResult("2H 3D 5S 8C 9D", "2C 3H 4S 7C 8H", "Black wins. - with high card: NINE");
    }

    @Test
    public void
    white_wins_with_9() {
        assertMatchingResult("2H 3D 5S 8C AD", "2S 3C 5D 9S AD", "White wins. - with high card: NINE");
    }

    @Test
    public void
    get_the_higher_hand() {
        Hand blackHand = buildFrom("Black", "2H 3D 5S 8C AD");
        Hand whiteHand = buildFrom("White", "2S 3C 5D 9S AD");

        HighCardRanking highCardRanking1 = new HighCardRanking(blackHand, whiteHand);
        HighHand higherHand = highCardRanking1.getHigherHand().get();
        assertEquals(whiteHand, higherHand.getHand());
    }

}