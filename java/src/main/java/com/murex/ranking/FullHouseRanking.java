package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.FullHouseHand;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class FullHouseRanking extends HandRanking{

    private final FullHouseHand blackFullHouseHand;
    private final FullHouseHand whiteFullHouseHand;

    public FullHouseRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        blackFullHouseHand = new FullHouseHand(blackHand);
        whiteFullHouseHand = new FullHouseHand(whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if(hasFullHouse(blackFullHouseHand)) {
            return Result.aMatchResult("Black wins. - with full house");
        }

        if(hasFullHouse(whiteFullHouseHand)) {
            return Result.aMatchResult("White wins. - with full house");
        }
        return super.getMatchingResult();
    }

    public boolean hasFullHouse(FullHouseHand whiteFullHouseHand) {
        Hand hand = whiteFullHouseHand.getHand();
        Map<Card, Long> cardGroupsMap = Arrays.stream(hand.getCards()).collect(groupingBy(Function.identity(), counting()));
        Optional<Card> pairCards = cardGroupsMap.keySet().stream().filter(card -> cardGroupsMap.get(card) == 2).findAny();
        Optional<Card> trioCards = cardGroupsMap.keySet().stream().filter(card -> cardGroupsMap.get(card) == 3).findAny();

        return pairCards.isPresent() && trioCards.isPresent();
    }
}
