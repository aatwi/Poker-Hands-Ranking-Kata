package com.murex.hands;

import com.murex.Hand;

public final class ThreeOfAKindHand {
    private final Hand hand;

    public ThreeOfAKindHand(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }
}
