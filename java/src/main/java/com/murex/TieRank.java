package com.murex;

public class TieRank extends PokerHandRank {
    public TieRank(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public String verify() {
        return "Tie";
    }
}
