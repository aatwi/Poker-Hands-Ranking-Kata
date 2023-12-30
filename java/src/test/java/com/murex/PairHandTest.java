package com.murex;

import com.murex.hands.PairHand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PairHandTest {

    @Test
    void is_pair_hand_should_return_false_when_there_is_no_pair_of_cards() {
        assertFalse(new PairHand(Hand.buildFrom("Black", "2H 4D 5S 6C JS")).hasPair());
    }

    @Test
    void is_pair_hand_should_return_true_when_there_is_a_pair_of_cards() {
        assertTrue(new PairHand(Hand.buildFrom("Black", "2H 2D 5S 6C JS")).hasPair());
    }

    @Test
    void it_returns_the_first_pair_of_cards() {
        PairHand blackHand = new PairHand(Hand.buildFrom("Black", "2H 2D 5S 6C JS"));
        assertEquals("TWO", blackHand.getPairCard().toString());
    }

    @Test
    void it_returns_an_empty_string_when_no_pairs_exist() {
        PairHand blackHand = new PairHand(Hand.buildFrom("Black", "2H 3D 5S 6C JS"));
        assertNull(blackHand.getPairCard());
    }
}