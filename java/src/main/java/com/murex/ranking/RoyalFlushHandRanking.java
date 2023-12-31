package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.ResultHelper;
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
        if(whiteRoyalFlushHand.isRoyalFlush() && blackRoyalFlushHand.isRoyalFlush()) {
            return ResultHelper.aNoMatchResult();
        }
        if (whiteRoyalFlushHand.isRoyalFlush()) {
            return ResultHelper.aRoyalFlushWinningResult(whiteRoyalFlushHand.getHand());
        }
        if (blackRoyalFlushHand.isRoyalFlush()) {
            return ResultHelper.aRoyalFlushWinningResult(blackRoyalFlushHand.getHand());
        }
        return super.getMatchingResult();
    }

}
 