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
        if(whiteHand.getCardAt(1).getCharValue() == '9' && whiteHand.getCardAt(2).getCharValue() == '9' && whiteHand.getCardAt(3).getCharValue() == '9') {
            Map<Card, Long> twoPairsMap = Arrays.stream(whiteHand.getCards()).collect(groupingBy(Function.identity(), counting()));
            List<Card> list = twoPairsMap.keySet().stream().filter(x -> twoPairsMap.get(x) == 3).sorted().toList();
            if (list.size() == 1) {
            return Result.aMatchResult("White wins. - with three of a kind: " + list.get(0).getCharValue());
            }
        }
        if(whiteHand.getCardAt(2).getCharValue() == 'T' && whiteHand.getCardAt(3).getCharValue() == 'T' && whiteHand.getCardAt(4).getCharValue() == 'T') {
            return Result.aMatchResult("White wins. - with three of a kind: Ten");
        }
        if(blackHand.getCardAt(0).getCharValue() == '7' && blackHand.getCardAt(1).getCharValue() == '7' && blackHand.getCardAt(2).getCharValue() == '7') {
            return Result.aMatchResult("Black wins. - with three of a kind: 7");
        }
        return Result.aNoMatchResult();
    }
}
