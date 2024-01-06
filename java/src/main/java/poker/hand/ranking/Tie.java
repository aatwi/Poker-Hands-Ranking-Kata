package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.Result;
import poker.hand.ResultHelper;

public class Tie extends RankingCategory {
    public Tie(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result evaluate() {
        for (int index = 0; index < 5; index++) {
            if (blackHand.getCardAt(index).compareTo(whiteHand.getCardAt(index)) != 0) {
                return super.evaluate();
            }
        }
        return ResultHelper.aTieResult();
    }
}
