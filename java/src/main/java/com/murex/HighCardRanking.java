package com.murex;

import java.util.Optional;

public class HighCardRanking extends PokerHandRanking {
    public HighCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Optional<String> verify() {
        Optional<Hand> winner = getHigherHand();
        return winner.isEmpty() ? Optional.empty():  Optional.of(buildMessage(winner.get()));
    }

    protected Optional<Hand> getHigherHand() {
        int cardComparison = blackHand.getCardAt(4).compareTo(whiteHand.getCardAt(4));
        if(cardComparison == 0) {
            return Optional.empty();
        }
        return Optional.of(cardComparison > 0 ? blackHand : whiteHand);
    }

    private String buildMessage(Hand hand) {
        return hand.getName() + " wins. - with high card: " + hand.getCardAt(4).getValue();
    }
}
