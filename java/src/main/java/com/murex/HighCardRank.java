package com.murex;

public class HighCardRank extends PokerHandRank{
    public HighCardRank(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
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
