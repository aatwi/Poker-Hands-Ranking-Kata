package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.FlushHand;
import com.murex.hands.HighHand;

import static com.murex.Result.*;

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
                return aNoMatchResult();
            }
            HighHand highHand = highCardRanking.getHigherHand().get();
            return Result.aFlushWinningResult(highHand.getHand(), true);
        }

        if(blackFlushHand.isFlush()) {
            return Result.aFlushWinningResult(blackFlushHand.getHand(), false);
        }

        if(whiteFlushHand.isFlush()) {
            return Result.aFlushWinningResult(whiteFlushHand.getHand(), false);
        }

        return super.getMatchingResult();
    }

}
