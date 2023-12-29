package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

public class FourOfAKindRanking extends HandRanking {
    public FourOfAKindRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if (blackHand.getCardAt(0).getCharValue() == blackHand.getCardAt(1).getCharValue()
                && blackHand.getCardAt(1).getCharValue() == blackHand.getCardAt(2).getCharValue()
                && blackHand.getCardAt(2).getCharValue() == blackHand.getCardAt(3).getCharValue()) {
            return Result.aMatchResult("Black wins. - with four of a kind");
        }
        return super.getMatchingResult();
    }
}
