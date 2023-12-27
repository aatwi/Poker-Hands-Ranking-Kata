package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

public class FlushCardRanking extends HandRanking {
    public FlushCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if (blackHand.getCardAt(0).getSuite() == 'H' && blackHand.getCardAt(1).getSuite() == 'H' && blackHand.getCardAt(2).getSuite() == 'H'
        && blackHand.getCardAt(3).getSuite() == 'H' && blackHand.getCardAt(4).getSuite() == 'H'){
            return Result.aMatchResult("Black wins. - with flush");
        }
        if (blackHand.getCardAt(0).getSuite() == 'D' && blackHand.getCardAt(1).getSuite() == 'D' && blackHand.getCardAt(2).getSuite() == 'D'
                && blackHand.getCardAt(3).getSuite() == 'D' && blackHand.getCardAt(4).getSuite() == 'D'){
            return Result.aMatchResult("Black wins. - with flush");
        }

        if (blackHand.getCardAt(0).getSuite() == 'C' && blackHand.getCardAt(1).getSuite() == 'C' && blackHand.getCardAt(2).getSuite() == 'C'
                && blackHand.getCardAt(3).getSuite() == 'C' && blackHand.getCardAt(4).getSuite() == 'C'){
            return Result.aMatchResult("Black wins. - with flush");
        }
        return super.getMatchingResult();
    }
}
