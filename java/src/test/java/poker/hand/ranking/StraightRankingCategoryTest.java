package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.HandBuilder;
import org.junit.jupiter.api.Test;

import static poker.hand.ResultHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StraightRankingCategoryTest {

    @Test
    public void it_should_return_a_no_matching_result_when_non_of_the_players_have_straight_cards() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7H 8C TD KH AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2D 3H 5C 9S KH").build();

        Straight straightHandRanking = new Straight(blackHand, whiteHand);
        assertEquals(aNoWinner(), straightHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_tie_result_when_both_players_have_same_straight_cards() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7H 8C 9D TH JS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("7D 8H 9C TS JH").build();

        Straight straightHandRanking = new Straight(blackHand, whiteHand);
        assertEquals(aTieResult(), straightHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_straight_cards_2_to_6() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7H 8C TD KH AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2D 3H 4C 5S 6H").build();

        Straight straightHandRanking = new Straight(blackHand, whiteHand);
        assertEquals(aStraightWinningResult(whiteHand, false), straightHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_straight_cards_8_to_Q() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7H 8C TD KH AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("8D 9H TC JS QH").build();

        Straight straightHandRanking = new Straight(blackHand, whiteHand);
        assertEquals(aStraightWinningResult(whiteHand, false), straightHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_straight_cards_7_to_Jack() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7H 8C 9D TH JS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2D 3H 7C JH AD").build();

        Straight straightHandRanking = new Straight(blackHand, whiteHand);
        assertEquals(aStraightWinningResult(blackHand, false), straightHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_straight_cards_3_to_7() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("3H 4C 5D 6H 7S").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2D 3H 7C JH AD").build();

        Straight straightHandRanking = new Straight(blackHand, whiteHand);
        assertEquals(aStraightWinningResult(blackHand, false), straightHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_straight_and_higher_cards() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7H 8C 9D TH JS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2D 3H 4C 5S 6H").build();

        Straight straightHandRanking = new Straight(blackHand, whiteHand);
        assertEquals(aStraightWinningResult(blackHand, true), straightHandRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_straight_and_higher_cards() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("4H 5C 6D 7H 8S").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("6D 7H 8C 9S TH").build();

        Straight straightHandRanking = new Straight(blackHand, whiteHand);
        assertEquals(aStraightWinningResult(whiteHand, true), straightHandRanking.evaluate());
    }


}