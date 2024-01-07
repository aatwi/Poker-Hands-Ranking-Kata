package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.Result;
import poker.hand.ResultHelper;

import static poker.hand.ResultHelper.aStraightFlushWinningResult;
import static poker.hand.ResultHelper.aTieResult;

public class StraightFlush extends RankingCategory {

    private Result result = ResultHelper.aNoWinner();

    public StraightFlush(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    public static boolean isStraightFlush(Hand hand) {
        return Straight.isStraight(hand) && Flush.isFlush(hand);
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
        if (!isStraightFlush(blackHand) && !isStraightFlush(whiteHand)) {
            return false;
        }

        if (isStraightFlush(blackHand) && isStraightFlush(whiteHand)) {
            result = getHigherHand();
            return true;
        }

        result = isStraightFlush(blackHand) ?
                aStraightFlushWinningResult(blackHand, false) :
                aStraightFlushWinningResult(whiteHand, false);
        return true;
    }

    private Result getHigherHand() {
        int comparison = blackHand.getCardAt(4).compareTo(whiteHand.getCardAt(4));
        if (comparison == 0) {
            return aTieResult();
        }
        Hand winningHand = comparison > 0 ? blackHand : whiteHand;
        return aStraightFlushWinningResult(winningHand, true);
    }
}
