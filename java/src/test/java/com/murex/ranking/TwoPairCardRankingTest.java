package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Test;

import static com.murex.CardNumber.*;
import static com.murex.CardNumber.NINE;
import static com.murex.Hand.buildFrom;
import static com.murex.ResultHelper.aNoMatchResult;
import static com.murex.ResultHelper.aTwoPairWinningResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoPairCardRankingTest {

    @Test
    public void it_should_return_a_matching_result_when_the_black_player_has_two_pairs() {
        Hand blackHand = buildFrom("Black", "7H 7C TD TH AS");
        Hand whiteHand = buildFrom("White", "2H 4C 5H 7D AD");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.getMatchingResult();

        assertEquals(aTwoPairWinningResult(blackHand, SEVEN, TEN, false), matchingResult);
    }

    @Test
    public void it_should_return_a_matching_result_when_the_white_player_has_two_pairs() {
        Hand blackHand = buildFrom("Black", "2H 7C 8D TH AS");
        Hand whiteHand = buildFrom("White", "3H 3C 6H 7D 7S");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.getMatchingResult();

        assertEquals(aTwoPairWinningResult(whiteHand, THREE, SEVEN, false), matchingResult);
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_white_wins() {
        Hand blackHand = buildFrom("Black", "2H 2C 8D 8H AS");
        Hand whiteHand = buildFrom("White", "3H 3C 9H 9D TS");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.getMatchingResult();

        assertEquals(aTwoPairWinningResult(whiteHand, THREE, NINE, true), matchingResult);
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_black_wins() {
        Hand blackHand = buildFrom("Black", "2H 2C TD TH AS");
        Hand whiteHand = buildFrom("White", "3H 3C 9H 9D TS");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.getMatchingResult();

        assertEquals(aTwoPairWinningResult(blackHand, TWO, TEN, true), matchingResult);
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_black_wins_higher_cards_are_equal() {
        Hand blackHand = buildFrom("Black", "2H 2C 4D 4H AS");
        Hand whiteHand = buildFrom("White", "3D 3S 4C 4S AH");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.getMatchingResult();

        assertEquals(aTwoPairWinningResult(whiteHand, THREE, FOUR, true), matchingResult);
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_black_wins_higher_cards_are_equal_option2() {
        Hand blackHand = buildFrom("Black", "3H 3C 4D 4H AS");
        Hand whiteHand = buildFrom("White", "2D 2S 4C 4S AH");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.getMatchingResult();

        assertEquals(aTwoPairWinningResult(blackHand, THREE, FOUR, true), matchingResult);
    }

    @Test
    public void it_should_return_a_no_matching_result_when_only_one_pair_exists() {
        Hand blackHand = buildFrom("Black", "2H 7C 8D TH AS");
        Hand whiteHand = buildFrom("White", "3H 3C 6H 7D 9S");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.getMatchingResult();

        assertEquals(aNoMatchResult(), matchingResult);
    }

    @Test
    public void it_should_return_a_no_matching_result_when_none_have_two_pairs() {
        Hand blackHand = buildFrom("Black", "2D 7C 8D TH AS");
        Hand whiteHand = buildFrom("White", "2H 3C 6H 7D 8S");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.getMatchingResult();

        assertEquals(aNoMatchResult(), matchingResult);
    }


    @Test
    public void it_should_return_a_no_matching_result_when_two_hands_have_same_card_values() {
        Hand blackHand = buildFrom("Black", "2H 2C 4D 4H AS");
        Hand whiteHand = buildFrom("White", "2D 2S 4C 4S AH");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.getMatchingResult();

        assertEquals(aNoMatchResult(), matchingResult);
    }
}