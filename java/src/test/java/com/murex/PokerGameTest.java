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

    private static final String FIRST_PLAYER = "PLAYER A";
    private static final String SECOND_PLAYER = "PLAYER B";

    private static void assertWinner(String player1Cards, String player2Cards, String expected) {
        assertEquals(expected, new PokerGame(FIRST_PLAYER, player1Cards, SECOND_PLAYER, player2Cards).getResult());
    }

    @Test
    public void TIE_between_two_hands() {
        assertWinner("2H 3D 5S 9C KD", "2D 3H 5C 9S KH", "Tie.");
    }

    @Nested
    @DisplayName("HIGH CARD")
    class HighCard {
        @Test
        void PLAYER_B_wins() {
            assertWinner("2H 2D 5S 6C JS", "2C 2S 6H 8H AC", "Player \"" + SECOND_PLAYER + "\" wins with a HIGH CARD hand (ACE)");
        }

        @Test
        void PLAYER_A_wins() {
            assertWinner("2H 2D 5S 6C KS", "2C 2S 6H 8H JC", "Player \"" + FIRST_PLAYER + "\" wins with a HIGH CARD hand (KING)");
        }
    }

    @Nested
    @DisplayName("PAIR")
    class Pair {
        @Test
        void PLAYER_A_wins() {
            assertWinner("7H JC KD AH AS", "2D 3H 5C 9S KH", "Player \"" + FIRST_PLAYER + "\" wins with a PAIR hand (ACE)");
        }

        @Test
        void PLAYER_B_wins() {
            assertWinner("5D 6C 7H JH AS", "2D 4H 4C 9S KH", "Player \"" + SECOND_PLAYER + "\" wins with a PAIR hand (FOUR)");
        }

        @Test
        void PLAYER_A_wins_with_higher_pair() {
            assertWinner("7H JC KD AH AS", "2D 3H 5C KS KH", "Player \"" + FIRST_PLAYER + "\" wins with a PAIR hand (ACE) and higher cards");
        }

        @Test
        void PLAYER_B_wins_with_higher_pair() {
            assertWinner("7H 8C TD QH QS", "2D 3H 5C KS KH", "Player \"" + SECOND_PLAYER + "\" wins with a PAIR hand (KING) and higher cards");
        }

        @Test
        void PLAYER_A_wins_with_high_card() {
            assertWinner("7H 8C KD KC AS", "2D 3H 5C KS KH", "Player \"" + FIRST_PLAYER + "\" wins with a HIGH CARD hand (ACE)");
        }
    }

    @Nested
    @DisplayName("TWO PAIRS")
    class TwoPairs {
        @Test
        void PLAYER_A_wins() {
            assertWinner("2H 2D 5S 5C KS", "2C 3S 6H 8H JC", "Player \"" + FIRST_PLAYER + "\" wins with a TWO PAIRS hand (TWO, FIVE)");
        }

        @Test
        void PLAYER_B_wins() {
            assertWinner("2H 4D 5S 5C KS", "2C 6S 6H 8H 8C", "Player \"" + SECOND_PLAYER + "\" wins with a TWO PAIRS hand (SIX, EIGHT)");
        }

        @Test
        void PLAYER_B_wins_with_higher_two_pairs() {
            assertWinner("6C 6D 5S 5C KS", "6S 6H 7H 7D AC", "Player \"" + SECOND_PLAYER + "\" wins with a TWO PAIRS hand (SIX, SEVEN) and higher cards");
        }

        @Test
        void PLAYER_A_wins_with_higher_two_pairs() {
            assertWinner("6S 6H QH QD AC", "6C 6D 5S 5C KS", "Player \"" + FIRST_PLAYER + "\" wins with a TWO PAIRS hand (SIX, QUEEN) and higher cards");
        }

        @Test
        void PLAYER_A_wins_with_high_card() {
            assertWinner("6S 6H QH QD AC", "6C 6D QS QC KS", "Player \"" + FIRST_PLAYER + "\" wins with a HIGH CARD hand (ACE)");
        }
    }

    @Nested
    @DisplayName("THREE OF A KIND")
    class ThreeOfAKind {
        @Test
        void PLAYER_A_wins() {
            assertWinner("2H 2D 2S 5C KS", "2C 3S 6H 8H JC", "Player \"" + FIRST_PLAYER + "\" wins with a THREE OF A KIND hand (TWO)");
        }

        @Test
        void PLAYER_B_wins() {
            assertWinner("2H 2S 4D 5C KS", "2C 3S 6H 6D 6C", "Player \"" + SECOND_PLAYER + "\" wins with a THREE OF A KIND hand (SIX)");
        }

        @Test
        void PLAYER_B_wins_with_higher_card_values() {
            assertWinner("2H 5D 5S 5C KS", "2C 3S JH JD JC", "Player \"" + SECOND_PLAYER + "\" wins with a THREE OF A KIND hand (JACK) and higher cards");
        }

        @Test
        void PLAYER_A_wins_with_higher_card_values() {
            assertWinner("2H KD KS KH AS", "2C 3S JH JD KC", "Player \"" + FIRST_PLAYER + "\" wins with a THREE OF A KIND hand (KING)");
        }
    }

    @Nested
    @DisplayName("STRAIGHT")
    class Straight {
        @Test
        void PLAYER_A_wins() {
            assertWinner("2H 3D 4S 5C 6S", "2C 3S 6H 8H JC", "Player \"" + FIRST_PLAYER + "\" wins with a STRAIGHT hand");
        }

        @Test
        void PLAYER_B_wins() {
            assertWinner("2H 2S 4D 5C KS", "7C 8S 9H TD JC", "Player \"" + SECOND_PLAYER + "\" wins with a STRAIGHT hand");
        }

        @Test
        void PLAYER_B_wins_with_a_card_values() {
            assertWinner("2H 3D 4S 5C 6S", "6C 7S 8H 9D TC", "Player \"" + SECOND_PLAYER + "\" wins with a STRAIGHT hand and higher cards");
        }

        @Test
        void PLAYER_A_wins_with_a_card_values() {
            assertWinner("6C 7S 8H 9D TC", "2H 3D 4S 5C 6S", "Player \"" + FIRST_PLAYER + "\" wins with a STRAIGHT hand and higher cards");
        }

        @Test
        void tie() {
            assertWinner("6C 7S 8H 9D TC", "6D 7C 8C 9H TS", "Tie.");
        }
    }

    @Nested
    @DisplayName("FLUSH")
    class Flush {
        @Test
        void PLAYER_A_wins() {
            assertWinner("2H 5H 8H JH KH", "2C 3S 6H 8S JC", "Player \"" + FIRST_PLAYER + "\" wins with a FLUSH hand");
        }

        @Test
        void PLAYER_B_wins() {
            assertWinner("2C 3S 6H 8S JC", "2D 5D 8D JD KD", "Player \"" + SECOND_PLAYER + "\" wins with a FLUSH hand");
        }

        @Test
        void PLAYER_B_wins_with_higher_card_values() {
            assertWinner("2C 3C 6C 8C JC", "2D 5D 8D JD KD", "Player \"" + SECOND_PLAYER + "\" wins with a FLUSH hand and higher cards");
        }

        @Test
        void PLAYER_A_wins_with_higher_card_values() {
            assertWinner("2C 3C 6C 8C AC", "2D 5D 8D JD KD", "Player \"" + FIRST_PLAYER + "\" wins with a FLUSH hand and higher cards");
        }

        @Test
        void tie() {
            assertWinner("2C 3C 6C 8C AC", "2D 3D 6D 8D AD", "Tie.");
        }
    }

    @Nested
    @DisplayName("FULL HOUSE")
    class FullHouse {
        @Test
        void PLAYER_B_wins() {
            assertWinner("6C 7S 8H 9D TC", "3H 3S AC AD AH", "Player \"" + SECOND_PLAYER + "\" wins with a FULL HOUSE hand");
        }

        @Test
        void PLAYER_A_wins() {
            assertWinner("2H 2S 2D JH JS", "6C 7S 8H 9D TC", "Player \"" + FIRST_PLAYER + "\" wins with a FULL HOUSE hand");
        }

        @Test
        void PLAYER_B_wins_with_higher_card_values() {
            assertWinner("2C 2S 2H AH AD", "3D 3S 3C TD TS", "Player \"" + SECOND_PLAYER + "\" wins with a FULL HOUSE hand and higher cards");
        }

        @Test
        void PLAYER_A_wins_with_higher_card_values() {
            assertWinner("5C 5S 5H AH AD", "3D 3S 3C TD TS", "Player \"" + FIRST_PLAYER + "\" wins with a FULL HOUSE hand and higher cards");
        }
    }

    @Nested
    @DisplayName("FOUR OF A KIND")
    class FourOfAKind {
        @Test
        void PLAYER_B_wins() {
            assertWinner("2C 3S 8H TH AD", "9D 9S 9C 9H TS", "Player \"" + SECOND_PLAYER + "\" wins with a FOUR OF A KIND hand");
        }

        @Test
        void PLAYER_A_wins() {
            assertWinner("8D TS TC TH TD", "2C 3S 8H TH AD", "Player \"" + FIRST_PLAYER + "\" wins with a FOUR OF A KIND hand");
        }

        @Test
        void PLAYER_A_wins_with_higher_card_values() {
            assertWinner("8D TS TC TH TD", "2C 4S 4H 4C 4D", "Player \"" + FIRST_PLAYER + "\" wins with a FOUR OF A KIND hand and higher cards");
        }

        @Test
        void PLAYER_B_wins_with_higher_card_values() {
            assertWinner("8D TS TC TH TD", "JC JS JH JD AD", "Player \"" + SECOND_PLAYER + "\" wins with a FOUR OF A KIND hand and higher cards");
        }
    }


    @Nested
    @DisplayName("STRAIGHT FLUSH")
    class StraightFlush {
        @Test
        void PLAYER_A_wins() {
            assertWinner("8S 9S TS JS QS", "JC JS JH JD AD", "Player \"" + FIRST_PLAYER + "\" wins with a STRAIGHT FLUSH hand");
        }

        @Test
        void PLAYER_B_wins() {
            assertWinner("JC JS JH JD AD", "2H 3H 4H 5H 6H", "Player \"" + SECOND_PLAYER + "\" wins with a STRAIGHT FLUSH hand");
        }

        @Test
        void PLAYER_A_wins_and_higher_card_values() {
            assertWinner("8D 9D TD JD QD", "2H 3H 4H 5H 6H", "Player \"" + FIRST_PLAYER + "\" wins with a STRAIGHT FLUSH hand and higher cards");
        }

        @Test
        void PLAYER_B_wins_and_higher_card_values() {
            assertWinner("2H 3H 4H 5H 6H", "8D 9D TD JD QD", "Player \"" + SECOND_PLAYER + "\" wins with a STRAIGHT FLUSH hand and higher cards");
        }

        @Test
        void tie() {
            assertWinner("2H 3H 4H 5H 6H", "2D 3D 4D 5D 6D", "Tie.");
        }
    }

    @Nested
    @DisplayName("ROYAL FLUSH")
    class RoyalFlush {
        @Test
        void PLAYER_B_wins() {
            assertWinner("2H 3H 4H 5H 6H", "TD JD QD KD AD", "Player \"" + SECOND_PLAYER + "\" wins with a ROYAL FLUSH hand");
        }

        @Test
        void PLAYER_A_wins() {
            assertWinner("TH JH QH KH AH", "2H 3H 4H 5H 6H", "Player \"" + FIRST_PLAYER + "\" wins with a ROYAL FLUSH hand");
        }

        @Test
        void tie() {
            assertWinner("TH JH QH KH AH", "TD JD QD KD AD", "Tie.");
        }
    }
}
