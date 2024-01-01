package com.murex.ranking;

import com.murex.Hand;
import com.murex.ResultHelper;
import org.junit.jupiter.api.Test;

import static com.murex.ResultHelper.*;
import static org.junit.jupiter.api.Assertions.*;

class RoyalFlushOrderRankingTest {

    @Test
    public void it_should_return_a_no_matching_result_when_non_of_the_players_have_royal_flush_cards() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 5C 9S KH");

        RoyalFlushOrderRanking handRanking = new RoyalFlushOrderRanking(blackHand, whiteHand);
        assertEquals(aNoWinner(), handRanking.evaluate());
    }

    @Test
    public void it_should_return_a_tie_when_both_players_have_same_royal_flush_cards() {
        Hand blackHand = Hand.buildFrom("Black", "TH JH QH KH AH");
        Hand whiteHand = Hand.buildFrom("White", "TS JS QS KS AS");

        RoyalFlushOrderRanking handRanking = new RoyalFlushOrderRanking(blackHand, whiteHand);
        assertEquals(aTieResult(), handRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8H 9H TH JH");
        Hand whiteHand = Hand.buildFrom("White", "TS JS QS KS AS");

        RoyalFlushOrderRanking handRanking = new RoyalFlushOrderRanking(blackHand, whiteHand);
        assertEquals(aRoyalFlushWinningResult(whiteHand), handRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner() {
        Hand blackHand = Hand.buildFrom("Black", "TH JH QH KH AH");
        Hand whiteHand = Hand.buildFrom("White", "7H 8H 9H TH JH");

        RoyalFlushOrderRanking handRanking = new RoyalFlushOrderRanking(blackHand, whiteHand);
        assertEquals(aRoyalFlushWinningResult(blackHand), handRanking.evaluate());
    }
}