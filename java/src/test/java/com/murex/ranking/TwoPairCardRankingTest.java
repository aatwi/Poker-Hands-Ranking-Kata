package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Test;

import static com.murex.Hand.buildFrom;
import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoPairCardRankingTest {

    private static void assertMatchingResult(String blackHand, String whiteHand, String message) {
        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(buildFrom("Black", blackHand), buildFrom("White", whiteHand));

        assertEquals(aMatchResult(message), twoPairCardRanking.getMatchingResult());
    }

    private static void assertNoMatchingResult(String blackHand, String whiteHand) {
        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(buildFrom("Black", blackHand), buildFrom("White", whiteHand));

        assertEquals(aNoMatchResult(), twoPairCardRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_when_the_black_player_has_two_pairs() {
        assertMatchingResult("7H 7C TD TH AS", "2H 4C 5H 7D AD", "Black wins. - with two pairs: SEVEN and TEN");
    }

    @Test
    public void it_should_return_a_matching_result_when_the_white_player_has_two_pairs() {
        assertMatchingResult("2H 7C 8D TH AS", "3H 3C 6H 7D 7S", "White wins. - with two pairs: THREE and SEVEN");
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_white_wins() {
        assertMatchingResult("2H 2C 8D 8H AS", "3H 3C 9H 9D TS", "White wins. - with two pairs: THREE and NINE");
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_black_wins() {
        assertMatchingResult("2H 2C TD TH AS", "3H 3C 9H 9D TS", "Black wins. - with two pairs: TWO and TEN");
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_black_wins_higher_cards_are_equal() {
        assertMatchingResult("2H 2C 4D 4H AS", "3D 3S 4C 4S AH", "White wins. - with two pairs: THREE and FOUR");
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_black_wins_higher_cards_are_equal_option2() {
        assertMatchingResult("3H 3C 4D 4H AS", "2D 2S 4C 4S AH", "Black wins. - with two pairs: THREE and FOUR");
    }

    @Test
    public void it_should_return_a_no_matching_result_when_only_one_pair_exists() {
        assertNoMatchingResult("2H 7C 8D TH AS", "3H 3C 6H 7D 9S");
    }

    @Test
    public void it_should_return_a_no_matching_result_when_none_have_two_pairs() {
        assertNoMatchingResult("2D 7C 8D TH AS", "2H 3C 6H 7D 8S");
    }


    @Test
    public void it_should_return_a_no_matching_result_when_two_hands_have_same_card_values() {
        assertNoMatchingResult("2H 2C 4D 4H AS", "2D 2S 4C 4S AH");
    }
}