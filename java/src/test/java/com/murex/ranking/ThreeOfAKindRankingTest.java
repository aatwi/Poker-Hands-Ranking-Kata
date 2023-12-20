package com.murex.ranking;

import com.murex.Hand;
import org.junit.jupiter.api.Test;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.*;

class ThreeOfAKindRankingTest {

    @Test
    public void it_should_return_a_non_matching_result_when_both_hands_have_no_three_cards_with_same_kind() {
        ThreeOfAKindRanking threeOfAKindRanking = buildThreeOfAKindRanking("7H 8C TD KH AS", "2D 3H 5C 9S KH");
        assertEquals(aNoMatchResult(), threeOfAKindRanking.getMatchingResult());
    }

    private static ThreeOfAKindRanking buildThreeOfAKindRanking(String blackHandCards, String whiteHandCards) {
        Hand blackHand = Hand.buildFrom("Black", blackHandCards);
        Hand whiteHand = Hand.buildFrom("White", whiteHandCards);
        return new ThreeOfAKindRanking(blackHand, whiteHand);
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_three_cards_of_9() {
        ThreeOfAKindRanking threeOfAKindRanking = buildThreeOfAKindRanking("7H 7C TD KH AS", "2D 9H 9C 9S KH");
        assertEquals(aMatchResult("White wins. - with three of a kind: 9"), threeOfAKindRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_three_cards_of_10() {
        ThreeOfAKindRanking threeOfAKindRanking = buildThreeOfAKindRanking("7H 7C TD KH AS", "2D 9H TC TS TH");
        assertEquals(aMatchResult("White wins. - with three of a kind: Ten"), threeOfAKindRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_three_cards_of_7() {
        ThreeOfAKindRanking threeOfAKindRanking = buildThreeOfAKindRanking("7H 7C 7D KH AS", "2D 9H 8C TS KH");
        assertEquals(aMatchResult("Black wins. - with three of a kind: 7"), threeOfAKindRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_three_cards_of_Ace_over_white_having_three_cards_of_9() {
        ThreeOfAKindRanking threeOfAKindRanking = buildThreeOfAKindRanking("2H 3C AD AH AS", "2D 9H 9C 9S KH");
        assertEquals(aMatchResult("Black wins. - with three of a kind: Ace"), threeOfAKindRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_three_cards_of_Jack_over_black_having_three_cards_of_8() {
        ThreeOfAKindRanking threeOfAKindRanking = buildThreeOfAKindRanking("2H 3C 8D 8H 8S", "2D JH JC JS KH");
        assertEquals(aMatchResult("White wins. - with three of a kind: Jack"), threeOfAKindRanking.getMatchingResult());
    }

}