package poker.hand.ranking;

import poker.hand.Card;
import poker.hand.CardNumber;
import poker.hand.Hand;
import poker.hand.result.Result;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static poker.hand.result.ResultHelper.aFourOfAKindWinningResult;
import static poker.hand.result.ResultHelper.aNoWinner;


public final class FourOfAKind extends RankingCategory {

    private final Optional<CardNumber> blackFourOfKindCard;
    private final Optional<CardNumber> whiteFourOfKindCard;

    public FourOfAKind(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        this.blackFourOfKindCard = extractFourOfAKindCard(blackHand);
        this.whiteFourOfKindCard = extractFourOfAKindCard(whiteHand);
    }

    private static Result buildMatchingResultWithHigherHand(Hand hand) {
        return aFourOfAKindWinningResult(hand, true);
    }

    private static Result buildMatchingResult(Hand hand) {
        return aFourOfAKindWinningResult(hand, false);
    }

    @Override
    public Result evaluate() {
        if (noHandHasFourOfAKind()) {
            return aNoWinner();
        }

        if (bothHaveFourOfAKind()) {
            return evaluateHigherHand();
        }

        return blackFourOfKindCard.isPresent() ?
                buildMatchingResult(blackHand) :
                buildMatchingResult(whiteHand);
    }

    private Result evaluateHigherHand() {
        int comparison = blackFourOfKindCard.get().compareTo(whiteFourOfKindCard.get());
        return comparison > 0 ?
                buildMatchingResultWithHigherHand(blackHand) :
                buildMatchingResultWithHigherHand(whiteHand);
    }

    private boolean bothHaveFourOfAKind() {
        return blackFourOfKindCard.isPresent() && whiteFourOfKindCard.isPresent();
    }

    private boolean noHandHasFourOfAKind() {
        return blackFourOfKindCard.isEmpty() && whiteFourOfKindCard.isEmpty();
    }

    private Optional<CardNumber> extractFourOfAKindCard(Hand hand) {
        Map<CardNumber, Long> cardGroupsMap = Arrays.stream(hand.cards()).collect(groupingBy(Card::getCardNumber, counting()));
        return cardGroupsMap.keySet().stream().filter(card -> cardGroupsMap.get(card) == 4).findAny();
    }
}
