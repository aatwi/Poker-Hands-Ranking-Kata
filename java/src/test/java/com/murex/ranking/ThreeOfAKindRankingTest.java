package com.murex.ranking;

import com.murex.Hand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.*;

class ThreeOfAKindRankingTest {

    @Test
    public void it_should_return_a_non_matching_result_when_both_hands_have_no_three_cards_with_same_kind() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White",  "2D 3H 5C 9S KH");
        ThreeOfAKindRanking threeOfAKindRanking = new ThreeOfAKindRanking(blackHand, whiteHand);
        assertEquals(aNoMatchResult(), threeOfAKindRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner() {
        Hand blackHand = Hand.buildFrom("Black", "7H 7C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White",  "2D 9H 9C 9S KH");
        ThreeOfAKindRanking threeOfAKindRanking = new ThreeOfAKindRanking(blackHand, whiteHand);
        assertEquals(aMatchResult("White wins. - with three of a kind: 9"), threeOfAKindRanking.getMatchingResult());

    }
}