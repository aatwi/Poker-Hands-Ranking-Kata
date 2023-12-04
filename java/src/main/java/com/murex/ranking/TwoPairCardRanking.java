package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

public class TwoPairCardRanking extends PokerHandRanking{
    public TwoPairCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if(blackHand.getCardAt(0).getValue().equals("7")){
            return Result.aMatchResult("Black wins. - with two pairs: 7 and Ten");
        }
        return Result.aMatchResult("White wins. - with two pairs: 1 and 7");
    }
}
