package com.murex.hands;

import com.murex.CardNumber;
import com.murex.Hand;

public final class RoyalFlushHand {
    private final Hand hand;
    private final StraightFlushHand straightFlushHand;

    public RoyalFlushHand(Hand hand) {
        this.hand = hand;
        straightFlushHand = new StraightFlushHand(hand);
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isRoyalFlush() {
        if (this.straightFlushHand.isStraightFlush()) {
            return hand.getCardAt(4).getCardNumber().equals(CardNumber.ACE);
        }
        return false;
    }
}
