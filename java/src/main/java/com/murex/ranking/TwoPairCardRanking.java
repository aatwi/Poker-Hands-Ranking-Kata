package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;
import com.murex.TwoPairsHand;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class TwoPairCardRanking extends PokerHandRanking {

    private final List<Card> blackPairs;
    private final List<Card> whitePairs;
    private final TwoPairsHand blackTwoPairsHand;
    private final TwoPairsHand whiteTwoPairsHand;

    public TwoPairCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        whiteTwoPairsHand = new TwoPairsHand(whiteHand);
        blackTwoPairsHand = new TwoPairsHand(blackHand);
        blackPairs = getTwoPairs(blackTwoPairsHand);
        whitePairs = getTwoPairs(whiteTwoPairsHand);
    }

    @Override
    public Result getMatchingResult() {
        if (noHandHasTwoPairs(blackPairs, whitePairs)) {
            return Result.aNoMatchResult();
        }

        if (bothHandsHaveTwoPairs(blackPairs, whitePairs)) {
            return getHigherHand(blackPairs, whitePairs);
        }

        if (blackPairs.size() == 2) {
            return Result.aMatchResult(blackHand.getName() + " wins. - with two pairs: " + blackPairs.get(0).getValue() + " and " + blackPairs.get(1).getValue());
        }

        return Result.aMatchResult("White wins. - with two pairs: " + whitePairs.get(0).getValue() + " and " + whitePairs.get(1).getValue());
    }

    private static boolean bothHandsHaveTwoPairs(List<Card> blackPairs, List<Card> whitePairs) {
        return blackPairs.size() == 2 && whitePairs.size() == 2;
    }

    private static boolean noHandHasTwoPairs(List<Card> blackPairs, List<Card> whitePairs) {
        return blackPairs.size() != 2 && whitePairs.size() != 2;
    }

    private Result getHigherHand(List<Card> blackPairs, List<Card> whitePairs) {
        int comparison = blackPairs.get(1).compareTo(whitePairs.get(1));
        Hand winner = comparison > 0 ? blackHand : whiteHand;
        List<Card> winnerCards = comparison > 0 ? blackPairs : whitePairs;
        return Result.aMatchResult(winner.getName() + " wins. - with two pairs: " + winnerCards.get(0).getValue() + " and " + winnerCards.get(1).getValue());
    }

    private List<Card> getTwoPairs(TwoPairsHand twoPairsHand) {
        Map<Card, Long> collect = Arrays.stream(twoPairsHand.getHand().getCards()).collect(groupingBy(Function.identity(), counting()));
        return collect.keySet().stream().filter(x -> collect.get(x) == 2).sorted().toList();
    }
}
