package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.HighHand;
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
        return aMatchResult(buildPairCardsMessage(winningPairHand.getHand(), winningPairHand.getPairValue()));
    }

    private Result getHigherPair() {
        int comparison = blackPairHand.getPairValue().compareTo(whitePairHand.getPairValue());
        if (comparison == 0) {
            return aNoMatchResult();
        }

        PairHand winningHand = comparison > 0 ? blackPairHand : whitePairHand;
        return aMatchResult(buildPairCardsMessage(winningHand.getHand(), winningHand.getPairValue()));

    }

    private boolean bothHandsHaveNoPairs() {
        return !blackPairHand.hasPair() && !whitePairHand.hasPair();
    }

    private String buildPairAndHighHandMessage(HighHand hand, String cardValue) {
        return hand.getHand().getName() + " wins. - with Pair cards and higher rank: " + cardValue + " and " + hand.getHighCard().getValue();
    }

    private String buildPairCardsMessage(Hand hand, String cardValue) {
        return hand.getName() + " wins. - with Pair cards: " + cardValue;
    }

    private boolean bothHandsHavePairs() {
        return this.blackPairHand.hasPair() && this.whitePairHand.hasPair();
    }
}
