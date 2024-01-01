package com.murex.ranking;

import com.murex.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static com.murex.ResultHelper.aNoWinner;
import static com.murex.ResultHelper.aPairWinningResult;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class PairCardRanking extends OrderRanking {
    private final Optional<CardNumber> blackPairCards;
    private final Optional<CardNumber> whitePairCards;

    public PairCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        this.blackPairCards = extractCardOfPairs(blackHand);
        this.whitePairCards = extractCardOfPairs(whiteHand);
    }

    @Override
    public Result evaluate() {
        if (bothHandsHavePair()) {
            return getHigherPair();
        }

        if (noHandHasPair()) {
            return aNoWinner();
        }

        return blackPairCards.isPresent() ?
                aPairWinningResult(blackHand, blackPairCards.get(), false) :
                aPairWinningResult(whiteHand, whitePairCards.get(), false);
    }

    private Result getHigherPair() {
        int comparison = blackPairCards.get().compareTo(whitePairCards.get());
        if (comparison == 0) {
            return aNoWinner();
        }

        return comparison > 0 ?
                aPairWinningResult(blackHand, blackPairCards.get(), true) :
                aPairWinningResult(whiteHand, whitePairCards.get(), true);
    }

    private boolean noHandHasPair() {
        return blackPairCards.isEmpty() && whitePairCards.isEmpty();
    }

    private boolean bothHandsHavePair() {
        return blackPairCards.isPresent() && whitePairCards.isPresent();
    }

    private Optional<CardNumber> extractCardOfPairs(Hand hand) {
        Map<CardNumber, Long> cardsPairMap = Arrays.stream(hand.getCards()).collect(groupingBy(Card::getCardNumber, counting()));
        return cardsPairMap.keySet().stream().filter(card -> cardsPairMap.get(card) == 2).findAny();
    }
}
