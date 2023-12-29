package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.FourOfAKindHand;


public class FourOfAKindRanking extends HandRanking {

    private final FourOfAKindHand whiteFourOfAKindHand;
    private final FourOfAKindHand blackFourOfAKindHand;

    public FourOfAKindRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        blackFourOfAKindHand = new FourOfAKindHand(blackHand);
        whiteFourOfAKindHand = new FourOfAKindHand(whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if(blackFourOfAKindHand.hasFourOfAKind() && whiteFourOfAKindHand.hasFourOfAKind())  {
            int comparison = blackFourOfAKindHand.getFourOfKindCard().compareTo(whiteFourOfAKindHand.getFourOfKindCard());
            Hand winningHand = comparison > 0 ? blackFourOfAKindHand.getHand() : whiteFourOfAKindHand.getHand();
            return buildMatchingResultWithHigherHand(winningHand);
        }
        if (blackFourOfAKindHand.hasFourOfAKind()) {
            return buildMatchingResult(blackFourOfAKindHand.getHand());
        }
        if (whiteFourOfAKindHand.hasFourOfAKind()) {
            return buildMatchingResult(whiteFourOfAKindHand.getHand());
        }
        return super.getMatchingResult();
    }

    private static Result buildMatchingResultWithHigherHand(Hand hand) {
        return Result.aMatchResult(hand.getName() + " wins. - with four of a kind and higher hand");
    }

    private static Result buildMatchingResult(Hand hand) {
        return Result.aMatchResult(hand.getName() + " wins. - with four of a kind");
    }

}
