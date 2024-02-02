package poker.hand.ranking;

import org.junit.jupiter.api.Test;
import poker.hand.Hand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static poker.hand.HandBuilder.aHand;
import static poker.hand.result.ResultHelper.*;

class StraightFlushRankingCategoryTest {

    @Test
    public void it_should_return_a_no_matching_result_when_non_of_the_players_have_straight_flush_cards() {
        Hand blackHand = aHand().withPlayer("Black").withCards("7H 8C TD KH AS").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("2D 3H 5C 9S KH").build();

        StraightFlush handRanking = new StraightFlush(blackHand, whiteHand);
        assertEquals(aNoWinner(), handRanking.evaluate());
    }

    @Test
    public void it_should_return_a_tie_result_when_both_players_have_same_straight_flush_cards() {
        Hand blackHand = aHand().withPlayer("Black").withCards("7H 8H 9H TH JH").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("7S 8S 9S TS JS").build();

        StraightFlush handRanking = new StraightFlush(blackHand, whiteHand);
        assertEquals(aTie(), handRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner() {
        Hand blackHand = aHand().withPlayer("Black").withCards("7H 8C TD KH AS").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("2D 3D 4D 5D 6D").build();

        StraightFlush handRanking = new StraightFlush(blackHand, whiteHand);
        assertEquals(aStraightFlushWinningResult(whiteHand, false), handRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner() {
        Hand blackHand = aHand().withPlayer("Black").withCards("2D 3D 4D 5D 6D").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("7H 8C TD KH AS").build();

        StraightFlush handRanking = new StraightFlush(blackHand, whiteHand);
        assertEquals(aStraightFlushWinningResult(blackHand, false), handRanking.evaluate());
    }


    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_with_higher_hand() {
        Hand blackHand = aHand().withPlayer("Black").withCards("7H 8H 9H TH JH").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("2D 3D 4D 5D 6D").build();

        StraightFlush handRanking = new StraightFlush(blackHand, whiteHand);
        assertEquals(aStraightFlushWinningResult(blackHand, true), handRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_with_higher_hand() {
        Hand blackHand = aHand().withPlayer("Black").withCards("7H 8H 9H TH JH").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("8D 9D TD JD QD").build();

        StraightFlush handRanking = new StraightFlush(blackHand, whiteHand);
        assertEquals(aStraightFlushWinningResult(whiteHand, true), handRanking.evaluate());
    }
}