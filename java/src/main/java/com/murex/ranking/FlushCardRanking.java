package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

public class FlushCardRanking extends HandRanking {
    public FlushCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        boolean allSameSuite = false;
        for (int index = 1; index < 5; index++) {
            if (blackHand.getCardAt(index-1).getSuite() != blackHand.getCardAt(index).getSuite()) {
                allSameSuite = false;
                break;
            }
            allSameSuite = true;
        }

        if(allSameSuite) {
            return Result.aMatchResult("Black wins. - with flush");
        }

        boolean allWhiteSameSuite = false;
        for (int index = 1; index < 5; index++) {
            if (whiteHand.getCardAt(index-1).getSuite() != whiteHand.getCardAt(index).getSuite()) {
                allWhiteSameSuite = false;
                break;
            }
            allWhiteSameSuite = true;
        }

        if(allWhiteSameSuite) {
            return Result.aMatchResult("White wins. - with flush");
        }

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
        if (blackHand.getCardAt(0).getSuite() == 'S' && blackHand.getCardAt(1).getSuite() == 'S' && blackHand.getCardAt(2).getSuite() == 'S'
                && blackHand.getCardAt(3).getSuite() == 'S' && blackHand.getCardAt(4).getSuite() == 'S'){
            return Result.aMatchResult("Black wins. - with flush");
        }
        return super.getMatchingResult();
    }
}
