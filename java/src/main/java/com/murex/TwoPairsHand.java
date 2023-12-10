package com.murex;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public final class TwoPairsHand {
    private final Hand hand;
    private final Map<Card, Long> twoPairsMap;
    private final List<Card> pairOfCards;

    public TwoPairsHand(Hand hand) {
        this.hand = hand;
        twoPairsMap = Arrays.stream(this.hand.getCards()).collect(groupingBy(Function.identity(), counting()));
        pairOfCards = getTwoPairs();
    }

    public Hand getHand() {
        return hand;
    }

    public String getHandName() {
        return getHand().getName();
    }

    private List<Card> getTwoPairs() {
        return twoPairsMap.keySet().stream().filter(x -> twoPairsMap.get(x) == 2).sorted().toList();
    }

    public boolean hasTwoPairs() {
        return pairOfCards.size() == 2;
    }

    public String getFirstPair() {
        return getFirstPairCard().getValue();
    }

    public  String getSecondPair() {
        return getSecondPairCard().getValue();
    }

    private Card getFirstPairCard() {
        return pairOfCards.get(0);
    }

    public Card getSecondPairCard() {
        return pairOfCards.get(1);
    }

}
