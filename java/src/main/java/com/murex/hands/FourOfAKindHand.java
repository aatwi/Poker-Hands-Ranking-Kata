package com.murex.hands;

import com.murex.Card;
import com.murex.Hand;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class FourOfAKindHand {
    private final Hand hand;
    private Optional<Card> fourCards;

    public FourOfAKindHand(Hand hand) {
        this.hand = hand;
        this.fourCards = extractFourOfAKindCard();
    }

    public Hand getHand() {
        return hand;
    }

    public boolean hasFourOfAKind() {
        return fourCards.isPresent();
    }

    private Optional<Card> extractFourOfAKindCard() {
        Map<Card, Long> cardGroupsMap = Arrays.stream(getHand().getCards()).collect(groupingBy(Function.identity(), counting()));
        return cardGroupsMap.keySet().stream().filter(card -> cardGroupsMap.get(card) == 4).findAny();
    }
}
