package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.ResultHelper;

public class TieRanking extends HandRanking {
    public TieRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        for (int index = 0; index < 5; index++) {
            if (blackHand.getCardAt(index).compareTo(whiteHand.getCardAt(index)) != 0) {
                return ResultHelper.aNoMatchResult();
            }
        }
        return ResultHelper.aTieResult();
    }
}
