package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.StraightHand;

public class StraightCardRanking extends HandRanking{

    private final StraightHand whiteStraight;
    private final StraightHand blackStraight;

    public StraightCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        whiteStraight = new StraightHand(whiteHand);
        blackStraight = new StraightHand(blackHand);
    }

    @Override
    public Result getMatchingResult() {
        boolean straightBlack = isAStraight(blackStraight);
        boolean straightWhite = isAStraight(whiteStraight);
        if(straightWhite) {
            return Result.aMatchResult("White wins. - with straight cards");
        }
        if(straightBlack) {
            return Result.aMatchResult("Black wins. - with straight cards");
        }
        return super.getMatchingResult();
    }

    private boolean isAStraight(StraightHand whiteStraight) {
        Hand hand1 = whiteStraight.getHand();
        for (int i = 1; i < hand1.getCards().length; i++) {
            int previousCard = hand1.getCardAt(i - 1).getIntValue();
            int currentCard = hand1.getCardAt(i).getIntValue();
            if(currentCard != previousCard + 1){
                return false;
            }
        }
        return true;
    }
}
