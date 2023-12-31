package com.murex;

import java.util.Objects;

public class Result {

    private final boolean isMatch;
    private final String message;

    public Result(boolean isMatch, String message) {
        this.isMatch = isMatch;
        this.message = message;
    }

    public static Result aPairWinningResult(Hand hand, CardNumber pairCard) {
        return aMatchResult(hand.getName() + " wins. - with Pair cards: " + pairCard.toString());
    }

    public boolean isMatch() {
        return isMatch;
    }

    public String getMessage() {
        return message;
    }

    public static Result aMatchResult(String message) {
        return new Result(true, message);
    }
    public static Result aNoMatchResult() {
        return new Result(false, "");
    }

    public static Result aHighCardWinningResult(Hand hand, CardNumber cardNumber) {
        String message = hand.getName() + " wins. - with high card: " + cardNumber;
        return new WinningResult(message);
    }

    public static Result aTwoPairWinningResult(Hand hand, CardNumber firstPairCard, CardNumber secondPairCard) {
        String message = hand.getName() + " wins. - with two pairs: " + firstPairCard.toString() + " and " + secondPairCard.toString();
        return new WinningResult(message);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return isMatch == result.isMatch && Objects.equals(message, result.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isMatch, message);
    }

    @Override
    public String toString() {
        return "Result{" +
                "isMatch=" + isMatch +
                ", message='" + message + '\'' +
                '}';
    }
}
class WinningResult extends Result {

    private final  boolean isMatch;
    private final String message;

    WinningResult(String message) {
        super(true, message);
        this.isMatch = true;
        this.message = message;
    }


    @Override
    public String toString() {
        return "WinningResult{" +
                "isMatch=" + isMatch +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WinningResult that = (WinningResult) o;
        return isMatch == that.isMatch && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isMatch, message);
    }
}
