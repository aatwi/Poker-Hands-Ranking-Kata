package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Test;

import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.*;

class StraightFlushHandRankingTest {

    @Test
    public void it_should_return_a_no_matching_result_when_non_of_the_players_have_straight_flush_cards() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 5C 9S KH");

        StraightFlushHandRanking handRanking = new StraightFlushHandRanking(blackHand, whiteHand);
        assertEquals(aNoMatchResult(), handRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_no_matching_result_when_both_players_have_same_straight_flush_cards() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8H 9H TH JH");
        Hand whiteHand = Hand.buildFrom("White", "7S 8S 9S TS JS");

        StraightFlushHandRanking handRanking = new StraightFlushHandRanking(blackHand, whiteHand);
        assertEquals(aNoMatchResult(), handRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3D 4D 5D 6D");

        StraightFlushHandRanking handRanking = new StraightFlushHandRanking(blackHand, whiteHand);
        assertEquals(Result.aStraightFlushWinningResult(whiteHand, false), handRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner() {
        Hand blackHand = Hand.buildFrom("Black", "2D 3D 4D 5D 6D");
        Hand whiteHand = Hand.buildFrom("White", "7H 8C TD KH AS");

        StraightFlushHandRanking handRanking = new StraightFlushHandRanking(blackHand, whiteHand);
        assertEquals(Result.aStraightFlushWinningResult(blackHand, false), handRanking.getMatchingResult());
    }


    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_with_higher_hand() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8H 9H TH JH");
        Hand whiteHand = Hand.buildFrom("White", "2D 3D 4D 5D 6D");

        StraightFlushHandRanking handRanking = new StraightFlushHandRanking(blackHand, whiteHand);
        assertEquals(Result.aStraightFlushWinningResult(blackHand, true), handRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_with_higher_hand() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8H 9H TH JH");
        Hand whiteHand = Hand.buildFrom("White", "8D 9D TD JD QD");

        StraightFlushHandRanking handRanking = new StraightFlushHandRanking(blackHand, whiteHand);
        assertEquals(Result.aStraightFlushWinningResult(whiteHand, true), handRanking.getMatchingResult());
    }
}