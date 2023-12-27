package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.murex.Result.aMatchResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FlushCardRankingTest {

    @Test
    void it_should_return_a_non_matching_result_when_both_hands_have_no_flush() {
        Hand blackHand = Hand.buildFrom("Black", "2H 4H 6H KH AH");
        Hand whiteHand = Hand.buildFrom("White", "4D 5S 7D JS AC");
        
        FlushCardRanking flushCardRanking = new FlushCardRanking(blackHand, whiteHand);
        Assertions.assertEquals(Result.aNoMatchResult(), flushCardRanking.getMatchingResult());
    }

    @Disabled
    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_flush() {
        Hand blackHand = Hand.buildFrom("Black", "2H 4H 6H KH AH");
        Hand whiteHand = Hand.buildFrom("White", "4D 5S 7D JS AC");
        FlushCardRanking flushCardRanking = new FlushCardRanking(blackHand, whiteHand);

        assertEquals(aMatchResult("Black wins. - with flush"), flushCardRanking.getMatchingResult());
    }
}