package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.ResultHelper;
import com.murex.hands.FlushHand;
import com.murex.hands.HighHand;

public class FlushHandRanking extends HandRanking {

    private final FlushHand blackFlushHand;
    private final FlushHand whiteFlushHand;

    public FlushHandRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        blackFlushHand = new FlushHand(blackHand);
        whiteFlushHand = new FlushHand(whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if(blackFlushHand.isFlush() && whiteFlushHand.isFlush()) {
            HighCardRanking highCardRanking = new HighCardRanking(blackHand, whiteHand);
            if (highCardRanking.getHigherHand().isEmpty()) {
                return ResultHelper.aNoMatchResult();
            }
            HighHand highHand = highCardRanking.getHigherHand().get();
            return ResultHelper.aFlushWinningResult(highHand.getHand(), true);
        }

        if(blackFlushHand.isFlush()) {
            return ResultHelper.aFlushWinningResult(blackFlushHand.getHand(), false);
        }

        if(whiteFlushHand.isFlush()) {
            return ResultHelper.aFlushWinningResult(whiteFlushHand.getHand(), false);
        }

        return super.getMatchingResult();
    }

}
