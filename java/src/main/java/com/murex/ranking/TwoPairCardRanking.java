package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

public class TwoPairCardRanking extends PokerHandRanking{
    public TwoPairCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        return Result.aMatchResult("Black wins. - with two pairs: 7 and Ten");
    }
}
