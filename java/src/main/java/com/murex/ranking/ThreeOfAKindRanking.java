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
        if (threeOfAKindWhiteHand.hasThreeOfAKind()) {
            return Result.aMatchResult("White wins. - with three of a kind: " + getCardValue(threeOfAKindWhiteHand));
        }

        List<Card> blackList = threeOfAKindBlackHand.extractThreeOfAKind();
        if (threeOfAKindBlackHand.hasThreeOfAKind()) {
            return Result.aMatchResult("Black wins. - with three of a kind: " + getCardValue(threeOfAKindBlackHand));
        }

        return Result.aNoMatchResult();
    }

    private static String getCardValue(ThreeOfAKindHand threeOfAKindWhiteHand) {
        return threeOfAKindWhiteHand.extractThreeOfAKind().get(0).getValue();
    }

}
