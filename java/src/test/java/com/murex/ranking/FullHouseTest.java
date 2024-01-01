package com.murex.ranking;

import com.murex.Hand;
import com.murex.HandBuilder;
import org.junit.jupiter.api.Test;

import static com.murex.ResultHelper.aFullHouseWinningResult;
import static com.murex.ResultHelper.aNoWinner;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FullHouseTest {

    @Test
    public void it_should_return_a_no_matching_result_when_neither_hands_have_full_house() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("3H 3S 4C AH AD").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("4D 5S 7D JS AC").build();

        FullHouse fullHouseCardRanking = new FullHouse(blackHand, whiteHand);
        assertEquals(aNoWinner(), fullHouseCardRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_hearts() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("3H 3S 3C AH AD").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("4D 5S 7D JS AC").build();

        FullHouse fullHouseCardRanking = new FullHouse(blackHand, whiteHand);
        assertEquals(aFullHouseWinningResult(blackHand, false), fullHouseCardRanking.evaluate());
    }

    @Test
    public void it_should_return_a_a_no_matching_result_result_when_black_has_only_3_cards_of_same_value() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("3H 3S 3C KH AD").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("4D 5S 7D JS AC").build();

        FullHouse fullHouseCardRanking = new FullHouse(blackHand, whiteHand);
        assertEquals(aNoWinner(), fullHouseCardRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_flush_with_hearts() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("4D 5S 7D JS AC").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("5H 5S 5C KH KD").build();

        FullHouse fullHouseCardRanking = new FullHouse(blackHand, whiteHand);
        assertEquals(aFullHouseWinningResult(whiteHand, false), fullHouseCardRanking.evaluate());
    }

    @Test
    public void it_should_return_a_no_matching_result_result_when_white_has_only_3_cards_of_same_value() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("4D 5S 7D JS AC").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("4H 4S 4C KH AD").build();

        FullHouse fullHouseCardRanking = new FullHouse(blackHand, whiteHand);
        assertEquals(aNoWinner(), fullHouseCardRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_result_when_white_and_black_have_full_house_and_higher_cards_for_white() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("4D 4S 4D JS JC").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("5H 5S 5C TH TD").build();

        FullHouse fullHouseCardRanking = new FullHouse(blackHand, whiteHand);
        assertEquals(aFullHouseWinningResult(whiteHand, true), fullHouseCardRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_result_when_white_and_black_have_full_house_and_higher_cards_for_black() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("5H 5S 5C TH TD").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("4D 4S 4D JS JC").build();

        FullHouse fullHouseCardRanking = new FullHouse(blackHand, whiteHand);
        assertEquals(aFullHouseWinningResult(blackHand, true), fullHouseCardRanking.evaluate());
    }
}