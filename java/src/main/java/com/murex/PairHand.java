package com.murex;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public final class PairHand {
    private final Hand hand;

    public PairHand(Hand hand) {
        this.hand = hand;
    }

    public boolean hasPair() {
        Map<Card, Long> cardsPairMap = Arrays.stream(hand.getCards()).collect(groupingBy(Function.identity(), counting()));
        return cardsPairMap.keySet().stream().anyMatch(card -> cardsPairMap.get(card) == 2);
    }

    public String getPairValue() {
        Map<Card, Long> cardsPairMap = Arrays.stream(hand.getCards()).collect(groupingBy(Function.identity(), counting()));
        Optional<Card> cardFound = cardsPairMap.keySet().stream().filter(card -> cardsPairMap.get(card) == 2).findAny();
        return cardFound.isPresent() ? cardFound.get().getValue() : "";
    }
}
