package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.ResultHelper;
import com.murex.hands.StraightHand;

public class StraightHandRanking extends HandRanking{

    private final StraightHand whiteStraight;
    private final StraightHand blackStraight;

    public StraightHandRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        whiteStraight = new StraightHand(whiteHand);
        blackStraight = new StraightHand(blackHand);
    }

    @Override
    public Result getMatchingResult() {
        if(bothHandsAreStraight()) {
            return getHigherHand();
        }

        if(!whiteStraight.isStraight() && !blackStraight.isStraight()) {
            return ResultHelper.aNoMatchResult();
        }

        return blackStraight.isStraight() ? buildMatchingResult(blackStraight) : buildMatchingResult(whiteStraight);
    }

    private boolean bothHandsAreStraight() {
        return whiteStraight.isStraight() && blackStraight.isStraight();
    }

    private Result getHigherHand() {
        int comparison = blackStraight.getHand().getCardAt(0).compareTo(whiteStraight.getHand().getCardAt(0));
        if(comparison == 0) {
            return ResultHelper.aNoMatchResult();
        }
        return comparison > 0 ? buildMessageWithHigherCards(blackStraight) : buildMessageWithHigherCards(whiteStraight);
    }

    private static Result buildMessageWithHigherCards(StraightHand straightHand) {
        return ResultHelper.aStraightWinningResult(straightHand.getHand(), true);
    }

    private static Result buildMatchingResult(StraightHand straightHand) {
        return ResultHelper.aStraightWinningResult(straightHand.getHand(), false);
    }
}
