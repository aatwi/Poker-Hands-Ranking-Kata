package poker.hand.ranking;

import org.junit.jupiter.api.Test;
import poker.hand.CardNumber;
import poker.hand.Hand;
import poker.hand.result.Result;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static poker.hand.HandBuilder.aHand;
import static poker.hand.result.ResultHelper.aHighCardWinningResult;
import static poker.hand.result.ResultHelper.aNoWinner;

class HighCardRankingTest {

    private static Result buildHighCardResult(Hand black, Hand white) {
        HighCard highCard = new HighCard(black, white);
        return highCard.evaluate();
    }

    @Test
    public void it_should_return_a_no_matching_result_when_there_is_no_high_hand() {
        Hand black = aHand().withPlayer("Black").withCards("2H 3D 5S 9C KD").build();
        Hand white = aHand().withPlayer("White").withCards("2D 3S 5C 9D KC").build();
        HighCard highCard = new HighCard(black, white);

        assertEquals(aNoWinner(), highCard.evaluate());
    }

    @Test
    public void HIGH_CARD_white_wins_with_Ace() {
        Hand black = aHand().withPlayer("Black").withCards("2H 3D 5S 9C KD").build();
        Hand white = aHand().withPlayer("White").withCards("2C 3H 4S 8C AH").build();

        assertEquals(aHighCardWinningResult(white, CardNumber.ACE), buildHighCardResult(black, white));
    }

    @Test
    public void HIGH_CARD_black_wins_with_Queen() {
        Hand black = aHand().withPlayer("Black").withCards("2H 3D 5S 9C QD").build();
        Hand white = aHand().withPlayer("White").withCards("2C 3H 4S 8C JH").build();

        assertEquals(aHighCardWinningResult(black, CardNumber.QUEEN), buildHighCardResult(black, white));
    }

    @Test
    public void HIGH_CARD_white_wins_Jack() {
        Hand black = aHand().withPlayer("Black").withCards("2H 3D 5S 7C 9D").build();
        Hand white = aHand().withPlayer("White").withCards("2C 3H 4S 8C JH").build();

        assertEquals(aHighCardWinningResult(white, CardNumber.JACK), buildHighCardResult(black, white));
    }

    @Test
    public void HIGH_CARD_black_wins_with_9() {
        Hand black = aHand().withPlayer("Black").withCards("2H 3D 5S 8C 9D").build();
        Hand white = aHand().withPlayer("White").withCards("2C 3H 4S 7C 8H").build();

        assertEquals(aHighCardWinningResult(black, CardNumber.NINE), buildHighCardResult(black, white));
    }

    @Test
    public void white_wins_with_9() {
        Hand black = aHand().withPlayer("Black").withCards("2H 3D 5S 8C AD").build();
        Hand white = aHand().withPlayer("White").withCards("2S 3C 5D 9S AD").build();

        assertEquals(aHighCardWinningResult(white, CardNumber.NINE), buildHighCardResult(black, white));
    }
}