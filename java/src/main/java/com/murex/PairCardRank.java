package com.murex;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class PairCardRank extends PokerHandRank{
    public PairCardRank(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
    }

    @Override
    public Result getResult() {
        String verify = verify();
        if(verify != null) {
            return new Result(true, verify);
        }
        return super.getResult();
    }

    @Override
    public String verify() {
        Optional<Card> blackPairCard = getCardOfPairs(blackHand);
        Optional<Card> whitePairCard = getCardOfPairs(whiteHand);

        if(blackPairCard.isPresent() && whitePairCard.isPresent()) {
            int comparison = blackPairCard.get().compareTo(whitePairCard.get());
            if (comparison > 0) {
                return buildPairCardsMessage(blackHand, blackPairCard.get());
            } else if (comparison < 0) {
                return buildPairCardsMessage(whiteHand, whitePairCard.get());
            }else {
                int highCardComparison = blackHand.getCards()[4].compareTo(whiteHand.getCards()[4]);
                if (highCardComparison > 0) {
                    return blackHand.getName() + " wins. - with Pair cards and higher rank: " + blackPairCard.get().getValue() + " and " + blackHand.getCards()[4].getValue();
                }
                if (highCardComparison < 0) {
                    return whiteHand.getName() + " wins. - with Pair cards and higher rank: " + whitePairCard.get().getValue() + " and " + whiteHand.getCards()[4].getValue();
                }
            }
        }

        if(blackPairCard.isPresent()) {
            return buildPairCardsMessage(blackHand, blackPairCard.get());
        }

        if(whitePairCard.isPresent()) {
            return buildPairCardsMessage(whiteHand, whitePairCard.get());
        }

        return null;
    }

    private static String buildPairCardsMessage(Hand hand, Card card) {
        return hand.getName() + " wins. - with Pair cards: " + card.getValue();
    }

    private Optional<Card> getCardOfPairs(Hand pokerHand) {
        Map<Card, Long> cardsPairMap = Arrays.stream(pokerHand.getCards()).collect(groupingBy(Function.identity(), counting()));
        return cardsPairMap.keySet().stream().filter(card -> cardsPairMap.get(card) == 2).findAny();
    }
}
