package com.murex;

import java.util.Arrays;
import java.util.List;

public class TieRank extends PokerHandRank {
    public TieRank(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public String verify() {
        List<Card> blackCards = Arrays.stream(this.blackHand.getCards()).toList();
        List<Card> whiteCards = Arrays.stream(this.blackHand.getCards()).toList();
        for (int i = 0; i < blackCards.size(); i++) {
            if(blackCards.get(i).compareTo(whiteCards.get(i)) != 0) {
                return null;
            }
        }
        return "Tie";
    }
}
