package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

import static com.murex.ResultHelper.aNoWinner;
import static com.murex.ResultHelper.aStraightWinningResult;

public class StraightOrderRanking extends OrderRanking {

    public StraightOrderRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if (bothHandsHaveStraight()) {
            return getHigherHand();
        }

        if (noHandHasStraight()) {
            return aNoWinner();
        }

        return isStraight(blackHand) ?
                aStraightWinningResult(blackHand, false) :
                aStraightWinningResult(whiteHand, false);
    }

    private boolean noHandHasStraight() {
        return !isStraight(whiteHand) && !isStraight(blackHand);
    }

    private boolean bothHandsHaveStraight() {
        return isStraight(whiteHand) && isStraight(blackHand);
    }

    private Result getHigherHand() {
        int comparison = blackHand.getCardAt(0).compareTo(whiteHand.getCardAt(0));
        if (comparison == 0) {
            return aNoWinner();
        }
        return comparison > 0 ?
                aStraightWinningResult(blackHand, true) :
                aStraightWinningResult(whiteHand, true);
    }

    protected static boolean isStraight(Hand hand) {
        for (int i = 1; i < hand.getCards().length; i++) {
            int previousCard = hand.getCardAt(i - 1).getIntValue();
            int currentCard = hand.getCardAt(i).getIntValue();
            if (currentCard != previousCard + 1) {
                return false;
            }
        }
        return true;
    }
}
