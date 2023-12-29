package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class FullHouseRanking extends HandRanking{
    public FullHouseRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if(hasFullHouse(blackHand)) {
            return Result.aMatchResult("Black wins. - with full house");
        }

        if(hasFullHouse(whiteHand)) {
            return Result.aMatchResult("White wins. - with full house");
        }
        return super.getMatchingResult();
    }

    private boolean hasFullHouse(Hand hand) {
        Map<Card, Long> cardGroupsMap = Arrays.stream(hand.getCards()).collect(groupingBy(Function.identity(), counting()));
        Optional<Card> pairCards = cardGroupsMap.keySet().stream().filter(card -> cardGroupsMap.get(card) == 2).findAny();
        Optional<Card> trioCards = cardGroupsMap.keySet().stream().filter(card -> cardGroupsMap.get(card) == 3).findAny();

        return pairCards.isPresent() && trioCards.isPresent();
    }
}
