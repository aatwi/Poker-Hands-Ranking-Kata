package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.result.Result;

import static poker.hand.result.ResultHelper.aStraightWinningResult;
import static poker.hand.result.ResultHelper.aTie;

public final class Straight extends RankingCategory {

    private Straight(Hand firstHand, Hand secondHand) {
        super(firstHand, secondHand);
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

    public static Straight aStraight(Hand firstHand, Hand secondHand) {
        return new Straight(firstHand, secondHand);
    }

    @Override
    public Result evaluate() {
        if (noHandHasStraight()) {
            return super.evaluate();
        }

        if (bothHandsHaveStraight()) {
            return evaluateHigherHand();
        }

        return isStraight(firstHand) ?
                aStraightWinningResult(firstHand, false) :
                aStraightWinningResult(secondHand, false);
    }

    private boolean noHandHasStraight() {
        return !isStraight(secondHand) && !isStraight(firstHand);
    }

    private boolean bothHandsHaveStraight() {
        return isStraight(secondHand) && isStraight(firstHand);
    }

    private Result evaluateHigherHand() {
        int comparison = firstHand.getCardAt(0).compareTo(secondHand.getCardAt(0));
        if (comparison == 0) {
            return aTie();
        }

        return comparison > 0 ?
                aStraightWinningResult(firstHand, true) :
                aStraightWinningResult(secondHand, true);
    }
}
