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

public final class PairHand {
    private final Hand hand;
    private final Optional<CardNumber> cardOfPairs;

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

    public CardNumber getPairCard() {
        return cardOfPairs.orElse(null);
    }

    private Optional<CardNumber> extractCardOfPairs() {
        Map<CardNumber, Long> cardsPairMap = Arrays.stream(hand.getCards()).collect(groupingBy(Card::getCardNumber, counting()));
        return cardsPairMap.keySet().stream().filter(card -> cardsPairMap.get(card) == 2).findAny();
    }
}
