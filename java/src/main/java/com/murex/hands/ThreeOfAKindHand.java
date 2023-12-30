package com.murex.hands;

import com.murex.Card;
import com.murex.CardNumber;
import com.murex.Hand;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public final class ThreeOfAKindHand {
    private final Hand hand;
    private final List<CardNumber> cards;

    public ThreeOfAKindHand(Hand hand) {
        this.hand = hand;
        this.cards = extractThreeOfAKind();
    }

    public Hand getHand() {
        return hand;
    }

    private List<CardNumber> extractThreeOfAKind() {
        Map<CardNumber, Long> twoPairsMap = Arrays.stream(this.hand.getCards()).collect(groupingBy(Card::getCardNumber, counting()));
        return twoPairsMap.keySet().stream().filter(x -> twoPairsMap.get(x) == 3).sorted().toList();
    }

    public boolean hasThreeOfAKind() {
        return this.cards.size() == 1;
    }

    public CardNumber getCard() {
        return this.cards.get(0);
    }
}
