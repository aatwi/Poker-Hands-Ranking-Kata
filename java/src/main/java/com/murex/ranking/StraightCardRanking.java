package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

public class StraightCardRanking extends HandRanking{
    public StraightCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        boolean straightWhite = false;
        boolean straightBlack = false;
        for (int i = 1; i < whiteHand.getCards().length; i++) {
            int previousCard = whiteHand.getCardAt(i - 1).getIntValue();
            int currentCard = whiteHand.getCardAt(i).getIntValue();
            if(currentCard != previousCard + 1){
                straightWhite = false;
                break;
            }
            straightWhite = true;
        }
        for (int i = 1; i < blackHand.getCards().length; i++) {
            int previousCard = blackHand.getCardAt(i - 1).getIntValue();
            int currentCard = blackHand.getCardAt(i).getIntValue();
            if(currentCard != previousCard + 1){
                straightBlack = false;
                break;
            }
            straightBlack = true;
        }
        if(straightWhite) {
            return Result.aMatchResult("White wins. - with straight cards");
        }
        if(straightBlack) {
            return Result.aMatchResult("Black wins. - with straight cards");
        }
        return super.getMatchingResult();
    }
}
