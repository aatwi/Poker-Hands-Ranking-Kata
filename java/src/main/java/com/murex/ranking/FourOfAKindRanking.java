package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.FourOfAKindHand;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class FourOfAKindRanking extends HandRanking {

    private final FourOfAKindHand fourOfAKindHand;

    public FourOfAKindRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        fourOfAKindHand = new FourOfAKindHand(whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if (hasFourOfAKind(blackHand)) {
            return Result.aMatchResult("Black wins. - with four of a kind");
        }
        if (hasFourOfAKind(whiteHand)) {
            return Result.aMatchResult("White wins. - with four of a kind");
        }
        return super.getMatchingResult();
    }

    private boolean hasFourOfAKind(Hand hand) {
        Map<Card, Long> cardGroupsMap = Arrays.stream(hand.getCards()).collect(groupingBy(Function.identity(), counting()));
        return cardGroupsMap.keySet().stream().filter(card -> cardGroupsMap.get(card) == 4).findAny().isPresent();
    }
}
