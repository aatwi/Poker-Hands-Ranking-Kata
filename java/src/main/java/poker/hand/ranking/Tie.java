package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.result.Result;
import poker.hand.result.ResultHelper;

public final class Tie extends RankingCategory {

    private Tie(Hand firstHand, Hand secondHand) {
        super(firstHand, secondHand);
    }

    public static Tie aTie(Hand firstHand, Hand secondHand) {
        return new Tie(firstHand, secondHand);
    }

    @Override
    public Result evaluate() {
        for (int index = 0; index < 5; index++) {
            if (firstHand.getCardAt(index).compareTo(secondHand.getCardAt(index)) != 0) {
                return super.evaluate();
            }
        }
        return ResultHelper.aTie();
    }
}
