package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class TwoPairCardRanking extends PokerHandRanking {
    public TwoPairCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        List<Card> blackPairs = getTwoPairs(blackHand);
        List<Card> whitePairs = getTwoPairs(whiteHand);

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

    private List<Card> getTwoPairs(Hand blackHand) {
        Map<Card, Long> collect = Arrays.stream(blackHand.getCards()).collect(groupingBy(Function.identity(), counting()));
        return collect.keySet().stream().filter(x -> collect.get(x) == 2).sorted().toList();
    }
}
