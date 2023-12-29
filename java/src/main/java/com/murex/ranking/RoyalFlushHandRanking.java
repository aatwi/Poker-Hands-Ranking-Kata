package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.StraightFlushHand;

public class RoyalFlushHandRanking extends HandRanking{

    public RoyalFlushHandRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        StraightFlushHand white = new StraightFlushHand(whiteHand);
        StraightFlushHand black = new StraightFlushHand(blackHand);
        if(white.isStraightFlush()){
            if (white.getHand().getCardAt(4).getCharValue() == 'A') {
                return Result.aMatchResult( "White wins. - with royal flush");
            }
        }
        if(black.isStraightFlush()) {
            if(black.isStraightFlush()) {
                if (black.getHand().getCardAt(4).getCharValue() == 'A') {
                    return Result.aMatchResult( "Black wins. - with royal flush");
                }
            }
        }
        return super.getMatchingResult();
    }
}
