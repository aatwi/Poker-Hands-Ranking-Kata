package com.murex.ranking;

import com.murex.CardNumber;
import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Test;

import static com.murex.Result.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PairCardRankingTest {

    private static Result buildPairCardResult(Hand blackHand, Hand whiteHand) {
        PairCardRanking pairCardRanking = new PairCardRanking(blackHand, whiteHand);
        return pairCardRanking.getMatchingResult();
    }

    @Test
    public void
    it_should_return_a_non_matching_result_when_no_pair_is_found() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 5C 9S KH");

        assertEquals(aNoMatchResult(), buildPairCardResult(blackHand, whiteHand));
    }

    @Test
    public void
    it_should_return_a_non_matching_result_when_having_all_tie_with_pairs() {
        Hand blackHand = Hand.buildFrom("Black", "7H 7C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White", "7D 7S TC KS AH");

        assertEquals(aNoMatchResult(), buildPairCardResult(blackHand, whiteHand));
    }

    @Test
    public void
    it_should_return_a_non_matching_result_when_having_pairs_tied_as_2() {
        Hand blackHand = Hand.buildFrom("Black", "2H 2D 5S 6C JS");
        Hand whiteHand = Hand.buildFrom("White", "2C 2S 6H 8H AC");

        assertEquals(aNoMatchResult(), buildPairCardResult(blackHand, whiteHand));
    }

    @Test
    public void
    it_should_return_a_non_matching_result_when_having_pairs_tied_as_ace() {
        Hand blackHand = Hand.buildFrom("Black", "2H 4D 8S AC AS");
        Hand whiteHand = Hand.buildFrom("White", "2C 3S 7H AH AD");

        assertEquals(aNoMatchResult(), buildPairCardResult(blackHand, whiteHand));
    }

    @Test
    public void
    it_should_return_a_matching_result_when_having_a_winner_black_wins_with_ace_as_pair() {
        Hand blackHand = Hand.buildFrom("Black", "7H JC KD AH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 5C 9S KH");

        assertEquals(
                aPairWinningResult(blackHand, CardNumber.ACE),
                buildPairCardResult(blackHand, whiteHand));
    }

    @Test
    public void
    it_should_return_a_matching_result_when_having_a_winner_black_wins_with_jack() {
        Hand blackHand = Hand.buildFrom("Black", "7H JH JC KD AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 5C 9S KH");

        assertEquals(
                aPairWinningResult(blackHand, CardNumber.JACK),
                buildPairCardResult(blackHand, whiteHand));
    }

    @Test
    public void
    it_should_return_a_matching_result_when_having_a_winner_black_wins_4() {
        Hand blackHand = Hand.buildFrom("Black", "5D 6C 7H JH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 4H 4C 9S KH");

        assertEquals(
                aPairWinningResult(whiteHand, CardNumber.FOUR),
                buildPairCardResult(blackHand, whiteHand));
    }

    @Test
    public void
    it_should_return_a_matching_result_when_having_a_winner_black_wins_with_ace() {
        Hand blackHand = Hand.buildFrom("Black", "2H 2D 5S 6C AS");
        Hand whiteHand = Hand.buildFrom("White", "4D 9S KH AH AC");

        assertEquals(
                aPairWinningResult(whiteHand, CardNumber.ACE),
                buildPairCardResult(blackHand, whiteHand));
    }
}