package com.murex.hands;

import com.murex.Card;
import com.murex.Hand;

import java.util.Objects;

public final class HighHand {
    private final Hand hand;
    private final Card highCard;

    public HighHand(Hand hand, int highCardIndex) {
        this.hand = hand;
        this.highCard = hand.getCardAt(highCardIndex);
    }

    public Hand getHand() {
        return hand;
    }

    public Card getHighCard() {
        return highCard;
    }

    @Override
    public String toString() {
        return "HighHand{" +
                "hand=" + hand +
                ", highCard=" + highCard +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HighHand highHand = (HighHand) o;
        return Objects.equals(hand, highHand.hand) && Objects.equals(highCard, highHand.highCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hand, highCard);
    }
}
