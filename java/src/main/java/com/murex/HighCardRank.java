package com.murex;

import java.util.Optional;

public class HighCardRank extends PokerHandRank{
    public HighCardRank(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Optional<String> verify() {
        int comparison = blackHand.getCards()[4].compareTo(whiteHand.getCards()[4]);
        if (comparison > 0) {
            return Optional.of(buildMessage(blackHand));
        } else if (comparison < 0) {
            return Optional.of(buildMessage(whiteHand));
        }
        return Optional.empty();
    }

    private String buildMessage(Hand hand) {
        return hand.getName() + " wins. - with high card: " + hand.getCards()[4].getValue();
    }
}
