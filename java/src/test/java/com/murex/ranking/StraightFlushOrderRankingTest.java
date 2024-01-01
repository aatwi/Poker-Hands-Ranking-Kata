package com.murex.ranking;

import com.murex.Hand;
import com.murex.ResultHelper;
import org.junit.jupiter.api.Test;

import static com.murex.ResultHelper.aNoWinner;
import static org.junit.jupiter.api.Assertions.*;

class StraightFlushOrderRankingTest {

    @Test
    public void it_should_return_a_no_matching_result_when_non_of_the_players_have_straight_flush_cards() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 5C 9S KH");

        StraightFlushOrderRanking handRanking = new StraightFlushOrderRanking(blackHand, whiteHand);
        assertEquals(aNoWinner(), handRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_no_matching_result_when_both_players_have_same_straight_flush_cards() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8H 9H TH JH");
        Hand whiteHand = Hand.buildFrom("White", "7S 8S 9S TS JS");

        StraightFlushOrderRanking handRanking = new StraightFlushOrderRanking(blackHand, whiteHand);
        assertEquals(aNoWinner(), handRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3D 4D 5D 6D");

        StraightFlushOrderRanking handRanking = new StraightFlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aStraightFlushWinningResult(whiteHand, false), handRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner() {
        Hand blackHand = Hand.buildFrom("Black", "2D 3D 4D 5D 6D");
        Hand whiteHand = Hand.buildFrom("White", "7H 8C TD KH AS");

        StraightFlushOrderRanking handRanking = new StraightFlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aStraightFlushWinningResult(blackHand, false), handRanking.getMatchingResult());
    }


    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_with_higher_hand() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8H 9H TH JH");
        Hand whiteHand = Hand.buildFrom("White", "2D 3D 4D 5D 6D");

        StraightFlushOrderRanking handRanking = new StraightFlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aStraightFlushWinningResult(blackHand, true), handRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_with_higher_hand() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8H 9H TH JH");
        Hand whiteHand = Hand.buildFrom("White", "8D 9D TD JD QD");

        StraightFlushOrderRanking handRanking = new StraightFlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aStraightFlushWinningResult(whiteHand, true), handRanking.getMatchingResult());
    }
}