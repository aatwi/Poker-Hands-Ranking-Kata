package com.murex;

import java.util.Optional;

public class HighCardRank extends PokerHandRank{
    public HighCardRank(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Optional<String> verify() {
        int comparison = blackHand.getCardAt(4).compareTo(whiteHand.getCardAt(4));
        if (comparison == 0) {
            return Optional.empty();
        }

        Hand winner = comparison > 0 ? blackHand : whiteHand;
        
        return Optional.of(buildMessage(winner));
    }

    private String buildMessage(Hand hand) {
        return hand.getName() + " wins. - with high card: " + hand.getCardAt(4).getValue();
    }
}
