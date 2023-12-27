package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

public class StraightCardRanking extends HandRanking{
    public StraightCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        boolean straightBlack = isAStraight(blackHand);
        boolean straightWhite = isAStraight(whiteHand);
        if(straightWhite) {
            return Result.aMatchResult("White wins. - with straight cards");
        }
        if(straightBlack) {
            return Result.aMatchResult("Black wins. - with straight cards");
        }
        return super.getMatchingResult();
    }

    private boolean isAStraight(Hand hand) {
        for (int i = 1; i < hand.getCards().length; i++) {
            int previousCard = hand.getCardAt(i - 1).getIntValue();
            int currentCard = hand.getCardAt(i).getIntValue();
            if(currentCard != previousCard + 1){
                return false;
            }
        }
        return true;
    }
}
