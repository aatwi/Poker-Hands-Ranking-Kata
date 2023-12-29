package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class FourOfAKindRanking extends HandRanking {
    public FourOfAKindRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if (hasFourOfAKind()) {
            return Result.aMatchResult("Black wins. - with four of a kind");
        }
        if (whiteHand.getCardAt(1).getCharValue() == whiteHand.getCardAt(2).getCharValue()
                && whiteHand.getCardAt(2).getCharValue() == whiteHand.getCardAt(3).getCharValue()
                && whiteHand.getCardAt(3).getCharValue() == whiteHand.getCardAt(4).getCharValue()) {
            return Result.aMatchResult("White wins. - with four of a kind");
        }
        return super.getMatchingResult();
    }

    private boolean hasFourOfAKind() {
        Map<Card, Long> cardGroupsMap = Arrays.stream(blackHand.getCards()).collect(groupingBy(Function.identity(), counting()));
        return cardGroupsMap.keySet().stream().filter(card -> cardGroupsMap.get(card) == 4).findAny().isPresent();
    }
}
