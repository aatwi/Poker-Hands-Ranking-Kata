package com.murex;

public class HighCardRank extends PokerHandRank{
    public HighCardRank(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public String verify() {
        return null;
    }
}
