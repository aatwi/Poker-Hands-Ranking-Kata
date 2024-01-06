package com.murex.ranking;

import com.murex.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairCardRankingTest {

    private static Result buildPairCardResult(Hand blackHand, Hand whiteHand) {
        Pair pair = new Pair(blackHand, whiteHand);
        return pair.evaluate();
    }

    @Test
    public void
    it_should_return_a_non_matching_result_when_no_pair_is_found() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7H 8C TD KH AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2D 3H 5C 9S KH").build();

        assertEquals(ResultHelper.aNoWinner(), buildPairCardResult(blackHand, whiteHand));
    }

    @Test
    public void
    it_should_return_a_non_matching_result_when_having_all_tie_with_pairs() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7H 7C TD KH AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("7D 7S TC KS AH").build();

        assertEquals(ResultHelper.aNoWinner(), buildPairCardResult(blackHand, whiteHand));
    }

    @Test
    public void
    it_should_return_a_non_matching_result_when_having_pairs_tied_as_2() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("2H 2D 5S 6C JS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2C 2S 6H 8H AC").build();

        assertEquals(ResultHelper.aNoWinner(), buildPairCardResult(blackHand, whiteHand));
    }

    @Test
    public void
    it_should_return_a_non_matching_result_when_having_pairs_tied_as_ace() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("2H 4D 8S AC AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2C 3S 7H AH AD").build();

        assertEquals(ResultHelper.aNoWinner(), buildPairCardResult(blackHand, whiteHand));
    }

    @Test
    public void
    it_should_return_a_matching_result_when_having_a_winner_black_wins_with_ace_as_pair() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7H JC KD AH AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2D 3H 5C 9S KH").build();

        assertEquals(
                ResultHelper.aPairWinningResult(blackHand, CardNumber.ACE, false),
                buildPairCardResult(blackHand, whiteHand));
    }

    @Test
    public void
    it_should_return_a_matching_result_when_having_a_winner_black_wins_with_jack() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7H JH JC KD AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2D 3H 5C 9S KH").build();

        assertEquals(
                ResultHelper.aPairWinningResult(blackHand, CardNumber.JACK, false),
                buildPairCardResult(blackHand, whiteHand));
    }

    @Test
    public void
    it_should_return_a_matching_result_when_having_a_winner_black_wins_4() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("5D 6C 7H JH AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2D 4H 4C 9S KH").build();

        assertEquals(
                ResultHelper.aPairWinningResult(whiteHand, CardNumber.FOUR, false),
                buildPairCardResult(blackHand, whiteHand));
    }

    @Test
    public void
    it_should_return_a_matching_result_when_having_a_winner_black_wins_with_ace() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("2H 2D 5S 6C AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("4D 9S KH AH AC").build();

        assertEquals(
                ResultHelper.aPairWinningResult(whiteHand, CardNumber.ACE, true),
                buildPairCardResult(blackHand, whiteHand));
    }
}