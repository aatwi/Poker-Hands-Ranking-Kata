package poker.hand.ranking;

import poker.hand.Card;
import poker.hand.CardNumber;
import poker.hand.Hand;
import poker.hand.Result;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static poker.hand.ResultHelper.aNoWinner;
import static poker.hand.ResultHelper.aPairWinningResult;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Pair extends RankingCategory {
    private final Optional<CardNumber> blackPairCards;
    private final Optional<CardNumber> whitePairCards;

    public Pair(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        this.blackPairCards = extractCardOfPairs(blackHand);
        this.whitePairCards = extractCardOfPairs(whiteHand);
    }

    @Override
    public Result evaluate() {
        if (bothHandsHavePair()) {
            return getHigherPair();
        }

        if (noHandHasPair()) {
            return super.evaluate();
        }

        return blackPairCards.isPresent() ?
                aPairWinningResult(blackHand, blackPairCards.get(), false) :
                aPairWinningResult(whiteHand, whitePairCards.get(), false);
    }

    private Result getHigherPair() {
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
