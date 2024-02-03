package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.result.Result;

import static poker.hand.result.ResultHelper.aHighCardWinningResult;

public final class HighCard extends RankingCategory {

    private HighCard(Hand firstHand, Hand secondHand) {
        super(firstHand, secondHand);
    }

    public static HighCard aHighCard(Hand firstHand, Hand secondHand) {
        return new HighCard(firstHand, secondHand);
    }

    @Override
    public Result evaluate() {
        for (int index = 4; index >= 0; index--) {
            int cardComparison = firstHand.getCardAt(index).compareTo(secondHand.getCardAt(index));
            if (cardComparison != 0) {
                return cardComparison > 0 ?
                        aHighCardWinningResult(firstHand, firstHand.getCardAt(index).getCardNumber()) :
                        aHighCardWinningResult(secondHand, secondHand.getCardAt(index).getCardNumber());
            }
        }
        return super.evaluate();
    }
}
