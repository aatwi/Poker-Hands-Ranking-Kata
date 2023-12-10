package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.TwoPairsHand;

public class TwoPairCardRanking extends PokerHandRanking {

    private final TwoPairsHand blackTwoPairsHand;
    private final TwoPairsHand whiteTwoPairsHand;

    public TwoPairCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        whiteTwoPairsHand = new TwoPairsHand(whiteHand);
        blackTwoPairsHand = new TwoPairsHand(blackHand);
    }

    @Override
    public Result getMatchingResult() {
        if (noHandHasTwoPairs(blackTwoPairsHand, whiteTwoPairsHand)) {
            return Result.aNoMatchResult();
        }

        if (bothHandsHaveTwoPairs(blackTwoPairsHand, whiteTwoPairsHand)) {
            return getHigherHand(blackTwoPairsHand, whiteTwoPairsHand);
        }

        TwoPairsHand winningPair = blackTwoPairsHand.hasTwoPairs() ? blackTwoPairsHand : whiteTwoPairsHand;
        return Result.aMatchResult(winningPair.getHandName() + " wins. - with two pairs: " + winningPair.getFirstPair() + " and " + winningPair.getSecondPair());
    }

    private static boolean bothHandsHaveTwoPairs(TwoPairsHand blackTwoPairsHand, TwoPairsHand whiteTwoPairsHand) {
        return blackTwoPairsHand.hasTwoPairs() && whiteTwoPairsHand.hasTwoPairs();
    }

    private static boolean noHandHasTwoPairs(TwoPairsHand blackTwoPairsHand, TwoPairsHand whiteTwoPairsHand) {
        return !blackTwoPairsHand.hasTwoPairs() && !whiteTwoPairsHand.hasTwoPairs();
    }

    private Result getHigherHand(TwoPairsHand blackTwoPairsHand, TwoPairsHand whiteTwoPairsHand) {
        int comparison = blackTwoPairsHand.getSecondPairCard().compareTo(whiteTwoPairsHand.getSecondPairCard());
        TwoPairsHand winningPair = comparison > 0 ? blackTwoPairsHand : whiteTwoPairsHand;
        return Result.aMatchResult(winningPair.getHandName() + " wins. - with two pairs: " + winningPair.getFirstPair() + " and " + winningPair.getSecondPair());
    }

}
