package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FlushCardRankingTest {

    private static FlushHandRanking buildFlushCardRanking(String blackCards, String whiteCards) {
        Hand blackHand = Hand.buildFrom("Black", blackCards);
        Hand whiteHand = Hand.buildFrom("White", whiteCards);

        return new FlushHandRanking(blackHand, whiteHand);
    }

    @Test
    void it_should_return_a_non_matching_result_when_both_hands_have_no_flush() {
        FlushHandRanking flushHandRanking = buildFlushCardRanking("2D 4H 6S KC AH", "4D 5S 7D JS AC");
        
        assertEquals(Result.aNoMatchResult(), flushHandRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_hearts() {
        FlushHandRanking flushHandRanking = buildFlushCardRanking("3H 4H 6H KH AH", "4D 5S 7D JS AC");
        assertEquals(aMatchResult("Black wins. - with flush"), flushHandRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_diamonds() {
        FlushHandRanking flushHandRanking = buildFlushCardRanking("3D 4D 6D KD AD", "2D 5S 7D JS AC");
        assertEquals(aMatchResult("Black wins. - with flush"), flushHandRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_clubs() {
        FlushHandRanking flushHandRanking = buildFlushCardRanking("3C 4C 6C KC AC", "2D 5S 7D JS AD");
        assertEquals(aMatchResult("Black wins. - with flush"), flushHandRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_spades() {
        FlushHandRanking flushHandRanking = buildFlushCardRanking("3S 4S 6S KS AS", "2D 5S 7D JS AD");
        assertEquals(aMatchResult("Black wins. - with flush"), flushHandRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_flush_with_spades() {
        FlushHandRanking flushHandRanking = buildFlushCardRanking("2D 5S 7D JS AD", "3S 4S 6S KS AS");
        assertEquals(aMatchResult("White wins. - with flush"), flushHandRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_flush_with_spades_and_higher_hands() {
        FlushHandRanking flushHandRanking = buildFlushCardRanking("2D 5D 7D JD AD", "3S 4S 6S KS AS");
        assertEquals(aMatchResult("White wins. - with flush and higher hand"), flushHandRanking.getMatchingResult());
    }

    @Disabled
    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_spades_and_higher_hands() {
        FlushHandRanking flushHandRanking = buildFlushCardRanking("2D 5D 7D JD AD", "3S 4S 6S JS QS");
        assertEquals(aMatchResult("Black wins. - with flush and higher hand"), flushHandRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_no_matching_result_when_the_two_hands_have_flush_and_equal_card_values() {
        FlushHandRanking flushHandRanking = buildFlushCardRanking("3D 4D 6D JD QD", "3S 4S 6S JS QS");
        assertEquals(aNoMatchResult(), flushHandRanking.getMatchingResult());
    }
}
