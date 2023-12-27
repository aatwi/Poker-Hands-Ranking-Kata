package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.FlushHand;
import com.murex.hands.HighHand;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;

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
        if(blackFlushHand.isFlush() && whiteFlushHand.isFlush()) {
            HighCardRanking highCardRanking = new HighCardRanking(blackHand, whiteHand);
            if (highCardRanking.getHigherHand().isEmpty()) {
                return aNoMatchResult();
            }
            HighHand highHand = highCardRanking.getHigherHand().get();
            return aMatchResult(highHand.getHand().getName() + " wins. - with flush and higher hand");
        }

        if(blackFlushHand.isFlush()) {
            return aMatchResult("Black wins. - with flush");
        }

        if(whiteFlushHand.isFlush()) {
            return aMatchResult("White wins. - with flush");
        }

        return super.getMatchingResult();
    }

}
