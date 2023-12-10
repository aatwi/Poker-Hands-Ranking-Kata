package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Test;

import static com.murex.Hand.buildFrom;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoPairCardRankingTest {

    @Test
    public void it_should_return_a_matching_result_when_the_black_player_has_two_pairs() {
        assertMatchingResult("7H 7C TD TH AS", "1H 4C 5H 7D AD", "Black wins. - with two pairs: 7 and Ten");
    }

    private static void assertMatchingResult(String blackHand, String whiteHand, String message) {
        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(buildFrom("Black", blackHand), buildFrom("White", whiteHand));
        assertResult(Result.aMatchResult(message), twoPairCardRanking);
    }

    private static void assertResult(Result expectedMessage, TwoPairCardRanking twoPairCardRanking) {
        assertEquals(expectedMessage, twoPairCardRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_when_the_white_player_has_two_pairs() {
        assertMatchingResult("2H 7C 8D TH AS", "1H 1C 6H 7D 7S", "White wins. - with two pairs: 1 and 7");
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_white_wins() {
        assertMatchingResult("2H 2C 8D 8H AS", "3H 3C 9H 9D TS", "White wins. - with two pairs: 3 and 9");
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_black_wins() {
        assertMatchingResult("2H 2C TD TH AS", "3H 3C 9H 9D TS", "Black wins. - with two pairs: 2 and Ten");
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_black_wins_higher_cards_are_equal() {
        assertMatchingResult("1H 1C 4D 4H AS", "2D 2S 4C 4S AH", "White wins. - with two pairs: 2 and 4");
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_black_wins_higher_cards_are_equal_option2() {
        assertMatchingResult("2H 2C 4D 4H AS", "1D 1S 4C 4S AH", "Black wins. - with two pairs: 2 and 4");
    }

    @Test
    public void it_should_return_a_no_matching_result_when_only_one_pair_exists() {
        Hand blackHand = buildFrom("Black", "2H 7C 8D TH AS");
        Hand whiteHand = buildFrom("White", "1H 1C 6H 7D 9S");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        assertResult(Result.aNoMatchResult(), twoPairCardRanking);
    }

    @Test
    public void it_should_return_a_no_matching_result_when_none_have_two_pairs() {
        Hand blackHand = buildFrom("Black", "1H 7C 8D TH AS");
        Hand whiteHand = buildFrom("White", "2H 3C 6H 7D 8S");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        assertResult(Result.aNoMatchResult(), twoPairCardRanking);
    }


    @Test
    public void it_should_return_a_no_matching_result_when_two_hands_have_same_card_values() {
        Hand blackHand = buildFrom("Black", "1H 1C 4D 4H AS");
        Hand whiteHand = buildFrom("White", "1D 1S 4C 4S AH");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        assertResult(Result.aNoMatchResult(), twoPairCardRanking);
    }
}