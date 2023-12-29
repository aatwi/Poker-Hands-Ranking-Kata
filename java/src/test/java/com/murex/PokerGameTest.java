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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerGameTest {

    private static void assertWinner(String black, String white, String expected) {
        assertEquals(expected, new PokerGame(black, white).getWinner());
    }

    @Test
    public void TIE_between_two_hands() {
        assertWinner("2H 3D 5S 9C KD", "2D 3H 5C 9S KH", "Tie.");
    }

    @Nested
    @DisplayName("High Card")
    class HighCard {
        @Test
        void white_wins() {
            assertWinner("2H 2D 5S 6C JS", "2C 2S 6H 8H AC", "White wins. - with high card: Ace");
        }

        @Test
        void black_wins() {
            assertWinner("2H 2D 5S 6C KS", "2C 2S 6H 8H JC", "Black wins. - with high card: King");
        }
    }

    @Nested
    @DisplayName("Pair")
    class Pair {
        @Test
        void black_wins() {
            assertWinner("7H JC KD AH AS", "2D 3H 5C 9S KH", "Black wins. - with Pair cards: Ace");
        }

        @Test
        void white_wins() {
            assertWinner("5D 6C 7H JH AS", "2D 4H 4C 9S KH", "White wins. - with Pair cards: 4");
        }

        @Test
        void black_wins_with_higher_pair() {
            assertWinner("7H JC KD AH AS", "2D 3H 5C KS KH", "Black wins. - with Pair cards: Ace");
        }

        @Test
        void white_wins_with_higher_pair() {
            assertWinner("7H 8C TD QH QS", "2D 3H 5C KS KH", "White wins. - with Pair cards: King");
        }

        @Test
        void white_wins_with_high_card() {
            assertWinner("7H 8C KD KC AS", "2D 3H 5C KS KH", "Black wins. - with high card: Ace");
        }
    }

    @Nested
    @DisplayName("Two Pairs")
    class TwoPairs {
        @Test
        void black_wins() {
            assertWinner("2H 2D 5S 5C KS", "2C 3S 6H 8H JC", "Black wins. - with two pairs: 2 and 5");
        }

        @Test
        void white_wins() {
            assertWinner("2H 4D 5S 5C KS", "2C 6S 6H 8H 8C", "White wins. - with two pairs: 6 and 8");
        }

        @Test
        void white_wins_with_higher_two_pairs() {
            assertWinner("6C 6D 5S 5C KS", "6S 6H 7H 7D AC", "White wins. - with two pairs: 6 and 7");
        }

        @Test
        void black_wins_with_higher_two_pairs() {
            assertWinner("6S 6H QH QD AC", "6C 6D 5S 5C KS", "Black wins. - with two pairs: 6 and Queen");
        }

        @Test
        void black_wins_with_high_card() {
            assertWinner("6S 6H QH QD AC", "6C 6D QS QC KS", "Black wins. - with high card: Ace");
        }
    }

    @Nested
    @DisplayName("Three Of A Kind")
    class ThreeOfAKind {
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
    }

    @Nested
    @DisplayName("STRAIGHT")
    class Straight {
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
    }

    @Nested
    @DisplayName("Flush")
    class Flush {
        @Test
        public void black_wins() {
            assertWinner("2H 5H 8H JH KH", "2C 3S 6H 8S JC", "Black wins. - with flush");
        }

        @Test
        public void white_wins() {
            assertWinner("2C 3S 6H 8S JC", "2D 5D 8D JD KD", "White wins. - with flush");
        }

        @Test
        public void white_wins_with_higher_cards() {
            assertWinner("2C 3C 6C 8C JC", "2D 5D 8D JD KD", "White wins. - with flush and higher hand");
        }

        @Test
        public void black_wins_with_higher_cards() {
            assertWinner("2C 3C 6C 8C AC", "2D 5D 8D JD KD", "Black wins. - with flush and higher hand");
        }
    }

    @Nested
    @DisplayName("Full House")
    class FullHouse {
        @Test
        public void white_wins() {
            assertWinner("6C 7S 8H 9D TC", "3H 3S AC AD AH", "White wins. - with full house");
        }

        @Test
        public void black_wins() {
            assertWinner("2H 2S 2D JH JS", "6C 7S 8H 9D TC", "Black wins. - with full house");
        }

        @Test
        public void white_wins_with_a_full_house_and_higher_cards() {
            assertWinner("2C 2S 2H AH AD", "3D 3S 3C TD TS", "White wins. - with full house and higher hand");
        }
    }


    @Nested
    @DisplayName("Four of a Kind")
    class FourOfAKind {
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
    }


    @Nested
    @DisplayName("Straight Flush")
    class StraightFlush {
        @Test
        public void STRAIGHT_FLUSH_black_wins() {
            assertWinner("8S 9S TS JS QS", "JC JS JH JD AD", "Black wins. - with straight flush");
        }

        @Test
        public void STRAIGHT_FLUSH_white_wins() {
            assertWinner("JC JS JH JD AD", "2H 3H 4H 5H 6H", "White wins. - with straight flush");
        }

        @Test
        public void STRAIGHT_FLUSH_black_wins_and_higher_hand() {
            assertWinner("8D 9D TD JD QD", "2H 3H 4H 5H 6H", "Black wins. - with straight flush and higher hand");
        }

        @Test
        public void STRAIGHT_FLUSH_white_wins_and_higher_hand() {
            assertWinner("2H 3H 4H 5H 6H", "8D 9D TD JD QD", "White wins. - with straight flush and higher hand");
        }

        @Test
        public void STRAIGHT_FLUSH_tie() {
            assertWinner("2H 3H 4H 5H 6H", "2D 3D 4D 5D 6D", "Tie.");
        }
    }

    @Nested
    @DisplayName("Royal Flush")
    class RoyalFlush {
        @Test
        public void ROYAL_FLUSH_white_wins() {
            assertWinner("2H 3H 4H 5H 6H", "TD JD QD KD AD", "White wins. - with royal flush");
        }

        @Test
        public void ROYAL_FLUSH_black_wins() {
            assertWinner("TH JH QH KH AH", "2H 3H 4H 5H 6H", "Black wins. - with royal flush");
        }

        @Test
        public void ROYAL_FLUSH_its_a_tie_when_both_have_royal_flush() {
            assertWinner("TH JH QH KH AH", "TD JD QD KD AD", "Tie.");
        }
    }
}
