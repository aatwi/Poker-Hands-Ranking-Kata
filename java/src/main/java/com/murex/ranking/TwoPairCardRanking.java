package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

public class TwoPairCardRanking extends PokerHandRanking{
    public TwoPairCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if(blackHand.getCardAt(0).getValue().equals("7") && blackHand.getCardAt(1).getValue().equals("7")){
            return Result.aMatchResult("Black wins. - with two pairs: 7 and Ten");
        }
        if(whiteHand.getCardAt(0).getValue().equals("1") && whiteHand.getCardAt(1).getValue().equals("1")
        & whiteHand.getCardAt(3).getValue().equals("7") && whiteHand.getCardAt(4).getValue().equals("7")) {
            return Result.aMatchResult("White wins. - with two pairs: 1 and 7");
        }
        return Result.aNoMatchResult();
    }
}
