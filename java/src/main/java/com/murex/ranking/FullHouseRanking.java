package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

public class FullHouseRanking extends HandRanking{
    public FullHouseRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if(hasFullHouse(blackHand)) {
            return Result.aMatchResult("Black wins. - with full house");
        }

        if(hasFullHouse(whiteHand)) {
            return Result.aMatchResult("White wins. - with full house");
        }
        return super.getMatchingResult();
    }

    private boolean hasFullHouse(Hand blackHand) {
        return (blackHand.getCardAt(0).getCharValue() == blackHand.getCardAt(1).getCharValue()
                && blackHand.getCardAt(1).getCharValue() == blackHand.getCardAt(2).getCharValue())
                && blackHand.getCardAt(3).getCharValue() == blackHand.getCardAt(4).getCharValue();
    }
}
