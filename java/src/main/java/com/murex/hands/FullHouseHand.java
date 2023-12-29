package com.murex.hands;

import com.murex.Card;
import com.murex.Hand;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class FullHouseHand {
    private final Hand hand;

    public FullHouseHand(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public boolean hasFullHouse() {
        Hand hand = getHand();
        Map<Card, Long> cardGroupsMap = Arrays.stream(hand.getCards()).collect(groupingBy(Function.identity(), counting()));
        Optional<Card> pairCards = cardGroupsMap.keySet().stream().filter(card -> cardGroupsMap.get(card) == 2).findAny();
        Optional<Card> trioCards = cardGroupsMap.keySet().stream().filter(card -> cardGroupsMap.get(card) == 3).findAny();

        return pairCards.isPresent() && trioCards.isPresent();
    }
}
