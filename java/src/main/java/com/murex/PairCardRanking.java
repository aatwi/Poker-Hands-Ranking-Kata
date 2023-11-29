package com.murex;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class PairCardRanking extends PokerHandRanking {
    public PairCardRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    private static String buildPairAndHighHandMessage(Hand hand, String cardValue) {
        return hand.getName() + " wins. - with Pair cards and higher rank: " + cardValue + " and " + hand.getCardAt(4).getValue();
    }

    private static String buildPairCardsMessage(Hand hand, String cardValue) {
        return hand.getName() + " wins. - with Pair cards: " + cardValue;
    }

    @Override
    public Optional<String> verify() {
        PairHand blackPairHand = new PairHand(blackHand);
        PairHand whitePairHand = new PairHand(whiteHand);

        if (!blackPairHand.hasPair() && !whitePairHand.hasPair()) {
            return Optional.empty();
        }

        String message;
        if (blackPairHand.hasPair() && whitePairHand.hasPair()) {
            int comparison = blackPairHand.getPairValue().compareTo(whitePairHand.getPairValue());
            String winnerCardValue = comparison > 0 ? blackPairHand.getPairValue() : whitePairHand.getPairValue();

            if (comparison == 0) {
                HighCardRanking highCardRank = new HighCardRanking(blackHand, whiteHand);
                Optional<Hand> higherHand = highCardRank.getHigherHand();
                if (higherHand.isEmpty()) {
                    return Optional.empty();
                }
                message = buildPairAndHighHandMessage(higherHand.get(), winnerCardValue);
            } else {
                Hand winningHad = comparison > 0 ? blackHand : whiteHand;
                message = buildPairCardsMessage(winningHad, winnerCardValue);
            }
        } else {
            Hand winningHad = blackPairHand.hasPair() ? blackHand : whiteHand;
            String winningPairCard = blackPairHand.hasPair() ? blackPairHand.getPairValue() : whitePairHand.getPairValue();
            message = buildPairCardsMessage(winningHad, winningPairCard);
        }
        return Optional.of(message);
    }
}
