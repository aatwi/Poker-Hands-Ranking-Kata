package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.Result;

import static poker.hand.ResultHelper.aHighCardWinningResult;

public class HighCard extends RankingCategory {

    public HighCard(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result evaluate() {
        for (int index = 4; index >= 0; index--) {
            int cardComparison = blackHand.getCardAt(index).compareTo(whiteHand.getCardAt(index));
            if (cardComparison != 0) {
                return cardComparison > 0 ?
                        aHighCardWinningResult(blackHand, blackHand.getCardAt(index).getCardNumber()) :
                        aHighCardWinningResult(whiteHand, whiteHand.getCardAt(index).getCardNumber());
            }
        }
        return super.evaluate();
    }
}
