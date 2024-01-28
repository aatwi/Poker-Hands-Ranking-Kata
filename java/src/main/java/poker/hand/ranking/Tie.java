package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.result.Result;
import poker.hand.result.ResultHelper;

public class Tie extends RankingCategory {
    private String message = "";

    public Tie(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public String getResult() {
        return message;
    }

    @Override
    public boolean isMatch() {
        for (int index = 0; index < 5; index++) {
            if (blackHand.getCardAt(index).compareTo(whiteHand.getCardAt(index)) != 0) {
                return false;
            }
        }
        this.message = ResultHelper.aTie().getMessage();
        return true;
    }

    @Override
    public Result evaluate() {
        if (isMatch()) {
            return ResultHelper.aTie();
        }
        return ResultHelper.aNoWinner();
    }
}
