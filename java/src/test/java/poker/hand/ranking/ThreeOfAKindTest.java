package poker.hand.ranking;

import org.junit.jupiter.api.Test;
import poker.hand.CardNumber;
import poker.hand.Hand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static poker.hand.HandBuilder.aHand;
import static poker.hand.result.ResultHelper.aNoWinner;
import static poker.hand.result.ResultHelper.aThreeOfAKindWinningResult;

class ThreeOfAKindTest {

    @Test
    public void it_should_return_a_non_matching_result_when_both_hands_have_no_three_cards_with_same_kind() {
        Hand blackHand = aHand().withPlayer("Black").withCards("7H 8C TD KH AS").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("2D 3H 5C 9S KH").build();

        assertEquals(aNoWinner(), new ThreeOfAKind(blackHand, whiteHand).evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_three_cards_of_9() {
        Hand blackHand = aHand().withPlayer("Black").withCards("7H 7C TD KH AS").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("2D 9H 9C 9S KH").build();

        assertEquals(aThreeOfAKindWinningResult(whiteHand, CardNumber.NINE, false), new ThreeOfAKind(blackHand, whiteHand).evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_three_cards_of_10() {
        Hand blackHand = aHand().withPlayer("Black").withCards("7H 7C TD KH AS").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("2D 9H TC TS TH").build();

        assertEquals(aThreeOfAKindWinningResult(whiteHand, CardNumber.TEN, false), new ThreeOfAKind(blackHand, whiteHand).evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_three_cards_of_7() {
        Hand blackHand = aHand().withPlayer("Black").withCards("7H 7C 7D KH AS").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("2D 9H 8C TS KH").build();

        assertEquals(aThreeOfAKindWinningResult(blackHand, CardNumber.SEVEN, false), new ThreeOfAKind(blackHand, whiteHand).evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_three_cards_of_Ace_over_white_having_three_cards_of_9() {
        Hand blackHand = aHand().withPlayer("Black").withCards("2H 3C AD AH AS").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("2D 9H 9C 9S KH").build();

        assertEquals(aThreeOfAKindWinningResult(blackHand, CardNumber.ACE, true), new ThreeOfAKind(blackHand, whiteHand).evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_three_cards_of_Jack_over_black_having_three_cards_of_8() {
        Hand blackHand = aHand().withPlayer("Black").withCards("2H 3C 8D 8H 8S").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("2D JH JC JS KH").build();

        assertEquals(aThreeOfAKindWinningResult(whiteHand, CardNumber.JACK, true), new ThreeOfAKind(blackHand, whiteHand).evaluate());
    }

}