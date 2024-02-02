package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.result.Result;
import poker.hand.result.ResultHelper;

import static poker.hand.result.ResultHelper.*;

public final class Straight extends RankingCategory {

    public Straight(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result evaluate() {
        if (noHandHasStraight()) {
            return ResultHelper.aNoWinner();
        }

        if (bothHandsHaveStraight()) {
            return evaluateHigherHand();
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

    private Result evaluateHigherHand() {
        int comparison = blackHand.getCardAt(0).compareTo(whiteHand.getCardAt(0));
        if (comparison == 0) {
            return aTie();
        }

        return comparison > 0 ?
                aStraightWinningResult(blackHand, true) :
                aStraightWinningResult(whiteHand, true);
    }

     static boolean isStraight(Hand hand) {
        for (int i = 1; i < hand.cards().length; i++) {
            int previousCard = hand.getCardAt(i - 1).getIntValue();
            int currentCard = hand.getCardAt(i).getIntValue();
            if (currentCard != previousCard + 1) {
                return false;
            }
        }
        return true;
    }
}
