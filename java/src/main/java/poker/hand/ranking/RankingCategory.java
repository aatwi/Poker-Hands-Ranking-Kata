package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

import static com.murex.ResultHelper.aNoWinner;

public abstract class RankingCategory {
    protected final Hand blackHand;
    protected final Hand whiteHand;

    public RankingCategory(Hand blackHand, Hand whiteHand) {
        this.blackHand = blackHand;
        this.whiteHand = whiteHand;
    }

    public Result evaluate() {
        return aNoWinner();
    }
}
