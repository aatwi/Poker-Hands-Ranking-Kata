package com.murex;

import static com.murex.ResultBuilder.aResultBuilder;

public class ResultHelper {
    public static final String HIGH_CARD = "HIGH CARD";
    public static final String PAIR = "PAIR";
    public static final String TWO_PAIRS = "TWO PAIRS";
    public static final String THREE_OF_A_KIND = "THREE OF A KIND";
    public static final String STRAIGHT = "STRAIGHT";
    public static final String FLUSH = "FLUSH";
    public static final String FULL_HOUSE = "FULL HOUSE";
    public static final String FOUR_OF_A_KIND = "FOUR OF A KIND";
    public static final String STRAIGHT_FLUSH = "STRAIGHT FLUSH";
    public static final String ROYAL_FLUSH = "ROYAL FLUSH";

    public static Result aPairWinningResult(Hand hand, CardNumber pairCard, boolean withHighHand) {
        return aResultBuilder()
                .withWinner(hand)
                .withCard(pairCard)
                .withRankOrder(PAIR)
                .withHigherCards(withHighHand)
                .build();
    }

    public static Result aNoMatchResult() {
        return new NoWinner();
    }

    public static Result aHighCardWinningResult(Hand hand, CardNumber cardNumber) {
        return aResultBuilder()
                .withWinner(hand)
                .withCard(cardNumber)
                .withRankOrder(HIGH_CARD)
                .build();
    }

    public static Result aTwoPairWinningResult(Hand hand, CardNumber firstPairCard, CardNumber secondPairCard, boolean withHighHand) {
        return aResultBuilder()
                .withWinner(hand)
                .withPairs(firstPairCard, secondPairCard)
                .withRankOrder(TWO_PAIRS)
                .withHigherCards(withHighHand)
                .build();
    }

    public static Result aThreeOfAKindWinningResult(Hand hand, CardNumber card, boolean withHighHand) {
        return aResultBuilder()
                .withWinner(hand)
                .withCard(card)
                .withRankOrder(THREE_OF_A_KIND)
                .withHigherCards(withHighHand)
                .build();
    }

    public static Result aStraightWinningResult(Hand hand, boolean withHighHand) {
        return aResultBuilder()
                .withWinner(hand)
                .withRankOrder(STRAIGHT)
                .withHigherCards(withHighHand)
                .build();
    }

    public static Result aFlushWinningResult(Hand hand, boolean withHighHand) {
        return aResultBuilder()
                .withWinner(hand)
                .withRankOrder(FLUSH)
                .withHigherCards(withHighHand)
                .build();
    }

    public static Result aFullHouseWinningResult(Hand hand, boolean withHighHand) {
        return aResultBuilder()
                .withWinner(hand)
                .withRankOrder(FULL_HOUSE)
                .withHigherCards(withHighHand)
                .build();
    }

    public static Result aFourOfAKindWinningResult(Hand hand, boolean withHighHand) {
        return aResultBuilder()
                .withWinner(hand)
                .withRankOrder(FOUR_OF_A_KIND)
                .withHigherCards(withHighHand)
                .build();
    }

    public static Result aStraightFlushWinningResult(Hand hand, boolean withHighHand) {
        return aResultBuilder()
                .withWinner(hand)
                .withRankOrder(STRAIGHT_FLUSH)
                .withHigherCards(withHighHand)
                .build();
    }

    public static Result aRoyalFlushWinningResult(Hand hand) {
        return aResultBuilder()
                .withWinner(hand)
                .withRankOrder(ROYAL_FLUSH)
                .build();
    }

    public static Result aTieResult() {
        return new Tie();
    }
}
