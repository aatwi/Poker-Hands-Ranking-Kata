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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PokerGameTest {

    @Test
    public void
    HIGH_CARD_white_wins_with_Ace(){
        assertWinner("2H 3D 5S 9C KD", "2C 3H 4S 8C AH", "White wins. - with high card: Ace");
    }

    private static void assertWinner(String black, String white, String expected) {
        assertEquals(expected, new PokerGame(black, white).getWinner());
    }

    @Test
    public void
    HIGH_CARD_black_wins_with_Queen(){
        assertWinner("2H 3D 5S 9C QD", "2C 3H 4S 8C JH", "Black wins. - with high card: Queen");
    }

    @Test
    public void
    HIGH_CARD_white_wins_Jack(){
        assertWinner("2H 3D 5S 7C 9D", "2C 3H 4S 8C JH", "White wins. - with high card: Jack");
    }

    @Test
    public void
    HIGH_CARD_black_wins_with_9(){
        assertWinner("2H 3D 5S 8C 9D", "2C 3H 4S 7C 8H", "Black wins. - with high card: 9");
    }

    @Test
    public void
    TIE_between_two_hands() {
        String black = "2H 3D 5S 9C KD";
        String white = "2D 3H 5C 9S KH";

        assertEquals("Tie.", new PokerGame(black, white).getWinner());
    }

    @Test
    public void
    PAIR_black_wins_with_ace_as_pair() {
        String black = "7H JC KD AH AS";
        String white = "2D 3H 5C 9S KH";

        assertEquals("Black wins. - with Pair cards: Ace", new PokerGame(black, white).getWinner());
    }

    @Test
    public void
    PAIR_black_wins_with_jack_as_pair() {
        String black = "7H JH JC KD AS";
        String white = "2D 3H 5C 9S KH";

        assertEquals("Black wins. - with Pair cards: Jack", new PokerGame(black, white).getWinner());
    }

    @Test
    public void
    PAIR_white_wins_with_4_as_pair() {
        String black = "5D 6C 7H JH AS";
        String white = "2D 4H 4C 9S KH";

        assertEquals("White wins. - with Pair cards: 4", new PokerGame(black, white).getWinner());
    }

    @Test
    public void
    PAIR_white_wins_with_ace_over_a_pair_of_2() {
        String black = "2H 2D 5S 6C AS";
        String white = "4D 9S KH AH AC";

        assertEquals("White wins. - with Pair cards: Ace", new PokerGame(black, white).getWinner());
    }

    @Test
    public void
    PAIR_white_wins_with_ace_over_a_pair_of_ace_due_to_other_rank() {
        String black = "2H 2D 5S 6C JS";
        String white = "2C 2S 6H 8H AC";

        assertEquals("White wins. - with Pair cards and higher rank: 2 and Ace", new PokerGame(black, white).getWinner());
    }

    @Test
    public void
    PAIR_black_wins_with_ace_over_a_pair_of_ace_due_to_other_rank() {
        String black = "2H 2D 5S 6C KS";
        String white = "2C 2S 6H 8H JC";

        assertEquals("Black wins. - with Pair cards and higher rank: 2 and King", new PokerGame(black, white).getWinner());
    }
}
