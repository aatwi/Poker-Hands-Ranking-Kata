package poker.hand.ranking;

import poker.hand.CardNumber;
import poker.hand.Hand;
import poker.hand.Result;

import static poker.hand.ResultHelper.*;
import static poker.hand.ResultHelper.aTieResult;

public class RoyalFlush extends RankingCategory {

    public RoyalFlush(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result evaluate() {
        if (!isRoyalFlush(blackHand) && !isRoyalFlush(whiteHand)) {
            return super.evaluate();
        }

        if (isRoyalFlush(blackHand) && isRoyalFlush(whiteHand)) {
            return aTieResult();
        }

        return isRoyalFlush(blackHand) ?
                aRoyalFlushWinningResult(blackHand) :
                aRoyalFlushWinningResult(whiteHand);
    }

    public boolean isRoyalFlush(Hand hand) {
        if (StraightFlush.isStraightFlush(hand)) {
            return hand.getCardAt(4).getCardNumber().equals(CardNumber.ACE);
        }
        return false;
    }
}
 