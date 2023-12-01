package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;

import java.util.Optional;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;

public class HighCardRanking extends PokerHandRanking {

    public HighCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        Optional<Hand> winner = getHigherHand();
        if (winner.isEmpty()) {
            return aNoMatchResult();
        }
        return aMatchResult(buildMessage(winner.get()));
    }

    protected Optional<Hand> getHigherHand() {
        int cardComparison = blackHand.getCardAt(4).compareTo(whiteHand.getCardAt(4));
        if (cardComparison == 0) {
            return Optional.empty();
        }
        return Optional.of(cardComparison > 0 ? blackHand : whiteHand);
    }

    private String buildMessage(Hand hand) {
        return hand.getName() + " wins. - with high card: " + hand.getCardAt(4).getValue();
    }
}
