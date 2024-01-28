package poker.hand.result;

import poker.hand.CardNumber;
import poker.hand.Hand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultBuilder {
    private Hand winner = null;
    private List<CardNumber> winningCards = new ArrayList<>();
    private String rankOrder = null;
    private boolean higherCards = false;

    public static ResultBuilder aResultBuilder() {
        return new ResultBuilder();
    }

    public ResultBuilder withWinner(Hand winner) {
        this.winner = winner;
        return this;
    }

    public ResultBuilder withCard(CardNumber cardNumber) {
        this.winningCards.add(cardNumber);
        return this;
    }

    public ResultBuilder withRankOrder(String rankOrder) {
        this.rankOrder = rankOrder;
        return this;
    }

    public ResultBuilder withPairs(CardNumber firstPairCard, CardNumber secondPairCard) {
        winningCards.add(firstPairCard);
        winningCards.add(secondPairCard);
        return this;
    }

    public ResultBuilder withHigherCards(boolean withHighHand) {
        this.higherCards = withHighHand;
        return this;
    }

    public Result build() {
        String winningCardsStr = !winningCards.isEmpty() ?
                " (" + winningCards.stream().map(String::valueOf).collect(Collectors.joining(", ")) + ")" :
                "";
        String higherCardsStr = higherCards ?  " and higher cards": "";
        String finalMessage = String.format("Player \"%s\" wins with a %s hand%s%s", winner.playerName(), rankOrder, winningCardsStr, higherCardsStr);

        return new Winner(finalMessage);
    }
}
