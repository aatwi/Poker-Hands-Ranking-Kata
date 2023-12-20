package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.ThreeOfAKindHand;

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
        if (bothHaveThreeOfAKindCards()) {
            return getHigherHand();
        }

        if (bothHaveNoThreeOfAKindCards()) {
            return Result.aNoMatchResult();
        }

        if (threeOfAKindWhiteHand.hasThreeOfAKind()) {
            return Result.aMatchResult("White wins. - with three of a kind: " + threeOfAKindWhiteHand.getCard().getValue());
        }

        if (threeOfAKindBlackHand.hasThreeOfAKind()) {
            return Result.aMatchResult("Black wins. - with three of a kind: " + threeOfAKindBlackHand.getCard().getValue());
        }

        return Result.aNoMatchResult();
    }

    private Result getHigherHand() {
        Card whiteCard = threeOfAKindWhiteHand.getCard();
        Card blackCard = threeOfAKindBlackHand.getCard();
        int comparison = blackCard.compareTo(whiteCard);
        ThreeOfAKindHand winningHand = comparison > 0 ? threeOfAKindBlackHand : threeOfAKindWhiteHand;
        return Result.aMatchResult(winningHand.getHand().getName() + " wins. - with three of a kind: " + winningHand.getCard().getValue());
    }

    private boolean bothHaveNoThreeOfAKindCards() {
        return !threeOfAKindWhiteHand.hasThreeOfAKind() && !threeOfAKindBlackHand.hasThreeOfAKind();
    }

    private boolean bothHaveThreeOfAKindCards() {
        return threeOfAKindWhiteHand.hasThreeOfAKind() && threeOfAKindBlackHand.hasThreeOfAKind();
    }

}
