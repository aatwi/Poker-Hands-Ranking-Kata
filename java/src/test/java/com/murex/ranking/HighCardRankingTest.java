package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Test;

import static com.murex.CardNumber.*;
import static com.murex.Hand.aHand;
import static com.murex.ResultHelper.aHighCardWinningResult;
import static com.murex.ResultHelper.aNoWinner;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HighCardRankingTest {

    private static Result buildHighCardResult(Hand black, Hand white) {
        HighCardRanking highCardRanking = new HighCardRanking(black, white);
        return highCardRanking.evaluate();
    }

    @Test
    public void it_should_return_a_no_matching_result_when_there_is_no_high_hand() {
        Hand black = aHand("Black", "2H 3D 5S 9C KD");
        Hand white = aHand("White", "2D 3S 5C 9D KC");
        HighCardRanking highCardRanking = new HighCardRanking(black, white);

        assertEquals(aNoWinner(), highCardRanking.evaluate());
    }

    @Test
    public void HIGH_CARD_white_wins_with_Ace() {
        Hand black = aHand("Black", "2H 3D 5S 9C KD");
        Hand white = aHand("White", "2C 3H 4S 8C AH");

        assertEquals(aHighCardWinningResult(white, ACE), buildHighCardResult(black, white));
    }

    @Test
    public void HIGH_CARD_black_wins_with_Queen() {
        Hand black = aHand("Black", "2H 3D 5S 9C QD");
        Hand white = aHand("White", "2C 3H 4S 8C JH");

        assertEquals(aHighCardWinningResult(black, QUEEN), buildHighCardResult(black, white));
    }

    @Test
    public void HIGH_CARD_white_wins_Jack() {
        Hand black = aHand("Black", "2H 3D 5S 7C 9D");
        Hand white = aHand("White", "2C 3H 4S 8C JH");

        assertEquals(aHighCardWinningResult(white, JACK), buildHighCardResult(black, white));
    }

    @Test
    public void HIGH_CARD_black_wins_with_9() {
        Hand black = aHand("Black", "2H 3D 5S 8C 9D");
        Hand white = aHand("White", "2C 3H 4S 7C 8H");

        assertEquals(aHighCardWinningResult(black, NINE), buildHighCardResult(black, white));
    }

    @Test
    public void white_wins_with_9() {
        Hand black = aHand("Black", "2H 3D 5S 8C AD");
        Hand white = aHand("White", "2S 3C 5D 9S AD");

        assertEquals(aHighCardWinningResult(white, NINE), buildHighCardResult(black, white));
    }
}