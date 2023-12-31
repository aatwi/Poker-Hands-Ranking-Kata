package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.ResultHelper;
import com.murex.hands.ThreeOfAKindHand;

import static com.murex.ResultHelper.aThreeOfAKindWinningResult;

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
            return ResultHelper.aNoMatchResult();
        }

        ThreeOfAKindHand winningHand = threeOfAKindBlackHand.hasThreeOfAKind() ? threeOfAKindBlackHand : threeOfAKindWhiteHand;
        return aThreeOfAKindWinningResult(winningHand.getHand(), winningHand.getCard(), false);

    }

    private Result getHigherHand() {
        int comparison = threeOfAKindBlackHand.getCard().compareTo(threeOfAKindWhiteHand.getCard());
        if (comparison == 0) {
            return ResultHelper.aNoMatchResult();
        }

        ThreeOfAKindHand winningHand = comparison > 0 ? threeOfAKindBlackHand : threeOfAKindWhiteHand;
        return aThreeOfAKindWinningResult(winningHand.getHand(), winningHand.getCard(), true);
    }

    private boolean bothHaveNoThreeOfAKindCards() {
        return !threeOfAKindWhiteHand.hasThreeOfAKind() && !threeOfAKindBlackHand.hasThreeOfAKind();
    }

    private boolean bothHaveThreeOfAKindCards() {
        return threeOfAKindWhiteHand.hasThreeOfAKind() && threeOfAKindBlackHand.hasThreeOfAKind();
    }
}
