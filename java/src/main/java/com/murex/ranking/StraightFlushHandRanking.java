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

    public StraightFlushHandRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        whiteStraightHand = new StraightHand(whiteHand);
        whiteFlushHand = new FlushHand(whiteHand);
        whiteStraightFlushHand = new StraightFlushHand(whiteHand);
        blackStraightHand = new StraightHand(blackHand);
        blackFlushHand = new FlushHand(blackHand);
    }

    @Override
    public Result getMatchingResult() {
        if(isStraightFlush(whiteStraightHand, whiteFlushHand)) {
            return Result.aMatchResult("White wins. - with straight flush");
        }
        if(isStraightFlush(blackStraightHand, blackFlushHand)) {
            return Result.aMatchResult("Black wins. - with straight flush");
        }
        return super.getMatchingResult();
    }

    private boolean isStraightFlush(StraightHand whiteStraightHand, FlushHand whiteFlushHand) {
        return whiteStraightHand.isStraight() && whiteFlushHand.isFlush();
    }
}
