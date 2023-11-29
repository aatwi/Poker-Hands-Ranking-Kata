package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

import java.util.Optional;

public abstract class PokerHandRanking {
    protected final Hand blackHand;
    protected final Hand whiteHand;

    public PokerHandRanking(Hand blackHand, Hand whiteHand) {
        this.blackHand = blackHand;
        this.whiteHand = whiteHand;
    }

    public Optional<String> verify() {
        return Optional.empty();
    }

    public Result getMatchResult() {
        Optional<String> verify = verify();
        return verify.map(Result::aMatchResult).orElseGet(Result::aNoMatchResult);
    }
}
