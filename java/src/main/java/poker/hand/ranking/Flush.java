package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.result.Result;
import poker.hand.result.ResultHelper;

import static poker.hand.result.ResultHelper.aFlushWinningResult;

public final class Flush extends RankingCategory {

    private Flush(Hand firstHand, Hand secondHand) {
        super(firstHand, secondHand);
    }

    static boolean isFlush(Hand hand) {
        for (int index = 1; index < 5; index++) {
            if (!hand.getCardAt(index - 1).getSuite().equals(hand.getCardAt(index).getSuite())) {
                return false;
            }
        }
        return true;
    }

    public static Flush aFlush(Hand firstHand, Hand secondHand) {
        return new Flush(firstHand, secondHand);
    }

    @Override
    public Result evaluate() {
        if (noHandHasAFlush()) {
            return super.evaluate();
        }

        if (bothHandsHaveFlush()) {
            return evaluateHigherHand();
        }

        return isFlush(firstHand) ?
                aFlushWinningResult(firstHand, false) :
                aFlushWinningResult(secondHand, false);
    }

    private boolean noHandHasAFlush() {
        return !isFlush(firstHand) && !isFlush(secondHand);
    }

    private Result evaluateHigherHand() {
        for (int index = 4; index >= 0; index--) {
            int cardComparison = firstHand.getCardAt(index).compareTo(secondHand.getCardAt(index));
            if (cardComparison != 0) {
                return cardComparison > 0 ?
                        aFlushWinningResult(firstHand, true) :
                        aFlushWinningResult(secondHand, true);
            }
        }
        return ResultHelper.aTie();
    }

    private boolean bothHandsHaveFlush() {
        return isFlush(firstHand) && isFlush(secondHand);
    }
}
