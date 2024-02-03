package poker.hand.ranking;

import org.junit.jupiter.api.Test;
import poker.hand.Hand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static poker.hand.HandBuilder.aHand;
import static poker.hand.result.ResultHelper.aFourOfAKindWinningResult;
import static poker.hand.result.ResultHelper.aNoWinner;

class FourOfAKindTest {

    @Test
    public void it_should_return_a_no_matching_result_when_neither_hands_have_full_house() {
        Hand blackHand = aHand().withPlayer("Black").withCards("3H 3S 4C AH AD").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("4D 5S 7D JS AC").build();

        FourOfAKind fourOfAKind = FourOfAKind.aFourOfAKind(blackHand, whiteHand);
        assertEquals(aNoWinner(), fourOfAKind.evaluate());
    }

    @Test
    public void it_should_return_a_result_with_black_as_a_winner() {
        Hand blackHand = aHand().withPlayer("Black").withCards("3H 3S 3C 3D AD").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("4D 5S 7D JS AC").build();

        FourOfAKind fourOfAKind = FourOfAKind.aFourOfAKind(blackHand, whiteHand);
        assertEquals(aFourOfAKindWinningResult(blackHand, false), fourOfAKind.evaluate());
    }

    @Test
    public void it_should_return_a_result_with_white_as_a_winner() {
        Hand blackHand = aHand().withPlayer("Black").withCards("4D 5S 7D JS AC").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("2H 4S 4C 4D 4H").build();

        FourOfAKind fourOfAKind = FourOfAKind.aFourOfAKind(blackHand, whiteHand);
        assertEquals(aFourOfAKindWinningResult(whiteHand, false), fourOfAKind.evaluate());
    }

    @Test
    public void it_should_return_a_result_with_black_as_a_winner_with_higher_hand() {
        Hand blackHand = aHand().withPlayer("Black").withCards("7D 7S 7H 7C AC").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("2H 4S 4C 4D 4H").build();

        FourOfAKind fourOfAKind = FourOfAKind.aFourOfAKind(blackHand, whiteHand);
        assertEquals(aFourOfAKindWinningResult(blackHand, true), fourOfAKind.evaluate());
    }

    @Test
    public void it_should_return_a_result_with_white_as_a_winner_with_higher_hand() {
        Hand blackHand = aHand().withPlayer("Black").withCards("7D 7S 7H 7C AC").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("2H TS TC TD TH").build();

        FourOfAKind fourOfAKind = FourOfAKind.aFourOfAKind(blackHand, whiteHand);
        assertEquals(aFourOfAKindWinningResult(whiteHand, true), fourOfAKind.evaluate());
    }

}