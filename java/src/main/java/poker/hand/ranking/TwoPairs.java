package poker.hand.ranking;

import poker.hand.Card;
import poker.hand.CardNumber;
import poker.hand.Hand;
import poker.hand.result.Result;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static poker.hand.result.ResultHelper.aTwoPairWinningResult;

public final class TwoPairs extends RankingCategory {

    private final List<CardNumber> firstPairOfCards;
    private final List<CardNumber> secondPairOfCards;

    public TwoPairs(Hand firstHand, Hand secondHand) {
        super(firstHand, secondHand);
        firstPairOfCards = extractPairs(firstHand);
        secondPairOfCards = extractPairs(secondHand);
    }

    @Override
    public Result evaluate() {
        if (noHandHasTwoPairs()) {
            return super.evaluate();
        }

        if (bothHandsHaveTwoPairs()) {
            return evaluateHigherHand();
        }

        return firstPairOfCards.size() == 2 ?
                aTwoPairWinningResult(firstHand, firstPairOfCards.get(0), firstPairOfCards.get(1), false) :
                aTwoPairWinningResult(secondHand, secondPairOfCards.get(0), secondPairOfCards.get(1), false);
    }

    private Result evaluateHigherHand() {
        int comparison = compareForHigherHands();
        if (comparison == 0) {
            return super.evaluate();
        }

        return comparison > 0 ?
                aTwoPairWinningResult(firstHand, firstPairOfCards.get(0), firstPairOfCards.get(1), true) :
                aTwoPairWinningResult(secondHand, secondPairOfCards.get(0), secondPairOfCards.get(1), true);
    }

    private boolean bothHandsHaveTwoPairs() {
        return firstPairOfCards.size() == 2 && secondPairOfCards.size() == 2;
    }

    private boolean noHandHasTwoPairs() {
        return firstPairOfCards.size() != 2 && secondPairOfCards.size() != 2;
    }

    private int compareForHigherHands() {
        int comparison = firstPairOfCards.get(1).compareTo(secondPairOfCards.get(1));
        return comparison != 0 ? comparison : firstPairOfCards.get(0).compareTo(secondPairOfCards.get(0));
    }

    private List<CardNumber> extractPairs(Hand hand) {
        Map<CardNumber, Long> twoPairsMap = Arrays.stream(hand.cards()).collect(groupingBy(Card::getCardNumber, counting()));
        return twoPairsMap.keySet().stream().filter(x -> twoPairsMap.get(x) == 2).sorted().toList();
    }
}
