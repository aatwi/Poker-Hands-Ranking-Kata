package poker.hand.ranking;

import poker.hand.*;
import poker.hand.result.Result;
import poker.hand.result.ResultHelper;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static poker.hand.result.ResultHelper.aPairWinningResult;

public class Pair extends RankingCategory {
    private final Optional<CardNumber> blackPairCards;
    private final Optional<CardNumber> whitePairCards;
    private Result result = ResultHelper.aNoWinner();

    public Pair(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        this.blackPairCards = extractCardOfPairs(blackHand);
        this.whitePairCards = extractCardOfPairs(whiteHand);
    }

    @Override
    public String getResult() {
        return result.getMessage();
    }

    @Override
    public boolean isMatch() {
        if (bothHandsHavePair()) {
            return getHigherPair();
        }

        if (noHandHasPair()) {
            return false;
        }

        result = blackPairCards.isPresent() ?
                aPairWinningResult(blackHand, blackPairCards.get(), false) :
                aPairWinningResult(whiteHand, whitePairCards.get(), false);
        return true;
    }

    @Override
    public Result evaluate() {
        isMatch();
        return result;
    }

    private boolean getHigherPair() {
        int comparison = blackPairCards.get().compareTo(whitePairCards.get());
        if (comparison == 0) {
            return false;
        }

        result = comparison > 0 ?
                aPairWinningResult(blackHand, blackPairCards.get(), true) :
                aPairWinningResult(whiteHand, whitePairCards.get(), true);
        return true;
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
