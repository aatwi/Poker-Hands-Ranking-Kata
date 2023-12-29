package com.murex.ranking;

import com.murex.Card;
import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.FullHouseHand;

public class FullHouseRanking extends HandRanking {

    private final FullHouseHand blackFullHouseHand;
    private final FullHouseHand whiteFullHouseHand;

    public FullHouseRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        blackFullHouseHand = new FullHouseHand(blackHand);
        whiteFullHouseHand = new FullHouseHand(whiteHand);
    }

    private static Result buildMatchingResult(Hand blackHand) {
        return Result.aMatchResult(blackHand.getName() + " wins. - with full house and higher hand");
    }

    @Override
    public Result getMatchingResult() {
        if (blackFullHouseHand.hasFullHouse() && whiteFullHouseHand.hasFullHouse()) {
            return buildMatchingResult(getHigherHand());
        }

        if (blackFullHouseHand.hasFullHouse()) {
            return Result.aMatchResult("Black wins. - with full house");
        }

        if (whiteFullHouseHand.hasFullHouse()) {
            return Result.aMatchResult("White wins. - with full house");
        }
        return super.getMatchingResult();
    }

    private Hand getHigherHand() {
        Card blackCard = blackFullHouseHand.getTrioCards().get();
        Card whiteCard = whiteFullHouseHand.getTrioCards().get();

        int comparison = blackCard.compareTo(whiteCard);
        return comparison > 0 ? blackHand : whiteHand;
    }
}
