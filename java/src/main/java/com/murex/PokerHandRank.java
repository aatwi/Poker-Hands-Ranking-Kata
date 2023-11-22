package com.murex;

public abstract class PokerHandRank {
    protected final Hand blackHand;
    protected final Hand whiteHand;

    public PokerHandRank(Hand blackHand, Hand whiteHand) {
        this.blackHand = blackHand;
        this.whiteHand = whiteHand;
    }

    public String verify() {
        return null;
    }

    public Result getResult() {
        return new Result(false, "");
    }
}
