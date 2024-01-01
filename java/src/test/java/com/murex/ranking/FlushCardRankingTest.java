package com.murex.ranking;

import com.murex.Hand;
import com.murex.ResultHelper;
import org.junit.jupiter.api.Test;

import static com.murex.HandBuilder.*;
import static com.murex.ResultHelper.aTieResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FlushCardRankingTest {

    @Test
    void it_should_return_a_non_matching_result_when_both_hands_have_no_flush() {
        Hand blackHand = aHand().withPlayer("Black").withCards("2D 4H 6S KC AH").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("4D 5S 7D JS AC").build();

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        
        assertEquals(ResultHelper.aNoWinner(), flushHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_hearts() {
        Hand blackHand = aHand().withPlayer("Black").withCards("3H 4H 6H KH AH").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("4D 5S 7D JS AC").build();

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aFlushWinningResult(blackHand, false), flushHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_diamonds() {
        Hand blackHand = aHand().withPlayer("Black").withCards("3D 4D 6D KD AD").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("2D 5S 7D JS AC").build();

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aFlushWinningResult(blackHand, false), flushHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_clubs() {
        Hand blackHand = aHand().withPlayer("Black").withCards("3C 4C 6C KC AC").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("2D 5S 7D JS AD").build();

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aFlushWinningResult(blackHand, false), flushHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_spades() {
        Hand blackHand = aHand().withPlayer("Black").withCards("3S 4S 6S KS AS").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("2D 5S 7D JS AD").build();

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aFlushWinningResult(blackHand, false), flushHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_flush_with_spades() {
        Hand blackHand = aHand().withPlayer("Black").withCards("2D 5S 7D JS AD").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("3S 4S 6S KS AS").build();

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aFlushWinningResult(whiteHand, false), flushHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_flush_with_spades_and_higher_hands() {
        Hand blackHand = aHand().withPlayer("Black").withCards("2D 5D 7D JD AD").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("3S 4S 6S KS AS").build();

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aFlushWinningResult(whiteHand, true), flushHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_spades_and_higher_hands() {
        Hand blackHand = aHand().withPlayer("Black").withCards("2D 5D 7D JD AD").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("3S 4S 6S JS QS").build();

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aFlushWinningResult(blackHand, true), flushHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_tie_when_the_two_hands_have_flush_and_equal_card_values() {
        Hand blackHand = aHand().withPlayer("Black").withCards("3D 4D 6D JD QD").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("3S 4S 6S JS QS").build();

        FlushOrderRanking flushHandRanking = new FlushOrderRanking(blackHand, whiteHand);
        assertEquals(aTieResult(), flushHandRanking.evaluate());
    }
}
