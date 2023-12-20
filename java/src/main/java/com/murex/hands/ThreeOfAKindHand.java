package com.murex.hands;

import com.murex.Card;
import com.murex.Hand;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public final class ThreeOfAKindHand {
    private final Hand hand;

    public ThreeOfAKindHand(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public List<Card> extractThreeOfAKind() {
        Map<Card, Long> twoPairsMap = Arrays.stream(this.hand.getCards()).collect(groupingBy(Function.identity(), counting()));
        return twoPairsMap.keySet().stream().filter(x -> twoPairsMap.get(x) == 3).sorted().toList();
    }
}
