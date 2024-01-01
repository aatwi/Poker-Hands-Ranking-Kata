package com.murex.ranking;

import com.murex.CardNumber;
import com.murex.Hand;
import com.murex.Result;
import com.murex.ResultHelper;

public class RoyalFlushOrderRanking extends OrderRanking {

    public RoyalFlushOrderRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if (!isRoyalFlush(blackHand) && !isRoyalFlush(whiteHand)) {
            return ResultHelper.aNoWinner();
        }

        if (isRoyalFlush(blackHand) && isRoyalFlush(whiteHand)) {
            return ResultHelper.aNoWinner();
        }

        return isRoyalFlush(blackHand) ?
                ResultHelper.aRoyalFlushWinningResult(blackHand) :
                ResultHelper.aRoyalFlushWinningResult(whiteHand);
    }

    public boolean isRoyalFlush(Hand hand) {
        if (StraightFlushOrderRanking.isStraightFlush(hand)) {
            return hand.getCardAt(4).getCardNumber().equals(CardNumber.ACE);
        }
        return false;
    }
}
 