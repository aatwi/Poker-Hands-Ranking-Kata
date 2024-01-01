package com.murex.ranking;

import com.murex.Hand;
import com.murex.HandBuilder;
import com.murex.Result;
import org.junit.jupiter.api.Test;

import static com.murex.CardNumber.*;
import static com.murex.CardNumber.NINE;
import static com.murex.ResultHelper.aNoWinner;
import static com.murex.ResultHelper.aTwoPairWinningResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoPairCardRankingTest {

    @Test
    public void it_should_return_a_matching_result_when_the_black_player_has_two_pairs() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("7H 7C TD TH AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2H 4C 5H 7D AD").build();

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.evaluate();

        assertEquals(aTwoPairWinningResult(blackHand, SEVEN, TEN, false), matchingResult);
    }

    @Test
    public void it_should_return_a_matching_result_when_the_white_player_has_two_pairs() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("2H 7C 8D TH AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("3H 3C 6H 7D 7S").build();

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.evaluate();

        assertEquals(aTwoPairWinningResult(whiteHand, THREE, SEVEN, false), matchingResult);
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_white_wins() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("2H 2C 8D 8H AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("3H 3C 9H 9D TS").build();

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.evaluate();

        assertEquals(aTwoPairWinningResult(whiteHand, THREE, NINE, true), matchingResult);
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_black_wins() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("2H 2C TD TH AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("3H 3C 9H 9D TS").build();

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.evaluate();

        assertEquals(aTwoPairWinningResult(blackHand, TWO, TEN, true), matchingResult);
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_black_wins_higher_cards_are_equal() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("2H 2C 4D 4H AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("3D 3S 4C 4S AH").build();

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.evaluate();

        assertEquals(aTwoPairWinningResult(whiteHand, THREE, FOUR, true), matchingResult);
    }

    @Test
    public void when_both_hands_has_two_pairs_the_higher_hand_wins_black_wins_higher_cards_are_equal_option2() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("3H 3C 4D 4H AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2D 2S 4C 4S AH").build();

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.evaluate();

        assertEquals(aTwoPairWinningResult(blackHand, THREE, FOUR, true), matchingResult);
    }

    @Test
    public void it_should_return_a_no_matching_result_when_only_one_pair_exists() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("2H 7C 8D TH AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("3H 3C 6H 7D 9S").build();

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.evaluate();

        assertEquals(aNoWinner(), matchingResult);
    }

    @Test
    public void it_should_return_a_no_matching_result_when_none_have_two_pairs() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("2D 7C 8D TH AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2H 3C 6H 7D 8S").build();

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.evaluate();

        assertEquals(aNoWinner(), matchingResult);
    }


    @Test
    public void it_should_return_a_no_matching_result_when_two_hands_have_same_card_values() {
        Hand blackHand = HandBuilder.aHand().withPlayer("Black").withCards("2H 2C 4D 4H AS").build();
        Hand whiteHand = HandBuilder.aHand().withPlayer("White").withCards("2D 2S 4C 4S AH").build();

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Result matchingResult = twoPairCardRanking.evaluate();

        assertEquals(aNoWinner(), matchingResult);
    }
}