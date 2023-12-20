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

public class ThreeOfAKindRanking extends HandRanking {
    private final Hand blackHand;
    private final Hand whiteHand;

    public ThreeOfAKindRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        this.blackHand = blackHand;
        this.whiteHand = whiteHand;
    }

    @Override
    public Result getMatchingResult() {
        List<Card> list = extractThreeOfAKind(whiteHand);
        if (list.size() == 1) {
            return Result.aMatchResult("White wins. - with three of a kind: " + list.get(0).getValue());
        }

        List<Card> blackList = extractThreeOfAKind(blackHand);
        if (blackList.size() == 1) {
            return Result.aMatchResult("Black wins. - with three of a kind: " + blackList.get(0).getValue());
        }

        return Result.aNoMatchResult();
    }

    private List<Card> extractThreeOfAKind(Hand whiteHand) {
        Map<Card, Long> twoPairsMap = Arrays.stream(whiteHand.getCards()).collect(groupingBy(Function.identity(), counting()));
        List<Card> list = twoPairsMap.keySet().stream().filter(x -> twoPairsMap.get(x) == 3).sorted().toList();
        return list;
    }
}
