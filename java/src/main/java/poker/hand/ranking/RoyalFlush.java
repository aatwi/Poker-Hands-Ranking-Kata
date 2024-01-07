package poker.hand.ranking;

import poker.hand.CardNumber;
import poker.hand.Hand;
import poker.hand.Result;
import poker.hand.ResultHelper;

import static poker.hand.ResultHelper.aRoyalFlushWinningResult;
import static poker.hand.ResultHelper.aTieResult;

public class RoyalFlush extends RankingCategory {

    private Result result = ResultHelper.aNoWinner();

    public RoyalFlush(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public String getResult() {
        return result.getMessage();
    }

    @Override
    public boolean isMatch() {
        if (!isRoyalFlush(blackHand) && !isRoyalFlush(whiteHand)) {
            return false;
        }

        if (isRoyalFlush(blackHand) && isRoyalFlush(whiteHand)) {
            result = aTieResult();
            return true;
        }

        result= isRoyalFlush(blackHand) ?
                aRoyalFlushWinningResult(blackHand) :
                aRoyalFlushWinningResult(whiteHand);
        return true;
    }

    @Override
    public Result evaluate() {
        isMatch();
        return result;
    }

    public boolean isRoyalFlush(Hand hand) {
        if (StraightFlush.isStraightFlush(hand)) {
            return hand.getCardAt(4).getCardNumber().equals(CardNumber.ACE);
        }
        return false;
    }
}
 