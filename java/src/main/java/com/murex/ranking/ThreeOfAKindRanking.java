package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.PairHand;
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
        if(threeOfAKindWhiteHand.hasThreeOfAKind() && threeOfAKindBlackHand.hasThreeOfAKind()) {
            Card whiteCard = threeOfAKindWhiteHand.getCard();
            Card blackCard = threeOfAKindBlackHand.getCard();
            int comparison = blackCard.compareTo(whiteCard);
            ThreeOfAKindHand winningHand = comparison > 0 ? threeOfAKindBlackHand : threeOfAKindWhiteHand;
            return Result.aMatchResult(winningHand.getHand().getName() + " wins. - with three of a kind: " + winningHand.getCardValue());
        }

        if (threeOfAKindWhiteHand.hasThreeOfAKind()) {
            return Result.aMatchResult("White wins. - with three of a kind: " + threeOfAKindWhiteHand.getCardValue());
        }

        if (threeOfAKindBlackHand.hasThreeOfAKind()) {
            return Result.aMatchResult("Black wins. - with three of a kind: " + threeOfAKindBlackHand.getCardValue());
        }

        return Result.aNoMatchResult();
    }

}
