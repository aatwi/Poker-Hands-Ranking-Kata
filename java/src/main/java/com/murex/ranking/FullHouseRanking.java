package com.murex.ranking;

import com.murex.Card;
import com.murex.CardNumber;
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

    private static Result buildMatchingResult(FullHouseHand fullHouseHand) {
        return Result.aMatchResult(fullHouseHand.getHand().getName() + " wins. - with full house");
    }

    @Override
    public Result getMatchingResult() {
        if (!blackFullHouseHand.hasFullHouse() && !whiteFullHouseHand.hasFullHouse()) {
            return super.getMatchingResult();
        }

        if (bothHaveFullHouse()) {
            return buildMatchingResultWithHigherHand(getHigherHand());
        }

        return blackFullHouseHand.hasFullHouse() ? buildMatchingResult(blackFullHouseHand) : buildMatchingResult(whiteFullHouseHand);
    }

    private boolean bothHaveFullHouse() {
        return blackFullHouseHand.hasFullHouse() && whiteFullHouseHand.hasFullHouse();
    }

    private Hand getHigherHand() {
        CardNumber blackCard = blackFullHouseHand.getTrioCards().get();
        CardNumber whiteCard = whiteFullHouseHand.getTrioCards().get();

        int comparison = blackCard.compareTo(whiteCard);
        return comparison > 0 ? blackHand : whiteHand;
    }
}
