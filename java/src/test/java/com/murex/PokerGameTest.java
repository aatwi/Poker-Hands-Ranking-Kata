/*
Copyright (c) 2023 Murex

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package com.murex;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerGameTest {

    private static void assertWinner(String black, String white, String expected) {
        assertEquals(expected, new PokerGame(black, white).getWinner());
    }

    @Test
    public void HIGH_CARD_white_wins_with_Ace() {
        assertWinner("2H 3D 5S 9C KD", "2C 3H 4S 8C AH", "White wins. - with high card: Ace");
    }

    @Test
    public void HIGH_CARD_black_wins_with_Queen() {
        assertWinner("2H 3D 5S 9C QD", "2C 3H 4S 8C JH", "Black wins. - with high card: Queen");
    }

    @Test
    public void HIGH_CARD_white_wins_Jack() {
        assertWinner("2H 3D 5S 7C 9D", "2C 3H 4S 8C JH", "White wins. - with high card: Jack");
    }

    @Test
    public void HIGH_CARD_black_wins_with_9() {
        assertWinner("2H 3D 5S 8C 9D", "2C 3H 4S 7C 8H", "Black wins. - with high card: 9");
    }

    @Test
    public void HIGH_CARD_black_wins_with_7() {
        assertWinner("2H 3D 5S 8C 9D", "2C 3H 4S 7C 9H", "Black wins. - with high card: 8");
    }

    @Test
    public void TIE_between_two_hands() {
        assertWinner("2H 3D 5S 9C KD", "2D 3H 5C 9S KH", "Tie.");
    }

    @Test
    public void PAIR_black_wins_with_ace_as_pair() {
        assertWinner("7H JC KD AH AS", "2D 3H 5C 9S KH", "Black wins. - with Pair cards: Ace");
    }

    @Test
    public void PAIR_black_wins_with_jack_as_pair() {
        assertWinner("7H JH JC KD AS", "2D 3H 5C 9S KH", "Black wins. - with Pair cards: Jack");
    }

    @Test
    public void PAIR_white_wins_with_4_as_pair() {
        assertWinner("5D 6C 7H JH AS", "2D 4H 4C 9S KH", "White wins. - with Pair cards: 4");
    }

    @Test
    public void PAIR_white_wins_with_ace_over_a_pair_of_2() {
        assertWinner("2H 2D 5S 6C AS", "4D 9S KH AH AC", "White wins. - with Pair cards: Ace");
    }

    @Test
    public void PAIR_white_wins_with_ace_over_a_pair_of_ace_due_to_other_rank() {
        assertWinner("2H 2D 5S 6C JS", "2C 2S 6H 8H AC", "White wins. - with high card: Ace");
    }

    @Test
    public void PAIR_black_wins_with_king_over_a_pair_of_ace_due_to_higher_rank() {
        assertWinner("2H 2D 5S 6C KS", "2C 2S 6H 8H JC", "Black wins. - with high card: King");
    }

    @Test
    public void TWO_PAIR_black_wins_with_two_pairs() {
        assertWinner("2H 2D 5S 5C KS", "2C 3S 6H 8H JC", "Black wins. - with two pairs: 2 and 5");
    }

    @Test
    public void THREE_OF_A_KIND_black_wins_having_three_cards_of_one_kind() {
        assertWinner("2H 2D 2S 5C KS", "2C 3S 6H 8H JC", "Black wins. - with three of a kind: 2");
    }

    @Test
    public void THREE_OF_A_KIND_white_wins_having_three_cards_of_one_kind() {
        assertWinner("2H 2S 4D 5C KS", "2C 3S 6H 6D 6C", "White wins. - with three of a kind: 6");
    }

    @Test
    public void THREE_OF_A_KIND_black_wins_having_three_cards_of_one_kind_and_higher_card() {
        assertWinner("2H 5D 5S 5C KS", "2C 3S JH JD JC", "White wins. - with three of a kind: Jack");
    }

    @Test
    public void STRAIGHT_black_wins_with_a_straight_hand() {
        assertWinner("2H 3D 4S 5C 6S", "2C 3S 6H 8H JC", "Black wins. - with straight cards");
    }

    @Test
    public void STRAIGHT_white_wins_with_a_straight_hand() {
        assertWinner("2H 2S 4D 5C KS", "7C 8S 9H TD JC", "White wins. - with straight cards");
    }

    @Test
    public void STRAIGHT_white_wins_with_a_straight_and_higher_cards() {
        assertWinner("2H 3D 4S 5C 6S", "6C 7S 8H 9D TC", "White wins. - with straight cards and higher cards");
    }

    @Test
    public void FULL_HOUSE_white_wins_with_a_full_house_cards() {
        assertWinner("6C 7S 8H 9D TC", "3H 3S AC AD AH", "White wins. - with full house");
    }

    @Test
    public void FLUSH_black_wins_with_a_full_house_cards() {
        assertWinner("2H 2S 2D JH JS", "6C 7S 8H 9D TC", "Black wins. - with full house");
    }

    @Test
    public void FLUSH_white_wins_with_a_full_house_and_higher_cards() {
        assertWinner("2C 2S 2H AH AD", "3D 3S 3C TD TS", "White wins. - with full house and higher hand");
    }

    @Test
    public void FOUR_OF_A_KINDS_white_wins() {
        assertWinner("2C 3S 8H TH AD", "9D 9S 9C 9H TS", "White wins. - with four of a kind");
    }

    @Test
    public void FOUR_OF_A_KINDS_black_wins() {
        assertWinner("8D TS TC TH TD", "2C 3S 8H TH AD", "Black wins. - with four of a kind");
    }

    @Test
    public void FOUR_OF_A_KINDS_black_wins_with_higher_cards() {
        assertWinner("8D TS TC TH TD", "2C 4S 4H 4C 4D", "Black wins. - with four of a kind and higher hand");
    }

    @Test
    public void FOUR_OF_A_KINDS_white_wins_with_higher_cards() {
        assertWinner("8D TS TC TH TD", "JC JS JH JD AD", "White wins. - with four of a kind and higher hand");
    }

    @Disabled
    @Test
    public void STRAIGHT_FLUSH_black_wins() {
        assertWinner("8S 9S TS JS QS", "JC JS JH JD AD", "Black wins. - with straight flush");
    }

    @Disabled
    @Test
    public void STRAIGHT_FLUSH_white_wins() {
        assertWinner("JC JS JH JD AD", "2H 3H 4H 5H 6H", "White wins. - with straight flush");
    }
}
