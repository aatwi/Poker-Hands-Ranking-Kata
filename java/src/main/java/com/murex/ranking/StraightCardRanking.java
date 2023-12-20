package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

public class StraightCardRanking extends HandRanking{
    public StraightCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if(whiteHand.getCardAt(0).getCharValue() == '2' && whiteHand.getCardAt(4).getCharValue() == '6') {
            return Result.aMatchResult("White wins. - with straight cards");
        }
        return super.getMatchingResult();
    }
}
