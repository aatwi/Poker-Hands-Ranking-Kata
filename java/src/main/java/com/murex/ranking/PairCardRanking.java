package com.murex.ranking;

import com.murex.CardNumber;
import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.PairHand;

import static com.murex.Result.*;

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
            return aNoMatchResult();
        }
        PairHand winningPairHand = blackPairHand.hasPair() ? blackPairHand : whitePairHand;
        return aPairWinningResult(winningPairHand.getHand(), winningPairHand.getPairCard());
    }

    private Result getHigherPair() {
        int comparison = blackPairHand.getPairCard().compareTo(whitePairHand.getPairCard());
        if (comparison == 0) {
            return aNoMatchResult();
        }

        PairHand winningHand = comparison > 0 ? blackPairHand : whitePairHand;
        return aPairWinningResult(winningHand.getHand(), winningHand.getPairCard());
    }

    private boolean bothHandsHaveNoPairs() {
        return !blackPairHand.hasPair() && !whitePairHand.hasPair();
    }

    private boolean bothHandsHavePairs() {
        return this.blackPairHand.hasPair() && this.whitePairHand.hasPair();
    }
}
