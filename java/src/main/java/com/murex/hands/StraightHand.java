package com.murex.hands;

import com.murex.Hand;

public class StraightHand {
    private final Hand hand;

    public StraightHand(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isAStraight() {
        for (int i = 1; i < hand.getCards().length; i++) {
            int previousCard = hand.getCardAt(i - 1).getIntValue();
            int currentCard = hand.getCardAt(i).getIntValue();
            if(currentCard != previousCard + 1){
                return false;
            }
        }
        return true;
    }
}
