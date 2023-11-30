package com.murex.ranking;

import com.murex.Hand;
import com.murex.PairHand;
import com.murex.Result;

import java.util.Optional;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;

public class PairCardRanking extends PokerHandRanking {

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
        String winnerCardValue = comparison > 0 ? blackPairHand.getPairValue() : whitePairHand.getPairValue();

        if (comparison == 0) {
            return getHigherHand(winnerCardValue);
        } else {
            Hand winningHad = comparison > 0 ? blackHand : whiteHand;
            return aMatchResult(buildPairCardsMessage(winningHad, winnerCardValue));
        }
    }

    private Result getHigherHand(String winnerCardValue) {
        HighCardRanking highCardRank = new HighCardRanking(blackHand, whiteHand);
        Optional<Hand> higherHand = highCardRank.getHigherHand();
        if (higherHand.isEmpty()) {
            return aNoMatchResult();
        }
        return aMatchResult(buildPairAndHighHandMessage(higherHand.get(), winnerCardValue));
    }

    private boolean bothHandsHaveNoPairs() {
        return !blackPairHand.hasPair() && !whitePairHand.hasPair();
    }

    private String buildPairAndHighHandMessage(Hand hand, String cardValue) {
        return hand.getName() + " wins. - with Pair cards and higher rank: " + cardValue + " and " + hand.getCardAt(4).getValue();
    }

    private String buildPairCardsMessage(Hand hand, String cardValue) {
        return hand.getName() + " wins. - with Pair cards: " + cardValue;
    }

    private boolean bothHandsHavePairs() {
        return this.blackPairHand.hasPair() && this.whitePairHand.hasPair();
    }
}
