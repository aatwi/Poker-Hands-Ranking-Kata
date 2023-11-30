package com.murex.ranking;

import com.murex.Hand;
import org.junit.jupiter.api.Test;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PairCardRankingTest {

    @Test
    public void
    it_should_return_a_no_matching_result_when_no_pair_is_found() {
        Hand blackHand = Hand.buildFrom("Black", "7H 8C TD KH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 5C 9S KH");
        PairCardRanking pairCardRanking = new PairCardRanking(blackHand, whiteHand);

        assertEquals(aNoMatchResult(), pairCardRanking.getMatchingResult());
    }

    @Test
    public void
    PAIR_black_wins_with_ace_as_pair() {
        Hand blackHand = Hand.buildFrom("Black", "7H JC KD AH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 5C 9S KH");
        PairCardRanking pairCardRanking = new PairCardRanking(blackHand, whiteHand);

        assertEquals(aMatchResult("Black wins. - with Pair cards: Ace"), pairCardRanking.getMatchingResult());
    }

    @Test
    public void
    PAIR_black_wins_with_jack_as_pair() {
        Hand blackHand = Hand.buildFrom("Black", "7H JH JC KD AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 3H 5C 9S KH");
        PairCardRanking pairCardRanking = new PairCardRanking(blackHand, whiteHand);

        assertEquals(aMatchResult("Black wins. - with Pair cards: Jack"), pairCardRanking.getMatchingResult());
    }

    @Test
    public void
    PAIR_white_wins_with_4_as_pair() {
        Hand blackHand = Hand.buildFrom("Black", "5D 6C 7H JH AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 4H 4C 9S KH");
        PairCardRanking pairCardRanking = new PairCardRanking(blackHand, whiteHand);

        assertEquals(aMatchResult("White wins. - with Pair cards: 4"), pairCardRanking.getMatchingResult());
    }

    @Test
    public void
    PAIR_white_wins_with_ace_over_a_pair_of_2() {
        Hand blackHand = Hand.buildFrom("Black", "2H 2D 5S 6C AS");
        Hand whiteHand = Hand.buildFrom("White", "4D 9S KH AH AC");
        PairCardRanking pairCardRanking = new PairCardRanking(blackHand, whiteHand);

        assertEquals(aMatchResult("White wins. - with Pair cards: Ace"), pairCardRanking.getMatchingResult());
    }

    @Test
    public void
    PAIR_white_wins_with_ace_over_a_pair_of_ace_due_to_other_rank() {
        Hand blackHand = Hand.buildFrom("Black", "2H 2D 5S 6C JS");
        Hand whiteHand = Hand.buildFrom("White", "2C 2S 6H 8H AC");
        PairCardRanking pairCardRanking = new PairCardRanking(blackHand, whiteHand);

        assertEquals(aMatchResult("White wins. - with Pair cards and higher rank: 2 and Ace"), pairCardRanking.getMatchingResult());
    }

    @Test
    public void
    PAIR_black_wins_with_ace_over_a_pair_of_ace_due_to_other_rank() {
        Hand blackHand = Hand.buildFrom("Black", "2H 2D 5S 6C KS");
        Hand whiteHand = Hand.buildFrom("White", "2C 2S 6H 8H JC");
        PairCardRanking pairCardRanking = new PairCardRanking(blackHand, whiteHand);

        assertEquals(aMatchResult("Black wins. - with Pair cards and higher rank: 2 and King"), pairCardRanking.getMatchingResult());
    }

}