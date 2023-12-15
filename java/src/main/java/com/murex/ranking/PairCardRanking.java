package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.PairHand;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;

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
        return aMatchResult(buildPairCardsMessage(winningPairHand.getHand(), winningPairHand.getPairCard(), winningPairHand.getPairValue()));
    }

    private Result getHigherPair() {
        int comparison = blackPairHand.getPairValue().compareTo(whitePairHand.getPairValue());
        if (comparison == 0) {
            return aNoMatchResult();
        }

        PairHand winningHand = comparison > 0 ? blackPairHand : whitePairHand;
        return aMatchResult(buildPairCardsMessage(winningHand.getHand(), winningHand.getPairCard(), winningHand.getPairValue()));
    }

    private boolean bothHandsHaveNoPairs() {
        return !blackPairHand.hasPair() && !whitePairHand.hasPair();
    }

    private String buildPairCardsMessage(Hand hand, Card pairCard, String cardValue) {
        return hand.getName() + " wins. - with Pair cards: " + cardValue;
    }

    private boolean bothHandsHavePairs() {
        return this.blackPairHand.hasPair() && this.whitePairHand.hasPair();
    }
}
