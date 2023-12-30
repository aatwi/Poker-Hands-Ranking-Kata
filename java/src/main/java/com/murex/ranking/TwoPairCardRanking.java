package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.TwoPairsHand;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;

public class TwoPairCardRanking extends HandRanking {

    private final TwoPairsHand blackTwoPairsHand;
    private final TwoPairsHand whiteTwoPairsHand;

    public TwoPairCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        whiteTwoPairsHand = new TwoPairsHand(whiteHand);
        blackTwoPairsHand = new TwoPairsHand(blackHand);
    }

    @Override
    public Result getMatchingResult() {
        if (noHandHasTwoPairs()) {
            return aNoMatchResult();
        }

        if (bothHandsHaveTwoPairs()) {
            return getHigherHand();
        }

        TwoPairsHand winningPair = blackTwoPairsHand.hasTwoPairs() ? blackTwoPairsHand : whiteTwoPairsHand;
        return aMatchResult(buildWinningMessage(winningPair));
    }

    private boolean bothHandsHaveTwoPairs() {
        return this.blackTwoPairsHand.hasTwoPairs() && this.whiteTwoPairsHand.hasTwoPairs();
    }

    private boolean noHandHasTwoPairs() {
        return !this.blackTwoPairsHand.hasTwoPairs() && !this.whiteTwoPairsHand.hasTwoPairs();
    }

    private Result getHigherHand() {
        int comparison = compareForHigherHands();
        if(comparison == 0){
            return aNoMatchResult();
        }

        TwoPairsHand winningPair = comparison > 0 ? this.blackTwoPairsHand : this.whiteTwoPairsHand;
        return aMatchResult(buildWinningMessage(winningPair));
    }

    private int compareForHigherHands() {
        int comparison = this.blackTwoPairsHand.getSecondPairCard().compareTo(this.whiteTwoPairsHand.getSecondPairCard());
        return comparison != 0 ? comparison : this.blackTwoPairsHand.getFirstPairCard().compareTo(this.whiteTwoPairsHand.getFirstPairCard());
    }

    private static String buildWinningMessage(TwoPairsHand winningPair) {
        return winningPair.getHandName() + " wins. - with two pairs: " + winningPair.getFirstPairCard().toString() + " and " + winningPair.getSecondPairCard().toString();
    }

}
