package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoPairCardRankingTest {

    @Test
    public void it_should_return_a_matching_result_when_the_black_player_has_two_pairs() {
        Hand blackHand = Hand.buildFrom("Black", "7H 7C TD TH AS");
        Hand whiteHand = Hand.buildFrom("White", "1H 4C 5H 7D AD");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        assertEquals(Result.aMatchResult("Black wins. - with two pairs: 7 and Ten"), twoPairCardRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_when_the_white_player_has_two_pairs() {
        Hand blackHand = Hand.buildFrom("Black", "2H 7C 8D TH AS");
        Hand whiteHand = Hand.buildFrom("White", "1H 1C 6H 7D 7S");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        assertEquals(Result.aMatchResult("White wins. - with two pairs: 1 and 7"), twoPairCardRanking.getMatchingResult());
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_white_wins() {
        Hand blackHand = Hand.buildFrom("Black", "2H 2C 8D 8H AS");
        Hand whiteHand = Hand.buildFrom("White", "3H 3C 9H 9D TS");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        assertEquals(Result.aMatchResult("White wins. - with two pairs: 3 and 9"), twoPairCardRanking.getMatchingResult());
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_black_wins() {
        Hand blackHand = Hand.buildFrom("Black", "2H 2C TD TH AS");
        Hand whiteHand = Hand.buildFrom("White", "3H 3C 9H 9D TS");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        assertEquals(Result.aMatchResult("Black wins. - with two pairs: 2 and Ten"), twoPairCardRanking.getMatchingResult());
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_black_wins_higher_cards_are_equal() {
        Hand blackHand = Hand.buildFrom("Black", "1H 1C 4D 4H AS");
        Hand whiteHand = Hand.buildFrom("White", "2D 2S 4C 4S AH");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        assertEquals(Result.aMatchResult("White wins. - with two pairs: 2 and 4"), twoPairCardRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_no_matching_result_when_only_one_pair_exists() {
        Hand blackHand = Hand.buildFrom("Black", "2H 7C 8D TH AS");
        Hand whiteHand = Hand.buildFrom("White", "1H 1C 6H 7D 9S");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        assertEquals(Result.aNoMatchResult(), twoPairCardRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_no_matching_result_when_none_have_two_pairs() {
        Hand blackHand = Hand.buildFrom("Black", "1H 7C 8D TH AS");
        Hand whiteHand = Hand.buildFrom("White", "2H 3C 6H 7D 8S");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        assertEquals(Result.aNoMatchResult(), twoPairCardRanking.getMatchingResult());
    }


    @Disabled
    @Test
    public void it_should_return_a_no_matching_result_when_two_hands_have_same_card_values() {
        Hand blackHand = Hand.buildFrom("Black", "1H 1C 4D 4H AS");
        Hand whiteHand = Hand.buildFrom("White", "1D 1S 4C 4S AH");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        assertEquals(Result.aNoMatchResult(), twoPairCardRanking.getMatchingResult());
    }
}