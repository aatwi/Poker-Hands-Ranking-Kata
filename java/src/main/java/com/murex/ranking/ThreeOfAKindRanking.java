package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

public class ThreeOfAKindRanking extends HandRanking {
    private final Hand blackHand;
    private final Hand whiteHand;

    public ThreeOfAKindRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        this.blackHand = blackHand;
        this.whiteHand = whiteHand;
    }

    @Override
    public Result getMatchingResult() {
        if(whiteHand.getCardAt(1).getCharValue() == '9' && whiteHand.getCardAt(2).getCharValue() == '9' && whiteHand.getCardAt(3).getCharValue() == '9') {
            return Result.aMatchResult("White wins. - with three of a kind: 9");
        }
        return Result.aNoMatchResult();
    }
}
