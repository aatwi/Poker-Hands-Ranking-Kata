package poker.hand.ranking;

import poker.hand.CardNumber;
import poker.hand.Hand;
import poker.hand.result.Result;

import static poker.hand.result.ResultHelper.*;

public class RoyalFlush extends RankingCategory {

    public RoyalFlush(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result evaluate() {
        if (!isRoyalFlush(blackHand) && !isRoyalFlush(whiteHand)) {
            return aNoWinner();
        }

        if (isRoyalFlush(blackHand) && isRoyalFlush(whiteHand)) {
            return aTie();
        }

        return isRoyalFlush(blackHand) ?
                aRoyalFlushWinningResult(blackHand) :
                aRoyalFlushWinningResult(whiteHand);
    }

    private boolean isRoyalFlush(Hand hand) {
        if (StraightFlush.isStraightFlush(hand)) {
            return hand.getCardAt(4).getCardNumber().equals(CardNumber.ACE);
        }
        return false;
    }
}
 