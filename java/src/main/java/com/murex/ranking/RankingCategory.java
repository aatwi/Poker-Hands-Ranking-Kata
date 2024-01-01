package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.ResultHelper;

import static com.murex.ResultHelper.aNoWinner;

public abstract class OrderRanking {
    protected final Hand blackHand;
    protected final Hand whiteHand;

    public OrderRanking(Hand blackHand, Hand whiteHand) {
        this.blackHand = blackHand;
        this.whiteHand = whiteHand;
    }

    public Result evaluate() {
        return aNoWinner();
    }
}
