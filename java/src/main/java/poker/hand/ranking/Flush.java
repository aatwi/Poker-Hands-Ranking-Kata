package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.result.Result;
import poker.hand.result.ResultHelper;

import static poker.hand.result.ResultHelper.aFlushWinningResult;
import static poker.hand.result.ResultHelper.aNoWinner;

public class Flush extends RankingCategory {

    private Result result = aNoWinner();

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
    public String getResult() {
        return result.getMessage();
    }

    @Override
    public boolean isMatch() {
        if (bothHandsHaveFlush()) {
            return getHigherHand();
        }

        if (noHandHasAFlush()) {
            return false;
        }

        result = isFlush(blackHand) ?
                aFlushWinningResult(blackHand, false) :
                aFlushWinningResult(whiteHand, false);
        return true;
    }

    @Override
    public Result evaluate() {
        isMatch();
        return result;
    }

    private boolean noHandHasAFlush() {
        return !isFlush(blackHand) && !isFlush(whiteHand);
    }

    private boolean getHigherHand() {
        for (int index = 4; index >= 0; index--) {
            int cardComparison = blackHand.getCardAt(index).compareTo(whiteHand.getCardAt(index));
            if (cardComparison != 0) {
                result = cardComparison > 0 ?
                        aFlushWinningResult(blackHand, true) :
                        aFlushWinningResult(whiteHand, true);
                return true;
            }
        }
        result = ResultHelper.aTieResult();
        return true;
    }

    private boolean bothHandsHaveFlush() {
        return isFlush(blackHand) && isFlush(whiteHand);
    }
}
