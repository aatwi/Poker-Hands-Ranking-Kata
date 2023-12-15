package com.murex.hands;

import com.murex.Card;
import com.murex.Hand;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public final class PairHand {
    private final Hand hand;
    private final Map<Card, Long> cardsPairMap;
    private Optional<Card> cardOfPairs;

    public PairHand(Hand hand) {
        this.hand = hand;
        cardsPairMap = buildPairMap();
        cardOfPairs = extractCardOfPairs();
    }

    public Hand getHand() {
        return hand;
    }

    public boolean hasPair() {
        return cardsPairMap.keySet().stream().anyMatch(card -> cardsPairMap.get(card) == 2);
    }

    private Map<Card, Long> buildPairMap() {
        return Arrays.stream(hand.getCards()).collect(groupingBy(Function.identity(), counting()));
    }

    public String getPairValue() {
        Optional<Card> cardFound = extractCardOfPairs();
        return cardFound.isPresent() ? cardFound.get().getValue() : "";
    }

    private Optional<Card> extractCardOfPairs() {
        cardOfPairs = cardsPairMap.keySet().stream().filter(card -> cardsPairMap.get(card) == 2).findAny();
        return cardOfPairs;
    }
}
