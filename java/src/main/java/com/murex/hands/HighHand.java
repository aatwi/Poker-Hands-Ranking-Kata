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
    
}
