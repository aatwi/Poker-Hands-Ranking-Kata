package poker.hand.ranking;

import poker.hand.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


public class FourOfAKind extends RankingCategory {

    private final Optional<CardNumber> blackFourOfKindCard;
    private final Optional<CardNumber> whiteFourOfKindCard;

    public FourOfAKind(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        this.blackFourOfKindCard = extractFourOfAKindCard(blackHand);
        this.whiteFourOfKindCard = extractFourOfAKindCard(whiteHand);
    }

    private static Result buildMatchingResultWithHigherHand(Hand hand) {
        return ResultHelper.aFourOfAKindWinningResult(hand, true);
    }

    private static Result buildMatchingResult(Hand hand) {
        return ResultHelper.aFourOfAKindWinningResult(hand, false);
    }

    @Override
    public Result evaluate() {
        if (noHandHasFourOfAKind()) {
            return super.evaluate();
        }

        if (bothHaveFourOfAKind()) {
            int comparison = blackFourOfKindCard.get().compareTo(whiteFourOfKindCard.get());
            Hand winningHand = comparison > 0 ? blackHand : whiteHand;
            return buildMatchingResultWithHigherHand(winningHand);
        }

        return blackFourOfKindCard.isPresent() ?
                buildMatchingResult(blackHand) :
                buildMatchingResult(whiteHand);
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
