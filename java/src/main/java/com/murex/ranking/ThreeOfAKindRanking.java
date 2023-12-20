package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

public class ThreeOfAKindRanking extends HandRanking {
    private final Hand blackHand;
    private final Hand whiteHand;

    public ThreeOfAKindRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        this.blackHand = blackHand;
        this.whiteHand = whiteHand;
    }

    @Override
    public Result getMatchingResult() {
        return Result.aNoMatchResult();
    }
}
