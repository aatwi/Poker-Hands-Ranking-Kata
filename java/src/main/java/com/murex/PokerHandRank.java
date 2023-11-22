package com.murex;

public abstract class PokerHandRank {
    private final Hand blackHand;
    private final Hand whiteHand;

    public PokerHandRank(Hand blackHand, Hand whiteHand) {
        this.blackHand = blackHand;
        this.whiteHand = whiteHand;
    }

    public String verify() {
        return null;
    }
}
