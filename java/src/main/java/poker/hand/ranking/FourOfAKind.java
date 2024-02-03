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

public final class FourOfAKind extends RankingCategory {

    private final Optional<CardNumber> firstFourOfKindCard;
    private final Optional<CardNumber> secondFourOfKindCard;

    private FourOfAKind(Hand firstHand, Hand secondHand) {
        super(firstHand, secondHand);
        this.firstFourOfKindCard = extractFourOfAKindCard(firstHand);
        this.secondFourOfKindCard = extractFourOfAKindCard(secondHand);
    }

    private static Result buildMatchingResultWithHigherHand(Hand hand) {
        return aFourOfAKindWinningResult(hand, true);
    }

    private static Result buildMatchingResult(Hand hand) {
        return aFourOfAKindWinningResult(hand, false);
    }

    public static FourOfAKind aFourOfAKind(Hand firstHand, Hand secondHand) {
        return new FourOfAKind(firstHand, secondHand);
    }

    @Override
    public Result evaluate() {
        if (noHandHasFourOfAKind()) {
            return super.evaluate();
        }

        if (bothHaveFourOfAKind()) {
            return evaluateHigherHand();
        }

        return firstFourOfKindCard.isPresent() ?
                buildMatchingResult(firstHand) :
                buildMatchingResult(secondHand);
    }

    private Result evaluateHigherHand() {
        int comparison = firstFourOfKindCard.get().compareTo(secondFourOfKindCard.get());
        return comparison > 0 ?
                buildMatchingResultWithHigherHand(firstHand) :
                buildMatchingResultWithHigherHand(secondHand);
    }

    private boolean bothHaveFourOfAKind() {
        return firstFourOfKindCard.isPresent() && secondFourOfKindCard.isPresent();
    }

    private boolean noHandHasFourOfAKind() {
        return firstFourOfKindCard.isEmpty() && secondFourOfKindCard.isEmpty();
    }

    private Optional<CardNumber> extractFourOfAKindCard(Hand hand) {
        Map<CardNumber, Long> cardGroupsMap = Arrays.stream(hand.cards()).collect(groupingBy(Card::getCardNumber, counting()));
        return cardGroupsMap.keySet().stream().filter(card -> cardGroupsMap.get(card) == 4).findAny();
    }
}
