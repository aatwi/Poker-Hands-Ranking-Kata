package com.murex.ranking;

import com.murex.Hand;
import com.murex.ResultHelper;
import org.junit.jupiter.api.Test;

import static com.murex.ResultHelper.aNoWinner;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StraightOrderRankingTest {

    @Test
    public void it_should_return_a_no_matching_result_when_non_of_the_players_have_straight_cards() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 5C 9S KH");

        StraightOrderRanking straightHandRanking = new StraightOrderRanking(blackHand, whiteHand);
        assertEquals(aNoWinner(), straightHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_straight_cards_2_to_6() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 4C 5S 6H");

        StraightOrderRanking straightHandRanking = new StraightOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aStraightWinningResult(whiteHand, false), straightHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_straight_cards_8_to_Q() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White", "8D 9H TC JS QH");

        StraightOrderRanking straightHandRanking = new StraightOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aStraightWinningResult(whiteHand, false), straightHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_straight_cards_7_to_Jack() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C 9D TH JS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 7C JH AD");

        StraightOrderRanking straightHandRanking = new StraightOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aStraightWinningResult(blackHand, false), straightHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_straight_cards_3_to_7() {
        Hand blackHand = Hand.buildFrom("Black", "3H 4C 5D 6H 7S");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 7C JH AD");

        StraightOrderRanking straightHandRanking = new StraightOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aStraightWinningResult(blackHand, false), straightHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_straight_and_higher_cards() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C 9D TH JS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 4C 5S 6H");

        StraightOrderRanking straightHandRanking = new StraightOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aStraightWinningResult(blackHand, true), straightHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_straight_and_higher_cards() {
        Hand blackHand = Hand.buildFrom("Black", "4H 5C 6D 7H 8S");
        Hand whiteHand = Hand.buildFrom("White", "6D 7H 8C 9S TH");

        StraightOrderRanking straightHandRanking = new StraightOrderRanking(blackHand, whiteHand);
        assertEquals(ResultHelper.aStraightWinningResult(whiteHand, true), straightHandRanking.evaluate());
    }


}