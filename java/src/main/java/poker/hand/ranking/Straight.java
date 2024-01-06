package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.Result;

import static poker.hand.ResultHelper.*;

public class Straight extends RankingCategory {

    public Straight(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result evaluate() {
        if (bothHandsHaveStraight()) {
            return getHigherHand();
        }

        if (noHandHasStraight()) {
            return super.evaluate();
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
            return aTieResult();
        }
        return comparison > 0 ?
                aStraightWinningResult(blackHand, true) :
                aStraightWinningResult(whiteHand, true);
    }

    protected static boolean isStraight(Hand hand) {
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
