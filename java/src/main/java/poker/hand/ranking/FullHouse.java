package poker.hand.ranking;

import poker.hand.*;
import poker.hand.result.Result;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static poker.hand.result.ResultHelper.aFullHouseWinningResult;
import static poker.hand.result.ResultHelper.aNoWinner;

public class FullHouse extends RankingCategory {

    private final Map<CardNumber, Long> blackCardsCountMap;
    private final Map<CardNumber, Long> whiteCardsCountMap;

    public FullHouse(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        this.blackCardsCountMap = extractPairsAndTrioCards(blackHand);
        this.whiteCardsCountMap = extractPairsAndTrioCards(whiteHand);
    }

    @Override
    public Result evaluate() {
        if (noHandHasFullHouse()) {
            return aNoWinner();
        }

        if (bothHaveFullHouse()) {
            return aFullHouseWinningResult(getHigherHand(), true);
        }

        return hasFullHouse(blackCardsCountMap) ?
                aFullHouseWinningResult(blackHand, false) :
                aFullHouseWinningResult(whiteHand, false);
    }

    private boolean noHandHasFullHouse() {
        return !hasFullHouse(blackCardsCountMap) && !hasFullHouse(whiteCardsCountMap);
    }

    private boolean hasFullHouse(Map<CardNumber, Long> cardsCountMap) {
        return cardsCountMap.keySet().stream().anyMatch(card -> cardsCountMap.get(card) == 2) &&
                cardsCountMap.keySet().stream().anyMatch(card -> cardsCountMap.get(card) == 3);
    }

    private boolean bothHaveFullHouse() {
        return hasFullHouse(blackCardsCountMap) && hasFullHouse(whiteCardsCountMap);
    }

    private Hand getHigherHand() {
        CardNumber blackCard = blackCardsCountMap.keySet().stream().filter(card -> blackCardsCountMap.get(card) == 3).findAny().get();
        CardNumber whiteCard = whiteCardsCountMap.keySet().stream().filter(card -> whiteCardsCountMap.get(card) == 3).findAny().get();

        int comparison = blackCard.compareTo(whiteCard);
        return comparison > 0 ? blackHand : whiteHand;
    }

    private Map<CardNumber, Long> extractPairsAndTrioCards(Hand hand) {
        return Arrays.stream(hand.cards()).collect(groupingBy(Card::getCardNumber, counting()));
    }

}
