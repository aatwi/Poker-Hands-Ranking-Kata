package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.result.Result;
import poker.hand.result.ResultHelper;

import static poker.hand.result.ResultHelper.*;

public class Straight extends RankingCategory {

    private Result result= ResultHelper.aNoWinner();
    public Straight(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public String getResult() {
        return result.getMessage();
    }

    @Override
    public Result evaluate() {
        isMatch();
        return result;
    }

    @Override
    public boolean isMatch() {
        if (noHandHasStraight()) {
            return false;
        }

        if (bothHandsHaveStraight()) {
            result = getHigherHand();
            return true;
        }

        result = isStraight(blackHand) ?
                aStraightWinningResult(blackHand, false) :
                aStraightWinningResult(whiteHand, false);

        return true;
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
            return aTie();
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
