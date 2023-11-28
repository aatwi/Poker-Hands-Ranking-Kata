package com.murex;

import java.util.Optional;

public class TieRanking extends PokerHandRanking {
    public TieRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Optional<String> verify() {
        for (int index = 0; index < 5; index++) {
            if (blackHand.getCardAt(index).compareTo(whiteHand.getCardAt(index)) != 0) {
                return Optional.empty();
            }
        }
        return Optional.of("Tie.");
    }

}
