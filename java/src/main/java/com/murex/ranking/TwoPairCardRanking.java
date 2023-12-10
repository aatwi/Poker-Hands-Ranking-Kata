package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;
import com.murex.TwoPairsHand;

import java.util.List;

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

        if (blackTwoPairsHand.hasTwoPairs()) {
            return Result.aMatchResult(blackTwoPairsHand.getHandName() + " wins. - with two pairs: " + blackTwoPairsHand.getFirstPair() + " and " + blackTwoPairsHand.getSecondPair().getValue());
        }

        return Result.aMatchResult(whiteTwoPairsHand.getHandName() + " wins. - with two pairs: " + whiteTwoPairsHand.getFirstPair() + " and " + whiteTwoPairsHand.getSecondPair().getValue());
    }

    private static boolean bothHandsHaveTwoPairs(TwoPairsHand blackTwoPairsHand, TwoPairsHand whiteTwoPairsHand) {
        return blackTwoPairsHand.hasTwoPairs() && whiteTwoPairsHand.hasTwoPairs();
    }

    private static boolean noHandHasTwoPairs(TwoPairsHand blackTwoPairsHand, TwoPairsHand whiteTwoPairsHand) {
        return !blackTwoPairsHand.hasTwoPairs() && !whiteTwoPairsHand.hasTwoPairs();
    }

    private Result getHigherHand(TwoPairsHand blackTwoPairsHand, TwoPairsHand whiteTwoPairsHand) {
        int comparison = blackTwoPairsHand.getSecondPair().compareTo(whiteTwoPairsHand.getSecondPair());
        Hand winner = comparison > 0 ? blackTwoPairsHand.getHand(): whiteTwoPairsHand.getHand();
        List<Card> winnerCards = comparison > 0 ?  blackTwoPairsHand.getTwoPairs() : whiteTwoPairsHand.getTwoPairs();
        return Result.aMatchResult(winner.getName() + " wins. - with two pairs: " + winnerCards.get(0).getValue() + " and " + winnerCards.get(1).getValue());
    }
}
