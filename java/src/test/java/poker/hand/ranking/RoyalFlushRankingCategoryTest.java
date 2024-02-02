package poker.hand.ranking;

import org.junit.jupiter.api.Test;
import poker.hand.Hand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static poker.hand.HandBuilder.aHand;
import static poker.hand.result.ResultHelper.*;

class RoyalFlushRankingCategoryTest {

    @Test
    public void it_should_return_a_no_matching_result_when_non_of_the_players_have_royal_flush_cards() {
        Hand blackHand = aHand().withPlayer("Black").withCards("7H 8C TD KH AS").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("2D 3H 5C 9S KH").build();

        RoyalFlush handRanking = new RoyalFlush(blackHand, whiteHand);
        assertEquals(aNoWinner(), handRanking.evaluate());
    }

    @Test
    public void it_should_return_a_tie_when_both_players_have_same_royal_flush_cards() {
        Hand blackHand = aHand().withPlayer("Black").withCards("TH JH QH KH AH").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("TS JS QS KS AS").build();

        RoyalFlush handRanking = new RoyalFlush(blackHand, whiteHand);
        assertEquals(aTie(), handRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner() {
        Hand blackHand = aHand().withPlayer("Black").withCards("7H 8H 9H TH JH").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("TS JS QS KS AS").build();

        RoyalFlush handRanking = new RoyalFlush(blackHand, whiteHand);
        assertEquals(aRoyalFlushWinningResult(whiteHand), handRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner() {
        Hand blackHand = aHand().withPlayer("Black").withCards("TH JH QH KH AH").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("7H 8H 9H TH JH").build();

        RoyalFlush handRanking = new RoyalFlush(blackHand, whiteHand);
        assertEquals(aRoyalFlushWinningResult(blackHand), handRanking.evaluate());
    }
}