package com.murex.ranking;

import com.murex.Hand;
import org.junit.jupiter.api.Test;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PairCardRankingTest {

    private static void assertNoMatchingResult(String blackCards, String whiteCards) {
        PairCardRanking pairCardRanking = buildPairCardRanking(blackCards, whiteCards);

        assertEquals(aNoMatchResult(), pairCardRanking.getMatchingResult());
    }

    private static void assertMatchingResult(String blackCards, String whiteCards, String message) {
        PairCardRanking pairCardRanking = buildPairCardRanking(blackCards, whiteCards);

        assertEquals(aMatchResult(message), pairCardRanking.getMatchingResult());
    }

    private static PairCardRanking buildPairCardRanking(String blackCards, String whiteCards) {
        Hand blackHand = Hand.buildFrom("Black", blackCards);
        Hand whiteHand = Hand.buildFrom("White", whiteCards);
        return new PairCardRanking(blackHand, whiteHand);
    }

    @Test
    public void
    it_should_return_a_non_matching_result_when_no_pair_is_found() {
        assertNoMatchingResult("7H 8C TD KH AS", "2D 3H 5C 9S KH");
    }

    @Test
    public void
    it_should_return_a_non_matching_result_when_having_all_tie_with_pairs() {
        assertNoMatchingResult("7H 7C TD KH AS", "7D 7S TC KS AH");
    }

    @Test
    public void
    it_should_return_a_non_matching_result_when_having_pairs_tied_as_2() {
        assertNoMatchingResult(
                "2H 2D 5S 6C JS",
                "2C 2S 6H 8H AC");
    }

    @Test
    public void
    it_should_return_a_non_matching_result_when_having_pairs_tied_as_ace() {
        assertNoMatchingResult(
                "2H 4D 8S AC AS",
                "2C 3S 7H AH AD");
    }

    @Test
    public void
    it_should_return_a_matching_result_when_having_a_winner_black_wins_with_ace_as_pair() {
        assertMatchingResult(
                "7H JC KD AH AS",
                "2D 3H 5C 9S KH",
                "Black wins. - with Pair cards: ACE");
    }

    @Test
    public void
    it_should_return_a_matching_result_when_having_a_winner_black_wins_with_jack() {
        assertMatchingResult(
                "7H JH JC KD AS",
                "2D 3H 5C 9S KH",
                "Black wins. - with Pair cards: JACK");
    }

    @Test
    public void
    it_should_return_a_matching_result_when_having_a_winner_black_wins_4() {
        assertMatchingResult(
                "5D 6C 7H JH AS",
                "2D 4H 4C 9S KH",
                "White wins. - with Pair cards: FOUR");
    }

    @Test
    public void
    it_should_return_a_matching_result_when_having_a_winner_black_wins_with_ace() {
        assertMatchingResult(
                "2H 2D 5S 6C AS",
                "4D 9S KH AH AC",
                "White wins. - with Pair cards: ACE");
    }
}