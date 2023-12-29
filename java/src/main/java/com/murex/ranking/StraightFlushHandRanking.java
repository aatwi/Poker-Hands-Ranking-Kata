package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.FlushHand;
import com.murex.hands.StraightFlushHand;
import com.murex.hands.StraightHand;

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
            return Result.aMatchResult("Black wins. - with straight flush and higher hand");
        }
        if(whiteStraightFlushHand.isStraightFlush()) {
            return Result.aMatchResult("White wins. - with straight flush");
        }
        if(blackStraightFlushHand.isStraightFlush()) {
            return Result.aMatchResult("Black wins. - with straight flush");
        }
        return super.getMatchingResult();
    }

}
