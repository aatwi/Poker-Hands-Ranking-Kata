package poker.hand.ranking;

import poker.hand.Hand;
import poker.hand.HandBuilder;
import org.junit.jupiter.api.Test;

import static poker.hand.ResultHelper.*;
import static org.junit.jupiter.api.Assertions.*;

class StraightFlushRankingTestCategory {

    @Test
    public void it_should_return_a_no_matching_result_when_non_of_the_players_have_straight_flush_cards() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7H 8C TD KH AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2D 3H 5C 9S KH").build();

        StraightFlush handRanking = new StraightFlush(blackHand, whiteHand);
        assertEquals(aNoWinner(), handRanking.evaluate());
    }

    @Test
    public void it_should_return_a_tie_result_when_both_players_have_same_straight_flush_cards() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7H 8H 9H TH JH").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("7S 8S 9S TS JS").build();

        StraightFlush handRanking = new StraightFlush(blackHand, whiteHand);
        assertEquals(aTieResult(), handRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7H 8C TD KH AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2D 3D 4D 5D 6D").build();

        StraightFlush handRanking = new StraightFlush(blackHand, whiteHand);
        assertEquals(aStraightFlushWinningResult(whiteHand, false), handRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("2D 3D 4D 5D 6D").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("7H 8C TD KH AS").build();

        StraightFlush handRanking = new StraightFlush(blackHand, whiteHand);
        assertEquals(aStraightFlushWinningResult(blackHand, false), handRanking.evaluate());
    }


    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_with_higher_hand() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7H 8H 9H TH JH").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2D 3D 4D 5D 6D").build();

        StraightFlush handRanking = new StraightFlush(blackHand, whiteHand);
        assertEquals(aStraightFlushWinningResult(blackHand, true), handRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_with_higher_hand() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7H 8H 9H TH JH").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("8D 9D TD JD QD").build();

        StraightFlush handRanking = new StraightFlush(blackHand, whiteHand);
        assertEquals(aStraightFlushWinningResult(whiteHand, true), handRanking.evaluate());
    }
}