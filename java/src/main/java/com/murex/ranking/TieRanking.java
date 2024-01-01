package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.ResultHelper;

public class TieRanking extends OrderRanking {
    public TieRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result evaluate() {
        for (int index = 0; index < 5; index++) {
            if (blackHand.getCardAt(index).compareTo(whiteHand.getCardAt(index)) != 0) {
                return ResultHelper.aNoWinner();
            }
        }
        return ResultHelper.aTieResult();
    }
}
