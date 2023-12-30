package com.murex.hands;

import com.murex.Card;
import com.murex.CardNumber;
import com.murex.Hand;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class FullHouseHand {
    private final Hand hand;
    private Optional<CardNumber> pairCards;
    private Optional<CardNumber> trioCards;

    public FullHouseHand(Hand hand) {
        this.hand = hand;
        this.extractPairsAndTrioCards();
    }

    public Hand getHand() {
        return hand;
    }

    public Optional<CardNumber> getTrioCards() {
        return trioCards;
    }

    public boolean hasFullHouse() {
        return pairCards.isPresent() && trioCards.isPresent();
    }

    private void extractPairsAndTrioCards() {
        Map<CardNumber, Long> cardGroupsMap = Arrays.stream(hand.getCards()).collect(groupingBy(Card::getCardNumber, counting()));
        pairCards = cardGroupsMap.keySet().stream().filter(card -> cardGroupsMap.get(card) == 2).findAny();
        trioCards = cardGroupsMap.keySet().stream().filter(card -> cardGroupsMap.get(card) == 3).findAny();
    }
}
