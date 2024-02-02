package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.result.Result;

import static poker.hand.result.ResultHelper.aNoWinner;

public abstract class RankingCategory {
    protected final Hand blackHand;
    protected final Hand whiteHand;

    public RankingCategory(Hand blackHand, Hand whiteHand) {
        this.blackHand = blackHand;
        this.whiteHand = whiteHand;
    }

    boolean isMatch() {
        return false;
    }

    public Result evaluate() {
        return aNoWinner();
    }
}
