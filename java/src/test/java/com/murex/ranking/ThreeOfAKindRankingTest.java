package com.murex.ranking;

import com.murex.CardNumber;
import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Test;

import static com.murex.CardNumber.*;
import static com.murex.Result.aNoMatchResult;
import static com.murex.Result.aThreeOfAKindWinningResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreeOfAKindRankingTest {

    @Test
    public void it_should_return_a_non_matching_result_when_both_hands_have_no_three_cards_with_same_kind() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 5C 9S KH");

        assertEquals(aNoMatchResult(), new ThreeOfAKindRanking(blackHand, whiteHand).getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_three_cards_of_9() {
        Hand blackHand = Hand.buildFrom("Black", "7H 7C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 9H 9C 9S KH");

        assertEquals(aThreeOfAKindWinningResult(whiteHand, NINE), new ThreeOfAKindRanking(blackHand, whiteHand).getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_three_cards_of_10() {
        Hand blackHand = Hand.buildFrom("Black", "7H 7C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 9H TC TS TH");

        assertEquals(aThreeOfAKindWinningResult(whiteHand, TEN), new ThreeOfAKindRanking(blackHand, whiteHand).getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_three_cards_of_7() {
        Hand blackHand = Hand.buildFrom("Black", "7H 7C 7D KH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 9H 8C TS KH");

        assertEquals(aThreeOfAKindWinningResult(blackHand, SEVEN), new ThreeOfAKindRanking(blackHand, whiteHand).getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_three_cards_of_Ace_over_white_having_three_cards_of_9() {
        Hand blackHand = Hand.buildFrom("Black", "2H 3C AD AH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 9H 9C 9S KH");

        assertEquals(aThreeOfAKindWinningResult(blackHand, ACE), new ThreeOfAKindRanking(blackHand, whiteHand).getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_three_cards_of_Jack_over_black_having_three_cards_of_8() {
        Hand blackHand = Hand.buildFrom("Black", "2H 3C 8D 8H 8S");
        Hand whiteHand = Hand.buildFrom("White", "2D JH JC JS KH");

        assertEquals(aThreeOfAKindWinningResult(whiteHand, JACK), new ThreeOfAKindRanking(blackHand, whiteHand).getMatchingResult());
    }

}