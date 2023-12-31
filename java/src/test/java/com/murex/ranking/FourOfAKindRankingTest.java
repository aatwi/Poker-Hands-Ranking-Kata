package com.murex.ranking;

import com.murex.Hand;
import org.junit.jupiter.api.Test;

import static com.murex.ResultHelper.aFourOfAKindWinningResult;
import static com.murex.ResultHelper.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FourOfAKindRankingTest {

    @Test
    public void it_should_return_a_no_matching_result_when_neither_hands_have_full_house() {
        Hand blackHand = Hand.buildFrom("Black", "3H 3S 4C AH AD");
        Hand whiteHand = Hand.buildFrom("White", "4D 5S 7D JS AC");

        FourOfAKindRanking fourOfAKindRanking = new FourOfAKindRanking(blackHand, whiteHand);
        assertEquals(aNoMatchResult(), fourOfAKindRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_result_with_black_as_a_winner() {
        Hand blackHand = Hand.buildFrom("Black", "3H 3S 3C 3D AD");
        Hand whiteHand = Hand.buildFrom("White", "4D 5S 7D JS AC");

        FourOfAKindRanking fourOfAKindRanking = new FourOfAKindRanking(blackHand, whiteHand);
        assertEquals(aFourOfAKindWinningResult(blackHand, false), fourOfAKindRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_result_with_white_as_a_winner() {
        Hand blackHand = Hand.buildFrom("Black", "4D 5S 7D JS AC");
        Hand whiteHand = Hand.buildFrom("White", "2H 4S 4C 4D 4H");

        FourOfAKindRanking fourOfAKindRanking = new FourOfAKindRanking(blackHand, whiteHand);
        assertEquals(aFourOfAKindWinningResult(whiteHand, false), fourOfAKindRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_result_with_black_as_a_winner_with_higher_hand() {
        Hand blackHand = Hand.buildFrom("Black", "7D 7S 7H 7C AC");
        Hand whiteHand = Hand.buildFrom("White", "2H 4S 4C 4D 4H");

        FourOfAKindRanking fourOfAKindRanking = new FourOfAKindRanking(blackHand, whiteHand);
        assertEquals(aFourOfAKindWinningResult(blackHand, true), fourOfAKindRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_result_with_white_as_a_winner_with_higher_hand() {
        Hand blackHand = Hand.buildFrom("Black", "7D 7S 7H 7C AC");
        Hand whiteHand = Hand.buildFrom("White", "2H TS TC TD TH");

        FourOfAKindRanking fourOfAKindRanking = new FourOfAKindRanking(blackHand, whiteHand);
        assertEquals(aFourOfAKindWinningResult(whiteHand, true), fourOfAKindRanking.getMatchingResult());
    }

}