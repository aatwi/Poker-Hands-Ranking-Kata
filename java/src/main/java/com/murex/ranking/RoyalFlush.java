package com.murex.ranking;

import com.murex.CardNumber;
import com.murex.Hand;
import com.murex.Result;

import static com.murex.ResultHelper.*;
import static com.murex.ResultHelper.aTieResult;

public class RoyalFlush extends RankingCategory {

    public RoyalFlush(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result evaluate() {
        if (!isRoyalFlush(blackHand) && !isRoyalFlush(whiteHand)) {
            return super.evaluate();
        }

        if (isRoyalFlush(blackHand) && isRoyalFlush(whiteHand)) {
            return aTieResult();
        }

        return isRoyalFlush(blackHand) ?
                aRoyalFlushWinningResult(blackHand) :
                aRoyalFlushWinningResult(whiteHand);
    }

    public boolean isRoyalFlush(Hand hand) {
        if (StraightFlush.isStraightFlush(hand)) {
            return hand.getCardAt(4).getCardNumber().equals(CardNumber.ACE);
        }
        return false;
    }
}
 