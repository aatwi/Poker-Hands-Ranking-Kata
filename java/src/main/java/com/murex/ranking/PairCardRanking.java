package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.ResultHelper;
import com.murex.hands.PairHand;

public class PairCardRanking extends HandRanking {

    private final PairHand blackPairHand;
    private final PairHand whitePairHand;

    public PairCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        blackPairHand = new PairHand(this.blackHand);
        whitePairHand = new PairHand(this.whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if (bothHandsHavePairs()) {
            return getHigherPair();
        }
        if (bothHandsHaveNoPairs()) {
            return ResultHelper.aNoMatchResult();
        }
        PairHand winningPairHand = blackPairHand.hasPair() ? blackPairHand : whitePairHand;
        return ResultHelper.aPairWinningResult(winningPairHand.getHand(), winningPairHand.getPairCard(), false);
    }

    private Result getHigherPair() {
        int comparison = blackPairHand.getPairCard().compareTo(whitePairHand.getPairCard());
        if (comparison == 0) {
            return ResultHelper.aNoMatchResult();
        }

        PairHand winningHand = comparison > 0 ? blackPairHand : whitePairHand;
        return ResultHelper.aPairWinningResult(winningHand.getHand(), winningHand.getPairCard(), true);
    }

    private boolean bothHandsHaveNoPairs() {
        return !blackPairHand.hasPair() && !whitePairHand.hasPair();
    }

    private boolean bothHandsHavePairs() {
        return this.blackPairHand.hasPair() && this.whitePairHand.hasPair();
    }
}
