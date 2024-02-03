package poker.hand.ranking;

import org.junit.jupiter.api.Test;
import poker.hand.Hand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static poker.hand.HandBuilder.aHand;
import static poker.hand.result.ResultHelper.aFullHouseWinningResult;
import static poker.hand.result.ResultHelper.aNoWinner;

class FullHouseTest {

    @Test
    public void it_should_return_a_no_matching_result_when_neither_hands_have_full_house() {
        Hand blackHand = aHand().withPlayer("Black").withCards("3H 3S 4C AH AD").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("4D 5S 7D JS AC").build();

        FullHouse fullHouseCardRanking = FullHouse.aFullHouse(blackHand, whiteHand);
        assertEquals(aNoWinner(), fullHouseCardRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_hearts() {
        Hand blackHand = aHand().withPlayer("Black").withCards("3H 3S 3C AH AD").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("4D 5S 7D JS AC").build();

        FullHouse fullHouseCardRanking = FullHouse.aFullHouse(blackHand, whiteHand);
        assertEquals(aFullHouseWinningResult(blackHand, false), fullHouseCardRanking.evaluate());
    }

    @Test
    public void it_should_return_a_a_no_matching_result_result_when_black_has_only_3_cards_of_same_value() {
        Hand blackHand = aHand().withPlayer("Black").withCards("3H 3S 3C KH AD").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("4D 5S 7D JS AC").build();

        FullHouse fullHouseCardRanking = FullHouse.aFullHouse(blackHand, whiteHand);
        assertEquals(aNoWinner(), fullHouseCardRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_flush_with_hearts() {
        Hand blackHand = aHand().withPlayer("Black").withCards("4D 5S 7D JS AC").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("5H 5S 5C KH KD").build();

        FullHouse fullHouseCardRanking = FullHouse.aFullHouse(blackHand, whiteHand);
        assertEquals(aFullHouseWinningResult(whiteHand, false), fullHouseCardRanking.evaluate());
    }

    @Test
    public void it_should_return_a_no_matching_result_result_when_white_has_only_3_cards_of_same_value() {
        Hand blackHand = aHand().withPlayer("Black").withCards("4D 5S 7D JS AC").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("4H 4S 4C KH AD").build();

        FullHouse fullHouseCardRanking = FullHouse.aFullHouse(blackHand, whiteHand);
        assertEquals(aNoWinner(), fullHouseCardRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_result_when_white_and_black_have_full_house_and_higher_cards_for_white() {
        Hand blackHand = aHand().withPlayer("Black").withCards("4D 4S 4D JS JC").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("5H 5S 5C TH TD").build();

        FullHouse fullHouseCardRanking = FullHouse.aFullHouse(blackHand, whiteHand);
        assertEquals(aFullHouseWinningResult(whiteHand, true), fullHouseCardRanking.evaluate());
    }

    @Test
    public void it_should_return_a_matching_result_result_when_white_and_black_have_full_house_and_higher_cards_for_black() {
        Hand blackHand = aHand().withPlayer("Black").withCards("5H 5S 5C TH TD").build();
        Hand whiteHand = aHand().withPlayer("White").withCards("4D 4S 4D JS JC").build();

        FullHouse fullHouseCardRanking = FullHouse.aFullHouse(blackHand, whiteHand);
        assertEquals(aFullHouseWinningResult(blackHand, true), fullHouseCardRanking.evaluate());
    }
}