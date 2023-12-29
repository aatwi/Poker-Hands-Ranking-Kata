package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.FourOfAKindHand;


public class FourOfAKindRanking extends HandRanking {

    private final FourOfAKindHand whiteFourOfAKindHand;
    private final FourOfAKindHand blackFourOfAKindHand;

    public FourOfAKindRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        blackFourOfAKindHand = new FourOfAKindHand(blackHand);
        whiteFourOfAKindHand = new FourOfAKindHand(whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if (blackFourOfAKindHand.hasFourOfAKind()) {
            return Result.aMatchResult("Black wins. - with four of a kind");
        }
        if (whiteFourOfAKindHand.hasFourOfAKind()) {
            return Result.aMatchResult("White wins. - with four of a kind");
        }
        return super.getMatchingResult();
    }

}
