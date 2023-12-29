package com.murex.hands;

import com.murex.Hand;

public class StraightFlushHand {
    private final Hand hand;
    private final StraightHand straightHand;
    private final FlushHand flushHand;

    public StraightFlushHand(Hand hand) {
        this.hand = hand;
        straightHand = new StraightHand(hand);
        flushHand = new FlushHand(hand);
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isStraightFlush() {
        return straightHand.isStraight() && flushHand.isFlush();
    }
}
