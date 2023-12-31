package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import com.murex.hands.HighHand;
import org.junit.jupiter.api.Test;

import static com.murex.CardNumber.*;
import static com.murex.Hand.buildFrom;
import static com.murex.Result.aHighCardWinningResult;
import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HighCardRankingTest {

    private static Result buildHighCardResult(Hand black, Hand white) {
        HighCardRanking highCardRanking = new HighCardRanking(black, white);
        return highCardRanking.getMatchingResult();
    }

    @Test
    public void it_should_return_a_no_matching_result_when_there_is_no_high_hand() {
        Hand black = buildFrom("Black", "2H 3D 5S 9C KD");
        Hand white = buildFrom("White", "2D 3S 5C 9D KC");
        HighCardRanking highCardRanking = new HighCardRanking(black, white);

        assertEquals(aNoMatchResult(), highCardRanking.getMatchingResult());
    }

    @Test
    public void HIGH_CARD_white_wins_with_Ace() {
        Hand black = buildFrom("Black", "2H 3D 5S 9C KD");
        Hand white = buildFrom("White", "2C 3H 4S 8C AH");

        assertEquals(aHighCardWinningResult(white, ACE), buildHighCardResult(black, white));
    }

    @Test
    public void HIGH_CARD_black_wins_with_Queen() {
        Hand black = buildFrom("Black", "2H 3D 5S 9C QD");
        Hand white = buildFrom("White", "2C 3H 4S 8C JH");

        assertEquals(aHighCardWinningResult(black, QUEEN), buildHighCardResult(black, white));
    }

    @Test
    public void HIGH_CARD_white_wins_Jack() {
        Hand black = buildFrom("Black", "2H 3D 5S 7C 9D");
        Hand white = buildFrom("White", "2C 3H 4S 8C JH");

        assertEquals(aHighCardWinningResult(white, JACK), buildHighCardResult(black, white));
    }

    @Test
    public void HIGH_CARD_black_wins_with_9() {
        Hand black = buildFrom("Black", "2H 3D 5S 8C 9D");
        Hand white = buildFrom("White", "2C 3H 4S 7C 8H");

        assertEquals(aHighCardWinningResult(black, NINE), buildHighCardResult(black, white));
    }

    @Test
    public void white_wins_with_9() {
        Hand black = buildFrom("Black", "2H 3D 5S 8C AD");
        Hand white = buildFrom("White", "2S 3C 5D 9S AD");

        assertEquals(aHighCardWinningResult(white, NINE), buildHighCardResult(black, white));
    }

    @Test
    public void get_the_higher_hand() {
        Hand blackHand = buildFrom("Black", "2H 3D 5S 8C AD");
        Hand whiteHand = buildFrom("White", "2S 3C 5D 9S AD");

        HighCardRanking highCardRanking1 = new HighCardRanking(blackHand, whiteHand);
        HighHand higherHand = highCardRanking1.getHigherHand().get();
        assertEquals(whiteHand, higherHand.getHand());
    }

}