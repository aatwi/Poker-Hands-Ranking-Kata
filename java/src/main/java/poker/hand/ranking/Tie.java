package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.result.Result;

import static poker.hand.result.ResultHelper.aTie;

public final class Tie extends RankingCategory {

    public Tie(Hand firstHand, Hand secondHand) {
        super(firstHand, secondHand);
    }

    @Override
    public Result evaluate() {
        for (int index = 0; index < 5; index++) {
            if (firstHand.getCardAt(index).compareTo(secondHand.getCardAt(index)) != 0) {
                return super.evaluate();
            }
        }
        return aTie();
    }
}
