package com.murex;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public final class PairHand {
    private final Hand hand;
    private final Map<Card, Long> cardsPairMap;

    public PairHand(Hand hand) {
        this.hand = hand;
        cardsPairMap = buildPairMap();
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
        Optional<Card> cardFound = cardsPairMap.keySet().stream().filter(card -> cardsPairMap.get(card) == 2).findAny();
        return cardFound.isPresent() ? cardFound.get().getValue() : "";
    }
}
