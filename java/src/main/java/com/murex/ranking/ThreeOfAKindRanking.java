package com.murex.ranking;

import com.murex.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static com.murex.ResultHelper.aThreeOfAKindWinningResult;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ThreeOfAKindRanking extends OrderRanking {
    private final Optional<CardNumber> blackThreeOfAKindCards;
    private final Optional<CardNumber> whiteThreeOfAKindCards;

    public ThreeOfAKindRanking(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        this.blackThreeOfAKindCards = extractThreeOfAKind(blackHand);
        this.whiteThreeOfAKindCards = extractThreeOfAKind(whiteHand);
    }

    @Override
    public Result getMatchingResult() {
        if (bothHaveThreeOfAKindCards()) {
            return getHigherHand();
        }

        if (noHandHasThreeOfAKindCards()) {
            return ResultHelper.aNoWinner();
        }

        return blackThreeOfAKindCards.isPresent() ?
                aThreeOfAKindWinningResult(blackHand, blackThreeOfAKindCards.get(), false) :
                aThreeOfAKindWinningResult(whiteHand, whiteThreeOfAKindCards.get(), false);
    }

    private Result getHigherHand() {
        int comparison = blackThreeOfAKindCards.get().compareTo(whiteThreeOfAKindCards.get());
        if (comparison == 0) {
            return ResultHelper.aNoWinner();
        }

        return comparison > 0 ?
                aThreeOfAKindWinningResult(blackHand, blackThreeOfAKindCards.get(), true) :
                aThreeOfAKindWinningResult(whiteHand, whiteThreeOfAKindCards.get(), true);
    }

    private boolean noHandHasThreeOfAKindCards() {
        return blackThreeOfAKindCards.isEmpty() &&  whiteThreeOfAKindCards.isEmpty();
    }

    private boolean bothHaveThreeOfAKindCards() {
        return blackThreeOfAKindCards.isPresent() && whiteThreeOfAKindCards.isPresent();
    }

    private Optional<CardNumber> extractThreeOfAKind(Hand hand) {
        Map<CardNumber, Long> twoPairsMap = Arrays.stream(hand.getCards()).collect(groupingBy(Card::getCardNumber, counting()));
        return twoPairsMap.keySet().stream().filter(x -> twoPairsMap.get(x) == 3).findFirst();
    }
}
