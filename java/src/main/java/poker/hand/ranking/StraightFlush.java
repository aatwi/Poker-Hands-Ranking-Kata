package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.result.Result;

import static poker.hand.result.ResultHelper.*;

public class StraightFlush extends RankingCategory {

    public StraightFlush(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    public static boolean isStraightFlush(Hand hand) {
        return Straight.isStraight(hand) && Flush.isFlush(hand);
    }

    @Override
    public Result evaluate() {
        if (!isStraightFlush(blackHand) && !isStraightFlush(whiteHand)) {
            return aNoWinner();
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
            return aTie();
        }
        Hand winningHand = comparison > 0 ? blackHand : whiteHand;
        return aStraightFlushWinningResult(winningHand, true);
    }
}
