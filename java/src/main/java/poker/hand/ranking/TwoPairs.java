package poker.hand.ranking;

import poker.hand.Card;
import poker.hand.CardNumber;
import poker.hand.Hand;
import poker.hand.Result;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static poker.hand.ResultHelper.aNoWinner;
import static poker.hand.ResultHelper.aTwoPairWinningResult;

public class TwoPairs extends RankingCategory {

    private final List<CardNumber> blackPairOfCards;
    private final List<CardNumber> whitePairOfCards;
    private Result result = aNoWinner();

    public TwoPairs(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        blackPairOfCards = extractPairs(blackHand);
        whitePairOfCards = extractPairs(whiteHand);
    }

    @Override
    public String getResult() {
        return result.getMessage();
    }

    @Override
    public boolean isMatch() {
        if (bothHandsHaveTwoPairs()) {
            return getHigherHand();
        }

        if (noHandHasTwoPairs()) {
            return false;
        }

        result = blackPairOfCards.size() == 2 ?
                aTwoPairWinningResult(blackHand, blackPairOfCards.get(0), blackPairOfCards.get(1), false) :
                aTwoPairWinningResult(whiteHand, whitePairOfCards.get(0), whitePairOfCards.get(1), false);
        return true;
    }

    @Override
    public Result evaluate() {
        isMatch();
        return result;
    }

    private boolean getHigherHand() {
        int comparison = compareForHigherHands();
        if (comparison == 0) {
            return false;
        }

        result = comparison > 0 ?
                aTwoPairWinningResult(blackHand, blackPairOfCards.get(0), blackPairOfCards.get(1), true) :
                aTwoPairWinningResult(whiteHand, whitePairOfCards.get(0), whitePairOfCards.get(1), true);
        return true;
    }

    private boolean bothHandsHaveTwoPairs() {
        return blackPairOfCards.size() == 2 && whitePairOfCards.size() == 2;
    }

    private boolean noHandHasTwoPairs() {
        return blackPairOfCards.size() != 2 && whitePairOfCards.size() != 2;
    }

    private int compareForHigherHands() {
        int comparison = blackPairOfCards.get(1).compareTo(whitePairOfCards.get(1));
        return comparison != 0 ? comparison : blackPairOfCards.get(0).compareTo(whitePairOfCards.get(0));
    }

    private List<CardNumber> extractPairs(Hand hand) {
        Map<CardNumber, Long> twoPairsMap = Arrays.stream(hand.cards()).collect(groupingBy(Card::getCardNumber, counting()));
        return twoPairsMap.keySet().stream().filter(x -> twoPairsMap.get(x) == 2).sorted().toList();
    }
}
