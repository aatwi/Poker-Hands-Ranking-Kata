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

    private static Result buildMatchingResultWithHigherHand(Hand blackHand) {
        return Result.aMatchResult(blackHand.getName() + " wins. - with full house and higher hand");
    }

    @Override
    public Result getMatchingResult() {
        if (bothHaveFullHouse()) {
            return buildMatchingResultWithHigherHand(getHigherHand());
        }

        if (blackFullHouseHand.hasFullHouse()) {
            return buildMatchingResult(blackFullHouseHand);
        }

        if (whiteFullHouseHand.hasFullHouse()) {
            return buildMatchingResult(whiteFullHouseHand);
        }
        return super.getMatchingResult();
    }

    private static Result buildMatchingResult(FullHouseHand fullHouseHand) {
        return Result.aMatchResult(fullHouseHand.getHand().getName() + " wins. - with full house");
    }

    private boolean bothHaveFullHouse() {
        return blackFullHouseHand.hasFullHouse() && whiteFullHouseHand.hasFullHouse();
    }

    private Hand getHigherHand() {
        Card blackCard = blackFullHouseHand.getTrioCards().get();
        Card whiteCard = whiteFullHouseHand.getTrioCards().get();

        int comparison = blackCard.compareTo(whiteCard);
        return comparison > 0 ? blackHand : whiteHand;
    }
}
