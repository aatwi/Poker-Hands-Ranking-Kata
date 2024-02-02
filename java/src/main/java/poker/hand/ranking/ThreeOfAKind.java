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
import static poker.hand.result.ResultHelper.aThreeOfAKindWinningResult;

public final class ThreeOfAKind extends RankingCategory {
    private final Optional<CardNumber> firstThreeOfAKindCards;
    private final Optional<CardNumber> secondThreeOfAKindCards;

    public ThreeOfAKind(Hand firstHand, Hand secondHand) {
        super(firstHand, secondHand);
        this.firstThreeOfAKindCards = extractThreeOfAKind(firstHand);
        this.secondThreeOfAKindCards = extractThreeOfAKind(secondHand);
    }

    @Override
    public Result evaluate() {
        if (noHandHasThreeOfAKindCards()) {
            return super.evaluate();
        }
        if (bothHaveThreeOfAKindCards()) {
            return evaluateHigherHand();
        }

        return firstThreeOfAKindCards.isPresent() ?
                aThreeOfAKindWinningResult(firstHand, firstThreeOfAKindCards.get(), false) :
                aThreeOfAKindWinningResult(secondHand, secondThreeOfAKindCards.get(), false);
    }

    private Result evaluateHigherHand() {
        int comparison = firstThreeOfAKindCards.get().compareTo(secondThreeOfAKindCards.get());
        if (comparison == 0) {
            return super.evaluate();
        }

        return comparison > 0 ?
                aThreeOfAKindWinningResult(firstHand, firstThreeOfAKindCards.get(), true) :
                aThreeOfAKindWinningResult(secondHand, secondThreeOfAKindCards.get(), true);
    }

    private boolean noHandHasThreeOfAKindCards() {
        return firstThreeOfAKindCards.isEmpty() && secondThreeOfAKindCards.isEmpty();
    }

    private boolean bothHaveThreeOfAKindCards() {
        return firstThreeOfAKindCards.isPresent() && secondThreeOfAKindCards.isPresent();
    }

    private Optional<CardNumber> extractThreeOfAKind(Hand hand) {
        Map<CardNumber, Long> twoPairsMap = Arrays.stream(hand.cards()).collect(groupingBy(Card::getCardNumber, counting()));
        return twoPairsMap.keySet().stream().filter(x -> twoPairsMap.get(x) == 3).findFirst();
    }
}
