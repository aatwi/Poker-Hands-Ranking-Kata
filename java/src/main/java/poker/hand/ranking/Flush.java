package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.result.Result;
import poker.hand.result.ResultHelper;

import static poker.hand.result.ResultHelper.aFlushWinningResult;
import static poker.hand.result.ResultHelper.aNoWinner;

public class Flush extends RankingCategory {

    public Flush(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    protected static boolean isFlush(Hand hand) {
        for (int index = 1; index < 5; index++) {
            if (!hand.getCardAt(index - 1).getSuite().equals(hand.getCardAt(index).getSuite())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Result evaluate() {
        if (noHandHasAFlush()) {
            return aNoWinner();
        }

        if (bothHandsHaveFlush()) {
            return evaluateHigherHand();
        }

        return isFlush(blackHand) ?
                aFlushWinningResult(blackHand, false) :
                aFlushWinningResult(whiteHand, false);
    }

    private boolean noHandHasAFlush() {
        return !isFlush(blackHand) && !isFlush(whiteHand);
    }

    private Result evaluateHigherHand() {
        for (int index = 4; index >= 0; index--) {
            int cardComparison = blackHand.getCardAt(index).compareTo(whiteHand.getCardAt(index));
            if (cardComparison != 0) {
                return cardComparison > 0 ?
                        aFlushWinningResult(blackHand, true) :
                        aFlushWinningResult(whiteHand, true);
            }
        }
        return ResultHelper.aTie();
    }

    private boolean bothHandsHaveFlush() {
        return isFlush(blackHand) && isFlush(whiteHand);
    }
}
