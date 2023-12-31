package com.murex.ranking;

import com.murex.Hand;
import com.murex.hands.HighHand;
import com.murex.Result;

import java.util.Optional;

import static com.murex.Result.aNoMatchResult;
import static com.murex.Result.aHighCardWinningResult;

public class HighCardRanking extends HandRanking {

    public HighCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        Optional<HighHand> winner = getHigherHand();
        return winner.isEmpty()? aNoMatchResult() : aHighCardWinningResult(winner.get().getHand(), winner.get().getHighCard().getCardNumber());
    }

    protected Optional<HighHand> getHigherHand() {
        for (int index = 4; index >= 0; index--) {
            int cardComparison = blackHand.getCardAt(index).compareTo(whiteHand.getCardAt(index));
            if (cardComparison != 0) {
                return Optional.of(cardComparison > 0 ? new HighHand(blackHand, index) : new HighHand(whiteHand, index));
            }
        }
        return Optional.empty();
    }
}
