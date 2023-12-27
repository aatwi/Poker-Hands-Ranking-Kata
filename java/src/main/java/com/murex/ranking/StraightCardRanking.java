package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.StraightHand;

public class StraightCardRanking extends HandRanking{

    private final StraightHand whiteStraight;
    private final StraightHand blackStraight;

    public StraightCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        whiteStraight = new StraightHand(whiteHand);
        blackStraight = new StraightHand(blackHand);
    }

    @Override
    public Result getMatchingResult() {
        boolean straightBlack = blackStraight.isStraight();
        boolean straightWhite = whiteStraight.isStraight();
        if(straightWhite) {
            return Result.aMatchResult("White wins. - with straight cards");
        }
        if(straightBlack) {
            return Result.aMatchResult("Black wins. - with straight cards");
        }
        return super.getMatchingResult();
    }

}
