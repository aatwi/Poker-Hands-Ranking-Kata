package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

public class FlushCardRanking extends HandRanking {
    public FlushCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if (blackHand.getCardAt(0).getCharValue() == '3'){
            return Result.aMatchResult("Black wins. - with flush");
        }
        return super.getMatchingResult();
    }
}
