package com.murex.hands;

import com.murex.Hand;

public final class FlushHand {
    private final Hand hand;

    public FlushHand(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isFlush() {
        for (int index = 1; index < 5; index++) {
            if (!getHand().getCardAt(index - 1).getSuite().equals(getHand().getCardAt(index).getSuite())) {
                return false;
            }
        }
        return true;
    }
}
