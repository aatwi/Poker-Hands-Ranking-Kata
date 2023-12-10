package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

public abstract class HandRanking {
    protected final Hand blackHand;
    protected final Hand whiteHand;

    public HandRanking(Hand blackHand, Hand whiteHand) {
        this.blackHand = blackHand;
        this.whiteHand = whiteHand;
    }

    public Result getMatchingResult() {
        return Result.aNoMatchResult();
    }
}
