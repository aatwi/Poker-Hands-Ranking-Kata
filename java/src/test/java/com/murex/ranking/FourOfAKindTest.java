package com.murex.ranking;

import com.murex.Hand;
import com.murex.HandBuilder;
import org.junit.jupiter.api.Test;

import static com.murex.ResultHelper.aFourOfAKindWinningResult;
import static com.murex.ResultHelper.aNoWinner;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FourOfAKindRankingTest {

    @Test
    public void it_should_return_a_no_matching_result_when_neither_hands_have_full_house() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("3H 3S 4C AH AD").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("4D 5S 7D JS AC").build();

        FourOfAKindRanking fourOfAKindRanking = new FourOfAKindRanking(blackHand, whiteHand);
        assertEquals(aNoWinner(), fourOfAKindRanking.evaluate());
    }

    @Test
    public void it_should_return_a_result_with_black_as_a_winner() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("3H 3S 3C 3D AD").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("4D 5S 7D JS AC").build();

        FourOfAKindRanking fourOfAKindRanking = new FourOfAKindRanking(blackHand, whiteHand);
        assertEquals(aFourOfAKindWinningResult(blackHand, false), fourOfAKindRanking.evaluate());
    }

    @Test
    public void it_should_return_a_result_with_white_as_a_winner() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("4D 5S 7D JS AC").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2H 4S 4C 4D 4H").build();

        FourOfAKindRanking fourOfAKindRanking = new FourOfAKindRanking(blackHand, whiteHand);
        assertEquals(aFourOfAKindWinningResult(whiteHand, false), fourOfAKindRanking.evaluate());
    }

    @Test
    public void it_should_return_a_result_with_black_as_a_winner_with_higher_hand() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7D 7S 7H 7C AC").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2H 4S 4C 4D 4H").build();

        FourOfAKindRanking fourOfAKindRanking = new FourOfAKindRanking(blackHand, whiteHand);
        assertEquals(aFourOfAKindWinningResult(blackHand, true), fourOfAKindRanking.evaluate());
    }

    @Test
    public void it_should_return_a_result_with_white_as_a_winner_with_higher_hand() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7D 7S 7H 7C AC").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2H TS TC TD TH").build();

        FourOfAKindRanking fourOfAKindRanking = new FourOfAKindRanking(blackHand, whiteHand);
        assertEquals(aFourOfAKindWinningResult(whiteHand, true), fourOfAKindRanking.evaluate());
    }

}