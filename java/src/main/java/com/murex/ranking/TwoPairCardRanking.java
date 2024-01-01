package com.murex.ranking;

import com.murex.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.murex.ResultHelper.*;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class TwoPairCardRanking extends OrderRanking {

    private final List<CardNumber> blackPairOfCards;
    private final List<CardNumber> whitePairOfCards;

    public TwoPairCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        blackPairOfCards = extractPairs(blackHand);
        whitePairOfCards = extractPairs(whiteHand);
    }

    @Override
    public Result evaluate() {
        if (bothHandsHaveTwoPairs()) {
            return getHigherHand();
        }

        if (noHandHasTwoPairs()) {
            return aNoWinner();
        }

        return blackPairOfCards.size() == 2 ?
                aTwoPairWinningResult(blackHand, blackPairOfCards.get(0), blackPairOfCards.get(1), false) :
                aTwoPairWinningResult(whiteHand, whitePairOfCards.get(0), whitePairOfCards.get(1), false);
    }

    private Result getHigherHand() {
        int comparison = compareForHigherHands();
        if(comparison == 0){
            return aNoWinner();
        }

        return comparison > 0 ?
                aTwoPairWinningResult(blackHand, blackPairOfCards.get(0), blackPairOfCards.get(1), true):
                aTwoPairWinningResult(whiteHand, whitePairOfCards.get(0), whitePairOfCards.get(1), true);
    }

    private boolean bothHandsHaveTwoPairs() {
        return  blackPairOfCards.size() == 2 && whitePairOfCards.size() == 2;
    }

    private boolean noHandHasTwoPairs() {
        return blackPairOfCards.size() != 2 && whitePairOfCards.size() != 2;
    }

    private int compareForHigherHands() {
        int comparison = blackPairOfCards.get(1).compareTo(whitePairOfCards.get(1));
        return comparison != 0 ? comparison : blackPairOfCards.get(0).compareTo(whitePairOfCards.get(0));
    }

    private List<CardNumber> extractPairs(Hand hand) {
        Map<CardNumber, Long> twoPairsMap = Arrays.stream(hand.getCards()).collect(groupingBy(Card::getCardNumber, counting()));
        return twoPairsMap.keySet().stream().filter(x -> twoPairsMap.get(x) == 2).sorted().toList();
    }
}
