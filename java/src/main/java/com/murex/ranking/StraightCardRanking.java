package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.StraightHand;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;

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
        if(whiteStraight.isStraight() && blackStraight.isStraight()) {
            int comparison = blackStraight.getHand().getCardAt(0).compareTo(whiteStraight.getHand().getCardAt(0));
            if(comparison == 0) {
                return aNoMatchResult();
            }
            if(comparison > 0) {
                return aMatchResult("Black wins. - with straight cards and higher cards");
            }
                return aMatchResult("White wins. - with straight cards and higher cards");
//            }
//            return aMatchResult("Black wins. - with straight cards and higher cards");
        }
        if(whiteStraight.isStraight()) {
            return buildMatchingResult(whiteStraight);
        }
        if(blackStraight.isStraight()) {
            return buildMatchingResult(blackStraight);
        }
        return super.getMatchingResult();
    }

    private static Result buildMatchingResult(StraightHand whiteStraight) {
        return aMatchResult(whiteStraight.getHand().getName() + " wins. - with straight cards");
    }

}
