package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.FlushHand;

import static com.murex.Result.aMatchResult;

public class FlushCardRanking extends HandRanking {

    private final FlushHand blackFlushHand;
    private final FlushHand whiteFlushHand;

    public FlushCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        blackFlushHand = new FlushHand(blackHand);
        whiteFlushHand = new FlushHand(whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if(blackFlushHand.isFlush()) {
            return aMatchResult("Black wins. - with flush");
        }

        if(whiteFlushHand.isFlush()) {
            return aMatchResult("White wins. - with flush");
        }

        return super.getMatchingResult();
    }

}
