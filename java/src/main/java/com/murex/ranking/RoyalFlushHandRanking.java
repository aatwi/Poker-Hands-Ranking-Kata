package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.RoyalFlushHand;
import com.murex.hands.StraightFlushHand;

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
        if (isRoyalFlush(whiteRoyalFlushHand)) {
            return Result.aMatchResult("White wins. - with royal flush");
        }
        if (isRoyalFlush(blackRoyalFlushHand)) {
            return Result.aMatchResult("Black wins. - with royal flush");
        }
        return super.getMatchingResult();
    }

    private boolean isRoyalFlush(RoyalFlushHand royalFlushHand) {
        Hand hand = royalFlushHand.getHand();
        StraightFlushHand white = new StraightFlushHand(hand);
        if(white.isStraightFlush()){
            if (white.getHand().getCardAt(4).getCharValue() == 'A') {
                return true;
            }
        }
        return false;
    }
}
 