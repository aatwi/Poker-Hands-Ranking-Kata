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
    public void it_should_return_a_matching_result_with_white_as_winner_having_three_cards_of_9() {
        Hand blackHand = Hand.buildFrom("Black", "7H 7C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White",  "2D 9H 9C 9S KH");
        ThreeOfAKindRanking threeOfAKindRanking = new ThreeOfAKindRanking(blackHand, whiteHand);
        assertEquals(aMatchResult("White wins. - with three of a kind: 9"), threeOfAKindRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_three_cards_of_10() {
        Hand blackHand = Hand.buildFrom("Black", "7H 7C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White",  "2D 9H TC TS TH");
        ThreeOfAKindRanking threeOfAKindRanking = new ThreeOfAKindRanking(blackHand, whiteHand);
        assertEquals(aMatchResult("White wins. - with three of a kind: Ten"), threeOfAKindRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_three_cards_of_7() {
        Hand blackHand = Hand.buildFrom("Black", "7H 7C 7D KH AS");
        Hand whiteHand = Hand.buildFrom("White",  "2D 9H 8C TS KH");
        ThreeOfAKindRanking threeOfAKindRanking = new ThreeOfAKindRanking(blackHand, whiteHand);
        assertEquals(aMatchResult("Black wins. - with three of a kind: 7"), threeOfAKindRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_three_cards_of_Ace_over_white_having_three_cards_of_9() {
        Hand blackHand = Hand.buildFrom("Black", "2H 3C AD AH AS");
        Hand whiteHand = Hand.buildFrom("White",  "2D 9H 9C 9S KH");
        ThreeOfAKindRanking threeOfAKindRanking = new ThreeOfAKindRanking(blackHand, whiteHand);
        assertEquals(aMatchResult("Black wins. - with three of a kind: Ace"), threeOfAKindRanking.getMatchingResult());
    }

}