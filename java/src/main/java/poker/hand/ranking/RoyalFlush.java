package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.result.Result;

import static poker.hand.CardNumber.ACE;
import static poker.hand.result.ResultHelper.aRoyalFlushWinningResult;
import static poker.hand.result.ResultHelper.aTie;

public final class RoyalFlush extends RankingCategory {

    private RoyalFlush(Hand firstHand, Hand secondHand) {
        super(firstHand, secondHand);
    }

    public static RoyalFlush aRoyalFlush(Hand firstHand, Hand secondHand) {
        return new RoyalFlush(firstHand, secondHand);
    }

    @Override
    public Result evaluate() {
        if (!isRoyalFlush(firstHand) && !isRoyalFlush(secondHand)) {
            return super.evaluate();
        }

        if (isRoyalFlush(firstHand) && isRoyalFlush(secondHand)) {
            return aTie();
        }

        return isRoyalFlush(firstHand) ?
                aRoyalFlushWinningResult(firstHand) :
                aRoyalFlushWinningResult(secondHand);
    }

    private boolean isRoyalFlush(Hand hand) {
        if (StraightFlush.isStraightFlush(hand)) {
            return hand.getCardAt(4).getCardNumber().equals(ACE);
        }
        return false;
    }
}
 