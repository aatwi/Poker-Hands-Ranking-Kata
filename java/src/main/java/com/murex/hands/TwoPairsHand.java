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

public final class TwoPairsHand {
    private final Hand hand;
    private final List<CardNumber> pairOfCards;

    public TwoPairsHand(Hand hand) {
        this.hand = hand;
        pairOfCards = extractPairs();
    }

    public Hand getHand() {
        return hand;
    }

    public String getHandName() {
        return getHand().getName();
    }

    private List<CardNumber> extractPairs() {
        Map<CardNumber, Long> twoPairsMap = Arrays.stream(this.hand.getCards()).collect(groupingBy(Card::getCardNumber, counting()));
        return twoPairsMap.keySet().stream().filter(x -> twoPairsMap.get(x) == 2).sorted().toList();
    }

    public boolean hasTwoPairs() {
        return pairOfCards.size() == 2;
    }

    public CardNumber getFirstPairCard() {
        return pairOfCards.get(0);
    }

    public CardNumber getSecondPairCard() {
        return pairOfCards.get(1);
    }

}
