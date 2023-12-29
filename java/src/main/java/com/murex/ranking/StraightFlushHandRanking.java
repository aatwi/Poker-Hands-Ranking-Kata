package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.FlushHand;
import com.murex.hands.StraightHand;

public class StraightFlushHandRanking extends HandRanking{
    public StraightFlushHandRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        StraightHand straightHand = new StraightHand(whiteHand);
        FlushHand flushHand = new FlushHand(whiteHand);
        if(straightHand.isStraight() && flushHand.isFlush()) {
            return Result.aMatchResult("White wins. - with straight flush");
        }
        return super.getMatchingResult();
    }
}
