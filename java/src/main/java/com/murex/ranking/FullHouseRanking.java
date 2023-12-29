package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.FullHouseHand;

import static java.util.stream.Collectors.groupingBy;

public class FullHouseRanking extends HandRanking{

    private final FullHouseHand blackFullHouseHand;
    private final FullHouseHand whiteFullHouseHand;

    public FullHouseRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        blackFullHouseHand = new FullHouseHand(blackHand);
        whiteFullHouseHand = new FullHouseHand(whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if(blackFullHouseHand.hasFullHouse()) {
            return Result.aMatchResult("Black wins. - with full house");
        }

        if(whiteFullHouseHand.hasFullHouse()) {
            return Result.aMatchResult("White wins. - with full house");
        }
        return super.getMatchingResult();
    }
}
