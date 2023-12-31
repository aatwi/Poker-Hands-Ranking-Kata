package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.StraightFlushHand;

public class StraightFlushHandRanking extends HandRanking{

    private final StraightFlushHand whiteStraightFlushHand;
    private final StraightFlushHand blackStraightFlushHand;

    public StraightFlushHandRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        whiteStraightFlushHand = new StraightFlushHand(whiteHand);
        blackStraightFlushHand = new StraightFlushHand(blackHand);
    }

    @Override
    public Result getMatchingResult() {
        if(whiteStraightFlushHand.isStraightFlush() && blackStraightFlushHand.isStraightFlush()) {
            Card blackCard = blackStraightFlushHand.getHand().getCardAt(4);
            Card whiteCard = whiteStraightFlushHand.getHand().getCardAt(4);
            int comparison = blackCard.compareTo(whiteCard);
            if (comparison == 0) {
                return Result.aNoMatchResult();
            }
            Hand winningHand = comparison > 0 ? blackHand : whiteHand;
            return Result.aStraightFlushWinningResult(winningHand, true);
        }
        if(whiteStraightFlushHand.isStraightFlush()) {
            return Result.aStraightFlushWinningResult(whiteStraightFlushHand.getHand(), false);
        }
        if(blackStraightFlushHand.isStraightFlush()) {
            return Result.aStraightFlushWinningResult(blackStraightFlushHand.getHand(), false);
        }
        return super.getMatchingResult();
    }
}
