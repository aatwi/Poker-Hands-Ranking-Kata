package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

public class TieRanking extends PokerHandRanking {
    public TieRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        for (int index = 0; index < 5; index++) {
            if (blackHand.getCardAt(index).compareTo(whiteHand.getCardAt(index)) != 0) {
                return Result.aNoMatchResult();
            }
        }
        return Result.aMatchResult("Tie.");
    }
}