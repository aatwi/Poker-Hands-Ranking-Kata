package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.result.Result;

import static poker.hand.result.ResultHelper.aNoWinner;

public abstract class RankingCategory {
    protected final Hand firstHand;
    protected final Hand secondHand;

    public RankingCategory(Hand firstHand, Hand secondHand) {
        this.firstHand = firstHand;
        this.secondHand = secondHand;
    }

    public Result evaluate() {
        return aNoWinner();
    }
}
