package poker.hand.ranking;

import poker.hand.Card;
import poker.hand.CardNumber;
import poker.hand.Hand;
import poker.hand.result.Result;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static poker.hand.result.ResultHelper.aNoWinner;
import static poker.hand.result.ResultHelper.aThreeOfAKindWinningResult;

public final class ThreeOfAKind extends RankingCategory {
    private final Optional<CardNumber> blackThreeOfAKindCards;
    private final Optional<CardNumber> whiteThreeOfAKindCards;

    public ThreeOfAKind(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        this.blackThreeOfAKindCards = extractThreeOfAKind(blackHand);
        this.whiteThreeOfAKindCards = extractThreeOfAKind(whiteHand);
    }

    @Override
    public Result evaluate() {
        if (noHandHasThreeOfAKindCards()) {
            return aNoWinner();
        }
        if (bothHaveThreeOfAKindCards()) {
            return evaluateHigherHand();
        }

        return blackThreeOfAKindCards.isPresent() ?
                aThreeOfAKindWinningResult(blackHand, blackThreeOfAKindCards.get(), false) :
                aThreeOfAKindWinningResult(whiteHand, whiteThreeOfAKindCards.get(), false);
    }

    private Result evaluateHigherHand() {
        int comparison = blackThreeOfAKindCards.get().compareTo(whiteThreeOfAKindCards.get());
        if (comparison == 0) {
            return super.evaluate();
        }

        return comparison > 0 ?
                aThreeOfAKindWinningResult(blackHand, blackThreeOfAKindCards.get(), true) :
                aThreeOfAKindWinningResult(whiteHand, whiteThreeOfAKindCards.get(), true);
    }

    private boolean noHandHasThreeOfAKindCards() {
        return blackThreeOfAKindCards.isEmpty() && whiteThreeOfAKindCards.isEmpty();
    }

    private boolean bothHaveThreeOfAKindCards() {
        return blackThreeOfAKindCards.isPresent() && whiteThreeOfAKindCards.isPresent();
    }

    private Optional<CardNumber> extractThreeOfAKind(Hand hand) {
        Map<CardNumber, Long> twoPairsMap = Arrays.stream(hand.cards()).collect(groupingBy(Card::getCardNumber, counting()));
        return twoPairsMap.keySet().stream().filter(x -> twoPairsMap.get(x) == 3).findFirst();
    }
}
