package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.ThreeOfAKindHand;

import java.util.List;

public class ThreeOfAKindRanking extends HandRanking {
    private final ThreeOfAKindHand threeOfAKindBlackHand;
    private final ThreeOfAKindHand threeOfAKindWhiteHand;

    public ThreeOfAKindRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        this.threeOfAKindBlackHand = new ThreeOfAKindHand(blackHand);
        this.threeOfAKindWhiteHand = new ThreeOfAKindHand(whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        List<Card> list = threeOfAKindWhiteHand.extractThreeOfAKind();
        if (list.size() == 1) {
            return Result.aMatchResult("White wins. - with three of a kind: " + list.get(0).getValue());
        }

        List<Card> blackList = threeOfAKindBlackHand.extractThreeOfAKind();
        if (blackList.size() == 1) {
            return Result.aMatchResult("Black wins. - with three of a kind: " + blackList.get(0).getValue());
        }

        return Result.aNoMatchResult();
    }

}
