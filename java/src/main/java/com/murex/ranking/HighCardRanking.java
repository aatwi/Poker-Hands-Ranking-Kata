package com.murex.ranking;

import com.murex.Hand;
import com.murex.hands.HighHand;
import com.murex.Result;

import java.util.Optional;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;

public class HighCardRanking extends HandRanking {

    public HighCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        Optional<HighHand> winner = getHigherHand();
        if (winner.isEmpty()) {
            return aNoMatchResult();
        }
        return aMatchResult(buildMessage(winner.get()));
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

    private String buildMessage(HighHand hand) {
        return hand.getHand().getName() + " wins. - with high card: " + hand.getHighCard().getValue();
    }
}
