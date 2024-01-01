package com.murex.ranking;

import com.murex.Hand;
import com.murex.ResultHelper;
import org.junit.jupiter.api.Test;

import static com.murex.ResultHelper.aNoWinner;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FlushCardRankingTest {

    @Test
    void it_should_return_a_non_matching_result_when_both_hands_have_no_flush() {
        Hand blackHand = Hand.buildFrom("Black", "2D 4H 6S KC AH");
        Hand whiteHand = Hand.buildFrom("White", "4D 5S 7D JS AC");

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        
        assertEquals(ResultHelper.aNoWinner(), flushHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_hearts() {
        Hand blackHand = Hand.buildFrom("Black", "3H 4H 6H KH AH");
        Hand whiteHand = Hand.buildFrom("White", "4D 5S 7D JS AC");

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aFlushWinningResult(blackHand, false), flushHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_diamonds() {
        Hand blackHand = Hand.buildFrom("Black", "3D 4D 6D KD AD");
        Hand whiteHand = Hand.buildFrom("White", "2D 5S 7D JS AC");

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aFlushWinningResult(blackHand, false), flushHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_clubs() {
        Hand blackHand = Hand.buildFrom("Black", "3C 4C 6C KC AC");
        Hand whiteHand = Hand.buildFrom("White", "2D 5S 7D JS AD");

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aFlushWinningResult(blackHand, false), flushHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_spades() {
        Hand blackHand = Hand.buildFrom("Black", "3S 4S 6S KS AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 5S 7D JS AD");

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aFlushWinningResult(blackHand, false), flushHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_flush_with_spades() {
        Hand blackHand = Hand.buildFrom("Black", "2D 5S 7D JS AD");
        Hand whiteHand = Hand.buildFrom("White", "3S 4S 6S KS AS");

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aFlushWinningResult(whiteHand, false), flushHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_flush_with_spades_and_higher_hands() {
        Hand blackHand = Hand.buildFrom("Black", "2D 5D 7D JD AD");
        Hand whiteHand = Hand.buildFrom("White", "3S 4S 6S KS AS");

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aFlushWinningResult(whiteHand, true), flushHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_spades_and_higher_hands() {
        Hand blackHand = Hand.buildFrom("Black", "2D 5D 7D JD AD");
        Hand whiteHand = Hand.buildFrom("White", "3S 4S 6S JS QS");

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aFlushWinningResult(blackHand, true), flushHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_no_matching_result_when_the_two_hands_have_flush_and_equal_card_values() {
        Hand blackHand = Hand.buildFrom("Black", "3D 4D 6D JD QD");
        Hand whiteHand = Hand.buildFrom("White", "3S 4S 6S JS QS");

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        assertEquals(aNoWinner(), flushHandRanking.evaluate());
    }
}
