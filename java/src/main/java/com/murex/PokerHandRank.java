package com.murex;

import java.util.Optional;

public abstract class PokerHandRank {
    protected final Hand blackHand;
    protected final Hand whiteHand;

    public PokerHandRank(Hand blackHand, Hand whiteHand) {
        this.blackHand = blackHand;
        this.whiteHand = whiteHand;
    }

    public Optional<String> verify() {
        return Optional.empty();
    }

    public Result getResult() {
        Optional<String> verify = verify();
        if(verify.isPresent()) {
            return new Result(true, verify.get());
        }
        return new Result(false, "");
    }
}
