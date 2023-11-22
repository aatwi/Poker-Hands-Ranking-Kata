package com.murex;

public abstract class PokerHandRank {
    protected final Hand blackHand;
    protected final Hand whiteHand;

    public PokerHandRank(Hand blackHand, Hand whiteHand) {
        this.blackHand = blackHand;
        this.whiteHand = whiteHand;
    }

    public String verify() {
        int comparison = blackHand.getCards()[4].compareTo(whiteHand.getCards()[4]);
        if (comparison > 0) {
            return buildMessage(blackHand);
        } else if (comparison < 0) {
            return buildMessage(whiteHand);
        }
        return null;
    }

    private String buildMessage(Hand hand) {
        return hand.getName() + " wins. - with high card: " + hand.getCards()[4].getValue();
    }
}
