package com.murex;

import java.util.Arrays;
import java.util.Objects;

public class Hand {
    private final String name;
    private final Card[] cards;

    public Hand(String name, Card[] cards) {
        this.name = name;
        this.cards = cards;
    }

    public static Hand buildFrom(String blackHand, String input) {
        return new Hand(blackHand,
                new Card[]{
                        new Card('2'),
                        new Card('3'),
                        new Card('5'),
                        new Card('9'),
                        new Card('K')});
    }

    public String getName() {
        return name;
    }

    public Card[] getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand = (Hand) o;
        return Objects.equals(name, hand.name) && Arrays.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(cards);
        return result;
    }

    @Override
    public String toString() {
        return "Hand{" +
               "name='" + name + '\'' +
               ", cards=" + Arrays.toString(cards) +
               '}';
    }
}
