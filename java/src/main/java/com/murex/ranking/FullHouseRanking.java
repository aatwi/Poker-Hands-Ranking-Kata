package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.FullHouseHand;
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
        if(blackFullHouseHand.hasFullHouse() && whiteFullHouseHand.hasFullHouse()) {
            Card blackCard = blackFullHouseHand.getTrioCards().get();
            Card whiteCard = whiteFullHouseHand.getTrioCards().get();

            int comparison = blackCard.compareTo(whiteCard);
            return comparison > 0 ? buildMatchingResult(blackHand) : buildMatchingResult(whiteHand);
        }

        if(blackFullHouseHand.hasFullHouse()) {
            return Result.aMatchResult("Black wins. - with full house");
        }

        if(whiteFullHouseHand.hasFullHouse()) {
            return Result.aMatchResult("White wins. - with full house");
        }
        return super.getMatchingResult();
    }

    private static Result buildMatchingResult(Hand blackHand) {
        return Result.aMatchResult(blackHand.getName() +" wins. - with full house and higher hand");
    }
}
