package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.FlushHand;

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
        if(isFlush(blackHand, blackFlushHand)) {
            return Result.aMatchResult("Black wins. - with flush");
        }

        if(isFlush(whiteHand, whiteFlushHand)) {
            return Result.aMatchResult("White wins. - with flush");
        }

        return super.getMatchingResult();
    }

    public boolean isFlush(Hand blackHand, FlushHand flushHand) {
        for (int index = 1; index < 5; index++) {
            if (flushHand.getHand().getCardAt(index - 1).getSuite() != flushHand.getHand().getCardAt(index).getSuite()) {
                return false;
            }
        }
        return true;
    }
}
