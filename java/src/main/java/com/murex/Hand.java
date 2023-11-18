package com.murex;

import java.util.Objects;

public class Hand {
    private final String name;
    private final Card card;

    public Hand(String name, Card card) {
        this.name = name;
        this.card = card;
    }

    public String getName() {
        return name;
    }

    public Card getCard() {
        return card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand = (Hand) o;
        return Objects.equals(name, hand.name) && Objects.equals(card, hand.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, card);
    }

    @Override
    public String toString() {
        return "Hand{" +
                "name='" + name + '\'' +
                ", card=" + card +
                '}';
    }
}
