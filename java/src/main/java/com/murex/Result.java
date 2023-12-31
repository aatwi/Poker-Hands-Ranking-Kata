package com.murex;

public record Result(boolean isMatch, String message) {

    public static Result aMatchResult(String message) {
        return new Result(true, message);
    }
    public static Result aNoMatchResult() {
        return new Result(false, "");
    }

    public static Result aHighCardWinningResult(Hand hand, Card highCard) {
        String message = hand.getName() + " wins. - with high card: " + highCard.getName();
        return new Result(true, message);
    }

    public static Result aTwoPairWinningResult(Hand hand, CardNumber firstPairCard, CardNumber secondPairCard) {
       return new Result(true, hand.getName() + " wins. - with two pairs: " + firstPairCard.toString() + " and " + secondPairCard.toString());
    }

    public static Result aThreeOfAKindWinningResult(Hand hand, CardNumber card) {
        return new Result(true, hand.getName() + " wins. - with three of a kind: " + card.toString());
    }

    public static Result aStraightWinningResult(Hand hand, boolean withHighHand) {
        String message = hand.getName() + " wins. - with straight cards";
        return withHighHand ? aMatchResult(message + " and higher cards"): aMatchResult(message);
    }

    public static Result aFlushWinningResult(Hand hand, boolean withHighHand) {
        String message = hand.getName() + " wins. - with flush";
        return withHighHand ? aMatchResult(message + " and higher hand"): aMatchResult(message);
    }

    public static Result aFullHouseWinningResult(Hand hand, boolean withHighHand) {
        String message = hand.getName() + " wins. - with full house";
        return withHighHand ? aMatchResult(message + " and higher hand") : aMatchResult(message);
    }

    public static Result aFourOfAKindWinningResult(Hand hand, boolean withHighHand) {
        String message = hand.getName() + " wins. - with four of a kind";
        return withHighHand ? aMatchResult(message + " and higher hand") : Result.aMatchResult(message);
    }

    public static Result aStraightFlushWinningResult(Hand hand, boolean withHighHand) {
        String message = hand.getName() + " wins. - with straight flush";
        return withHighHand ? aMatchResult(message + " and higher hand") : Result.aMatchResult(message);
    }

    public static Result aRoyalFlushWinningResult(Hand hand) {
        return Result.aMatchResult(hand.getName() + " wins. - with royal flush");
    }

    public static Result aTieResult() {
        return Result.aMatchResult("Tie.");
    }
}
