package poker.hand.ranking;

import poker.hand.Card;
import poker.hand.CardNumber;
import poker.hand.Hand;
import poker.hand.result.Result;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static poker.hand.result.ResultHelper.aFullHouseWinningResult;

public final class FullHouse extends RankingCategory {

    private final Map<CardNumber, Long> firstCardsCountMap;
    private final Map<CardNumber, Long> secondCardsCountMap;

    public FullHouse(Hand firstHand, Hand secondHand) {
        super(firstHand, secondHand);
        this.firstCardsCountMap = extractPairsAndTrioCards(firstHand);
        this.secondCardsCountMap = extractPairsAndTrioCards(secondHand);
    }

    @Override
    public Result evaluate() {
        if (noHandHasFullHouse()) {
            return super.evaluate();
        }

        if (bothHaveFullHouse()) {
            return evaluateHigherHand();
        }

        return hasFullHouse(firstCardsCountMap) ?
                aFullHouseWinningResult(firstHand, false) :
                aFullHouseWinningResult(secondHand, false);
    }

    private Result evaluateHigherHand() {
        CardNumber blackCard = firstCardsCountMap.keySet().stream().filter(card -> firstCardsCountMap.get(card) == 3).findAny().get();
        CardNumber whiteCard = secondCardsCountMap.keySet().stream().filter(card -> secondCardsCountMap.get(card) == 3).findAny().get();

        int comparison = blackCard.compareTo(whiteCard);
        return comparison > 0 ?
                aFullHouseWinningResult(firstHand, true) :
                aFullHouseWinningResult(secondHand, true);
    }

    private boolean noHandHasFullHouse() {
        return !hasFullHouse(firstCardsCountMap) && !hasFullHouse(secondCardsCountMap);
    }

    private boolean hasFullHouse(Map<CardNumber, Long> cardsCountMap) {
        return cardsCountMap.keySet().stream().anyMatch(card -> cardsCountMap.get(card) == 2) &&
                cardsCountMap.keySet().stream().anyMatch(card -> cardsCountMap.get(card) == 3);
    }

    private boolean bothHaveFullHouse() {
        return hasFullHouse(firstCardsCountMap) && hasFullHouse(secondCardsCountMap);
    }

    private Map<CardNumber, Long> extractPairsAndTrioCards(Hand hand) {
        return Arrays.stream(hand.cards()).collect(groupingBy(Card::getCardNumber, counting()));
    }

}
