package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.ResultHelper;

import static com.murex.ResultHelper.aStraightFlushWinningResult;
import static com.murex.ResultHelper.aTieResult;

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
                aStraightFlushWinningResult(blackHand, false) :
                aStraightFlushWinningResult(whiteHand, false);
    }

    private Result getHigherHand() {
        int comparison = blackHand.getCardAt(4).compareTo(whiteHand.getCardAt(4));
        if (comparison == 0) {
            return aTieResult();
        }
        Hand winningHand = comparison > 0 ? blackHand : whiteHand;
        return aStraightFlushWinningResult(winningHand, true);
    }

    public static boolean isStraightFlush(Hand hand) {
        return StraightOrderRanking.isStraight(hand) && FlushOrderRanking.isFlush(hand);
    }
}
