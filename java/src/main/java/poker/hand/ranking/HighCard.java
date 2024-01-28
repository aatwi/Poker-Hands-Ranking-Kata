package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.result.Result;
import poker.hand.result.ResultHelper;

import static poker.hand.result.ResultHelper.aHighCardWinningResult;

public class HighCard extends RankingCategory {

    private Result result = ResultHelper.aNoWinner();

    public HighCard(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public String getResult() {
        return result.getMessage();
    }

    @Override
    public boolean isMatch() {
        for (int index = 4; index >= 0; index--) {
            int cardComparison = blackHand.getCardAt(index).compareTo(whiteHand.getCardAt(index));
            if (cardComparison != 0) {
                this.result = cardComparison > 0 ?
                        aHighCardWinningResult(blackHand, blackHand.getCardAt(index).getCardNumber()) :
                        aHighCardWinningResult(whiteHand, whiteHand.getCardAt(index).getCardNumber());
                return true;
            }
        }
        return false;
    }

    @Override
    public Result evaluate() {
        isMatch();
        return result;
    }
}
