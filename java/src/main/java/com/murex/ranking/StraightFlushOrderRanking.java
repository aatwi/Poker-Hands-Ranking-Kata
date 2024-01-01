package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.ResultHelper;

public class StraightFlushOrderRanking extends OrderRanking {

    public StraightFlushOrderRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result evaluate() {
        if (!isStraightFlush(blackHand) && !isStraightFlush(whiteHand)) {
            return super.evaluate();
        }

        if (isStraightFlush(blackHand) && isStraightFlush(whiteHand)) {
            return getHigherHand();
        }

        return isStraightFlush(blackHand) ?
                ResultHelper.aStraightFlushWinningResult(blackHand, false) :
                ResultHelper.aStraightFlushWinningResult(whiteHand, false);
    }

    private Result getHigherHand() {
        int comparison = blackHand.getCardAt(4).compareTo(whiteHand.getCardAt(4));
        if (comparison == 0) {
            return ResultHelper.aNoWinner();
        }
        Hand winningHand = comparison > 0 ? blackHand : whiteHand;
        return ResultHelper.aStraightFlushWinningResult(winningHand, true);
    }

    public static boolean isStraightFlush(Hand hand) {
        return StraightOrderRanking.isStraight(hand) && FlushOrderRanking.isFlush(hand);
    }
}
