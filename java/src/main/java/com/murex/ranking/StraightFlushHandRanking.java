package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.FlushHand;
import com.murex.hands.StraightFlushHand;
import com.murex.hands.StraightHand;

public class StraightFlushHandRanking extends HandRanking{

    private final StraightHand whiteStraightHand;
    private final FlushHand whiteFlushHand;
    private final StraightHand blackStraightHand;
    private final FlushHand blackFlushHand;
    private final StraightFlushHand whiteStraightFlushHand;
    private final StraightFlushHand blackStraightFlushHand;

    public StraightFlushHandRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        whiteStraightHand = new StraightHand(whiteHand);
        whiteFlushHand = new FlushHand(whiteHand);
        whiteStraightFlushHand = new StraightFlushHand(whiteHand);
        blackStraightHand = new StraightHand(blackHand);
        blackFlushHand = new FlushHand(blackHand);
        blackStraightFlushHand = new StraightFlushHand(blackHand);
    }

    @Override
    public Result getMatchingResult() {
        if(isStraightFlush(whiteStraightFlushHand)) {
            return Result.aMatchResult("White wins. - with straight flush");
        }
        if(isStraightFlush(blackStraightFlushHand)) {
            return Result.aMatchResult("Black wins. - with straight flush");
        }
        return super.getMatchingResult();
    }

    private boolean isStraightFlush(StraightFlushHand straightFlushHand) {
        return straightFlushHand.isStraightFlush();
    }
}
