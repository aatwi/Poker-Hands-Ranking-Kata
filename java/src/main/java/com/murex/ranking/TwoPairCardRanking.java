package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class TwoPairCardRanking extends PokerHandRanking{
    public TwoPairCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        List<Card> blackPairs = getTwoPairs(blackHand);
        if(blackPairs.size() == 2){
            return Result.aMatchResult("Black wins. - with two pairs: "+blackPairs.get(0).getValue()+" and " + blackPairs.get(1).getValue());
        }
        if(whiteHand.getCardAt(0).getValue().equals("1") && whiteHand.getCardAt(1).getValue().equals("1")
        && whiteHand.getCardAt(3).getValue().equals("7") && whiteHand.getCardAt(4).getValue().equals("7")) {
            List<Card> twoPairs = getTwoPairs(whiteHand);
            return Result.aMatchResult("White wins. - with two pairs: "+twoPairs.get(0).getValue()+" and " + twoPairs.get(1).getValue());
        }
        return Result.aNoMatchResult();
    }

    private List<Card> getTwoPairs(Hand blackHand) {
        Map<Card, Long> collect = Arrays.stream(blackHand.getCards()).collect(groupingBy(Function.identity(), counting()));
        return collect.keySet().stream().filter(x -> collect.get(x) == 2).sorted().toList();
    }
}
