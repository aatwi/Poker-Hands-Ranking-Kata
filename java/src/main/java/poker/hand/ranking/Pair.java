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
import static poker.hand.result.ResultHelper.aPairWinningResult;

public final class Pair extends RankingCategory {
    private final Optional<CardNumber> firstPairCards;
    private final Optional<CardNumber> secondPairCards;

    public Pair(Hand firstHand, Hand secondHand) {
        super(firstHand, secondHand);
        this.firstPairCards = extractCardOfPairs(firstHand);
        this.secondPairCards = extractCardOfPairs(secondHand);
    }

    @Override
    public Result evaluate() {
        if (noHandHasPair()) {
            return super.evaluate();
        }

        if (bothHandsHavePair()) {
            return evaluateHigherHand();
        }

        return firstPairCards.isPresent() ?
                aPairWinningResult(firstHand, firstPairCards.get(), false) :
                aPairWinningResult(secondHand, secondPairCards.get(), false);
    }

    private Result evaluateHigherHand() {
        int comparison = firstPairCards.get().compareTo(secondPairCards.get());
        if (comparison == 0) {
            return super.evaluate();
        }

        return comparison > 0 ?
                aPairWinningResult(firstHand, firstPairCards.get(), true) :
                aPairWinningResult(secondHand, secondPairCards.get(), true);
    }

    private boolean noHandHasPair() {
        return firstPairCards.isEmpty() && secondPairCards.isEmpty();
    }

    private boolean bothHandsHavePair() {
        return firstPairCards.isPresent() && secondPairCards.isPresent();
    }

    private Optional<CardNumber> extractCardOfPairs(Hand hand) {
        Map<CardNumber, Long> cardsPairMap = Arrays.stream(hand.cards()).collect(groupingBy(Card::getCardNumber, counting()));
        return cardsPairMap.keySet().stream().filter(card -> cardsPairMap.get(card) == 2).findAny();
    }
}
