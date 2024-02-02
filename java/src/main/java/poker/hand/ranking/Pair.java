package poker.hand.ranking;

import poker.hand.*;
import poker.hand.result.Result;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static poker.hand.result.ResultHelper.aNoWinner;
import static poker.hand.result.ResultHelper.aPairWinningResult;

public final class Pair extends RankingCategory {
    private final Optional<CardNumber> blackPairCards;
    private final Optional<CardNumber> whitePairCards;

    public Pair(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        this.blackPairCards = extractCardOfPairs(blackHand);
        this.whitePairCards = extractCardOfPairs(whiteHand);
    }

    @Override
    public Result evaluate() {
        if (noHandHasPair()) {
            return aNoWinner();
        }

        if (bothHandsHavePair()) {
            return evaluateHigherHand();
        }

        return blackPairCards.isPresent() ?
                aPairWinningResult(blackHand, blackPairCards.get(), false) :
                aPairWinningResult(whiteHand, whitePairCards.get(), false);
    }

    private Result evaluateHigherHand() {
        int comparison = blackPairCards.get().compareTo(whitePairCards.get());
        if (comparison == 0) {
            return aNoWinner();
        }

        return comparison > 0 ?
                aPairWinningResult(blackHand, blackPairCards.get(), true) :
                aPairWinningResult(whiteHand, whitePairCards.get(), true);
    }

    private boolean noHandHasPair() {
        return blackPairCards.isEmpty() && whitePairCards.isEmpty();
    }

    private boolean bothHandsHavePair() {
        return blackPairCards.isPresent() && whitePairCards.isPresent();
    }

    private Optional<CardNumber> extractCardOfPairs(Hand hand) {
        Map<CardNumber, Long> cardsPairMap = Arrays.stream(hand.cards()).collect(groupingBy(Card::getCardNumber, counting()));
        return cardsPairMap.keySet().stream().filter(card -> cardsPairMap.get(card) == 2).findAny();
    }
}
