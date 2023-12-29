package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.RoyalFlushHand;

public class RoyalFlushHandRanking extends HandRanking{

    private final RoyalFlushHand whiteRoyalFlushHand;
    private final RoyalFlushHand blackRoyalFlushHand;

    public RoyalFlushHandRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        whiteRoyalFlushHand = new RoyalFlushHand(whiteHand);
        blackRoyalFlushHand = new RoyalFlushHand(blackHand);
    }

    @Override
    public Result getMatchingResult() {
        if (whiteRoyalFlushHand.isRoyalFlush()) {
            return Result.aMatchResult("White wins. - with royal flush");
        }
        if (blackRoyalFlushHand.isRoyalFlush()) {
            return Result.aMatchResult("Black wins. - with royal flush");
        }
        return super.getMatchingResult();
    }

}
 