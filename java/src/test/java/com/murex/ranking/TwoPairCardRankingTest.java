package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TwoPairCardRankingTest {

    @Test
    public void it_should_return_a_matching_result_when_one_() {
        Hand blackHand = Hand.buildFrom("Black", "7H 7C TD TH AS");
        Hand whiteHand = Hand.buildFrom("White", "1H 4C 5H 7D AD");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Assertions.assertEquals(Result.aMatchResult("Black wins. - with two pairs: 7 and Ten"), twoPairCardRanking.getMatchingResult());
    }

}