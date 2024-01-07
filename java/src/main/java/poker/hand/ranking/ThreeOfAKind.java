package poker.hand.ranking;

import poker.hand.Card;
import poker.hand.CardNumber;
import poker.hand.Hand;
import poker.hand.Result;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static poker.hand.ResultHelper.aNoWinner;
import static poker.hand.ResultHelper.aThreeOfAKindWinningResult;

public class ThreeOfAKind extends RankingCategory {
    private final Optional<CardNumber> blackThreeOfAKindCards;
    private final Optional<CardNumber> whiteThreeOfAKindCards;
    private Result result = aNoWinner();

    public ThreeOfAKind(Hand blackHand, Hand whiteHand) {
        super(blackHand, whiteHand);
        this.blackThreeOfAKindCards = extractThreeOfAKind(blackHand);
        this.whiteThreeOfAKindCards = extractThreeOfAKind(whiteHand);
    }

    @Override
    public String getResult() {
        return result.getMessage();
    }

    @Override
    public Result evaluate() {
        isMatch();
        return result;
    }

    @Override
    public boolean isMatch() {
        if (noHandHasThreeOfAKindCards()) {
            return false;
        }
        if (bothHaveThreeOfAKindCards()) {
            result = getHigherHand();
            return true;
        }
        
        result = blackThreeOfAKindCards.isPresent() ?
                aThreeOfAKindWinningResult(blackHand, blackThreeOfAKindCards.get(), false) :
                aThreeOfAKindWinningResult(whiteHand, whiteThreeOfAKindCards.get(), false);
        return true;
    }

    private Result getHigherHand() {
        int comparison = blackThreeOfAKindCards.get().compareTo(whiteThreeOfAKindCards.get());
        if (comparison == 0) {
            return super.evaluate();
        }

        return comparison > 0 ?
                aThreeOfAKindWinningResult(blackHand, blackThreeOfAKindCards.get(), true) :
                aThreeOfAKindWinningResult(whiteHand, whiteThreeOfAKindCards.get(), true);
    }

    private boolean noHandHasThreeOfAKindCards() {
        return blackThreeOfAKindCards.isEmpty() && whiteThreeOfAKindCards.isEmpty();
    }

    private boolean bothHaveThreeOfAKindCards() {
        return blackThreeOfAKindCards.isPresent() && whiteThreeOfAKindCards.isPresent();
    }

    private Optional<CardNumber> extractThreeOfAKind(Hand hand) {
        Map<CardNumber, Long> twoPairsMap = Arrays.stream(hand.cards()).collect(groupingBy(Card::getCardNumber, counting()));
        return twoPairsMap.keySet().stream().filter(x -> twoPairsMap.get(x) == 3).findFirst();
    }
}
