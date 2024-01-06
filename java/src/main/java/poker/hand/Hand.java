package com.murex;

import java.util.Arrays;
import java.util.Objects;

public record Hand(String playerName, Card[] cards) {

    public Card getCardAt(int index) {
        return cards[index];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand = (Hand) o;
        return Objects.equals(playerName, hand.playerName) && Arrays.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(playerName);
        result = 31 * result + Arrays.hashCode(cards);
        return result;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "playerName='" + playerName + '\'' +
                ", cards=" + Arrays.toString(cards) +
                '}';
    }
}
