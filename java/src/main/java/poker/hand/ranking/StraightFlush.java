package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.result.Result;

import static poker.hand.ranking.Flush.isFlush;
import static poker.hand.ranking.Straight.isStraight;
import static poker.hand.result.ResultHelper.aStraightFlushWinningResult;
import static poker.hand.result.ResultHelper.aTie;

public final class StraightFlush extends RankingCategory {

    private StraightFlush(Hand firstHand, Hand secondHand) {
        super(firstHand, secondHand);
    }

    public static boolean isStraightFlush(Hand hand) {
        return isStraight(hand) && isFlush(hand);
    }

    public static StraightFlush aStraightFlush(Hand firstHand, Hand secondHand) {
        return new StraightFlush(firstHand, secondHand);
    }

    @Override
    public Result evaluate() {
        if (!isStraightFlush(firstHand) && !isStraightFlush(secondHand)) {
            return super.evaluate();
        }

        if (isStraightFlush(firstHand) && isStraightFlush(secondHand)) {
            return evaluateHigherHand();
        }

        return isStraightFlush(firstHand) ?
                aStraightFlushWinningResult(firstHand, false) :
                aStraightFlushWinningResult(secondHand, false);
    }

    private Result evaluateHigherHand() {
        int comparison = firstHand.getCardAt(4).compareTo(secondHand.getCardAt(4));
        if (comparison == 0) {
            return aTie();
        }
        Hand winningHand = comparison > 0 ? firstHand : secondHand;
        return aStraightFlushWinningResult(winningHand, true);
    }
}
