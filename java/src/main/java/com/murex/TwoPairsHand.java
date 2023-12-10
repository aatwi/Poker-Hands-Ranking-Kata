package com.murex;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public final class TwoPairsHand {
    private final Hand hand;

    public TwoPairsHand(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public String getHandName() {
        return getHand().getName();
    }

    public List<Card> getTwoPairs() {
        Map<Card, Long> collect = Arrays.stream(getHand().getCards()).collect(groupingBy(Function.identity(), counting()));
        return collect.keySet().stream().filter(x -> collect.get(x) == 2).sorted().toList();
    }

    public boolean hasTwoPairs() {
        return getTwoPairs().size() == 2;
    }

    public String getFirstPair() {
        return getFirstPairCard().getValue();
    }

    public  String getSecondPair() {
        return getSecondPairCard().getValue();
    }

    private Card getFirstPairCard() {
        return getTwoPairs().get(0);
    }

    public Card getSecondPairCard() {
        return getTwoPairs().get(1);
    }

}
