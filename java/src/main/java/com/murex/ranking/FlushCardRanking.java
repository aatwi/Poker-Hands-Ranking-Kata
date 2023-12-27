package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

public class FlushCardRanking extends HandRanking {
    public FlushCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        boolean allSameSuite = isFlush(blackHand);

        if(allSameSuite) {
            return Result.aMatchResult("Black wins. - with flush");
        }

        boolean allWhiteSameSuite = isFlush(whiteHand);

        if(allWhiteSameSuite) {
            return Result.aMatchResult("White wins. - with flush");
        }

        return super.getMatchingResult();
    }

    private boolean isFlush(Hand blackHand) {
        for (int index = 1; index < 5; index++) {
            if (blackHand.getCardAt(index - 1).getSuite() != blackHand.getCardAt(index).getSuite()) {
                return false;
            }
        }
        return true;
    }
}
