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
    private final Optional<Card> cardOfPairs;

    public PairHand(Hand hand) {
        this.hand = hand;
        cardOfPairs = extractCardOfPairs();
    }

    public Hand getHand() {
        return hand;
    }

    public boolean hasPair() {
        return cardOfPairs.isPresent();
    }

    private Map<Card, Long> buildPairMap() {
        return Arrays.stream(hand.getCards()).collect(groupingBy(Function.identity(), counting()));
    }

    public Card getPairCard() {
        return cardOfPairs.get();
    }

    public String getPairValue() {
        Optional<Card> cardFound = cardOfPairs;
        return cardFound.isPresent() ? cardFound.get().getValue() : "";
    }

    private Optional<Card> extractCardOfPairs() {
        Map<Card, Long> cardsPairMap = buildPairMap();
        return cardsPairMap.keySet().stream().filter(card -> cardsPairMap.get(card) == 2).findAny();
    }
}
