package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;
import com.murex.TwoPairsHand;

import java.util.List;

public class TwoPairCardRanking extends PokerHandRanking {

    private final List<Card> blackPairs;
    private final List<Card> whitePairs;
    private final TwoPairsHand blackTwoPairsHand;
    private final TwoPairsHand whiteTwoPairsHand;

    public TwoPairCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        whiteTwoPairsHand = new TwoPairsHand(whiteHand);
        blackTwoPairsHand = new TwoPairsHand(blackHand);
        blackPairs = blackTwoPairsHand.getTwoPairs();
        whitePairs = whiteTwoPairsHand.getTwoPairs();
    }

    @Override
    public Result getMatchingResult() {
        if (noHandHasTwoPairs(blackTwoPairsHand, whiteTwoPairsHand)) {
            return Result.aNoMatchResult();
        }

        if (bothHandsHaveTwoPairs(blackTwoPairsHand, whiteTwoPairsHand)) {
            return getHigherHand(blackTwoPairsHand, whiteTwoPairsHand);
        }

        if (blackTwoPairsHand.hasTwoPairs()) {
            return Result.aMatchResult(blackHand.getName() + " wins. - with two pairs: " + blackPairs.get(0).getValue() + " and " + blackPairs.get(1).getValue());
        }

        return Result.aMatchResult("White wins. - with two pairs: " + whitePairs.get(0).getValue() + " and " + whitePairs.get(1).getValue());
    }

    private static boolean bothHandsHaveTwoPairs(TwoPairsHand blackTwoPairsHand, TwoPairsHand whiteTwoPairsHand) {
        return blackTwoPairsHand.hasTwoPairs() && whiteTwoPairsHand.hasTwoPairs();
    }

    private static boolean noHandHasTwoPairs(TwoPairsHand blackTwoPairsHand, TwoPairsHand whiteTwoPairsHand) {
        return !blackTwoPairsHand.hasTwoPairs() && !whiteTwoPairsHand.hasTwoPairs();
    }

    private Result getHigherHand(TwoPairsHand blackTwoPairsHand, TwoPairsHand whiteTwoPairsHand) {
        int comparison = blackTwoPairsHand.getTwoPairs().get(1).compareTo(whiteTwoPairsHand.getTwoPairs().get(1));
        Hand winner = comparison > 0 ? blackTwoPairsHand.getHand(): whiteTwoPairsHand.getHand();
        List<Card> winnerCards = comparison > 0 ?  blackTwoPairsHand.getTwoPairs() : whiteTwoPairsHand.getTwoPairs();
        return Result.aMatchResult(winner.getName() + " wins. - with two pairs: " + winnerCards.get(0).getValue() + " and " + winnerCards.get(1).getValue());
    }
}
