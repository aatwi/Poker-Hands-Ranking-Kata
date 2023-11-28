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

    @Override
    public Optional<String> verify() {
        Optional<Card> blackPairCard = getCardOfPairs(blackHand);
        Optional<Card> whitePairCard = getCardOfPairs(whiteHand);

        if (blackPairCard.isEmpty() && whitePairCard.isEmpty()) {
            return Optional.empty();
        }

        if(blackPairCard.isPresent() && whitePairCard.isPresent()) {
            int comparison = blackPairCard.get().compareTo(whitePairCard.get());

            Card winnerPairCard = comparison > 0 ? blackPairCard.get() : whitePairCard.get();

            if (comparison > 0) {
                return Optional.of(buildPairCardsMessage(blackHand, winnerPairCard));
            } else if (comparison < 0) {
                return Optional.of(buildPairCardsMessage(whiteHand, winnerPairCard));
            }
            if(comparison == 0) {
                HighCardRanking highCardRank = new HighCardRanking(blackHand, whiteHand);
                Optional<Hand> higherHand = highCardRank.getHigherHand();
                if (higherHand.isEmpty()) {
                    return Optional.empty();
                }
//                int highCardComparison = blackHand.getCardAt(4).compareTo(whiteHand.getCardAt(4));
//                if (highCardComparison == 0) {
//                    return Optional.empty();
//                }
                return Optional.of(buildPairAndHighHandMessage(higherHand.get(), winnerPairCard));
            }
        }

        if(blackPairCard.isPresent()) {
            return Optional.of(buildPairCardsMessage(blackHand, blackPairCard.get()));
        }else {
            return Optional.of(buildPairCardsMessage(whiteHand, whitePairCard.get()));
        }

    }

    private String buildPairAndHighHandMessage(Hand hand, Card card) {
        return hand.getName() + " wins. - with Pair cards and higher rank: " + card.getValue() + " and " + hand.getCardAt(4).getValue();
    }

    private static String buildPairCardsMessage(Hand hand, Card card) {
        return hand.getName() + " wins. - with Pair cards: " + card.getValue();
    }

    private Optional<Card> getCardOfPairs(Hand pokerHand) {
        Map<Card, Long> cardsPairMap = Arrays.stream(pokerHand.getCards()).collect(groupingBy(Function.identity(), counting()));
        return cardsPairMap.keySet().stream().filter(card -> cardsPairMap.get(card) == 2).findAny();
    }
}
