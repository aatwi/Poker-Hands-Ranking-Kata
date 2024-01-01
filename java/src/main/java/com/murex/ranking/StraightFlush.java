package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

import static com.murex.ResultHelper.aStraightFlushWinningResult;
import static com.murex.ResultHelper.aTieResult;

public class StraightFlush extends RankingCategory {

    public StraightFlush(Hand blackHand, Hand whiteHand) {
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
        return Straight.isStraight(hand) && Flush.isFlush(hand);
    }
}
