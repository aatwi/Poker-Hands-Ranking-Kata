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
        String black = "2H 3D 5S 9C KD";
        String white = "2C 3H 4S 8C AH";

        String expected = "White wins. - with high card: Ace";
        assertEquals(expected, new PokerGame(black, white).getWinner());
    }

    @Test
    public void
    HIGH_CARD_black_wins_with_Queen(){
        String black = "2H 3D 5S 9C QD";
        String white = "2C 3H 4S 8C JH";

        String expected = "Black wins. - with high card: Queen";
        assertEquals(expected, new PokerGame(black, white).getWinner());
    }

    @Test
    public void
    HIGH_CARD_white_wins_Jack(){
        String black = "2H 3D 5S 7C 9D";
        String white = "2C 3H 4S 8C JH";

        String expected = "White wins. - with high card: Jack";
        assertEquals(expected, new PokerGame(black, white).getWinner());
    }

    @Test
    public void
    HIGH_CARD_black_wins_with_9(){
        String black = "2H 3D 5S 8C 9D";
        String white = "2C 3H 4S 7C 8H";

        String expected = "Black wins. - with high card: 9";
        assertEquals(expected, new PokerGame(black, white).getWinner());
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
        String black = "7H JH JC AS KD";
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
}
