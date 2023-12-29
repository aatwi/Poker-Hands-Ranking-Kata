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
    private Optional<Card> pairCards;
    private Optional<Card> trioCards;

    public FullHouseHand(Hand hand) {
        this.hand = hand;
        this.extractPairsAndTrioCards();
    }

    public Hand getHand() {
        return hand;
    }

    public Optional<Card> getTrioCards() {
        return trioCards;
    }

    public boolean hasFullHouse() {
        return pairCards.isPresent() && trioCards.isPresent();
    }

    private void extractPairsAndTrioCards() {
        Map<Card, Long> cardGroupsMap = Arrays.stream(hand.getCards()).collect(groupingBy(Function.identity(), counting()));
        pairCards = cardGroupsMap.keySet().stream().filter(card -> cardGroupsMap.get(card) == 2).findAny();
        trioCards = cardGroupsMap.keySet().stream().filter(card -> cardGroupsMap.get(card) == 3).findAny();
    }
}
