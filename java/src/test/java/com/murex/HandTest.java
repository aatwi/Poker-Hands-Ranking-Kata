package com.murex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HandTest {

    @Test
    public void
    it_builds_a_poker_hand_from_string_cards() {
        String input = "2H 3D 5S 9C KD";
        Hand pokerHand = Hand.buildFrom("blackHand", input);
        Hand expected = new Hand("blackHand",
                new Card[]{
                        new Card('2'),
                        new Card('3'),
                        new Card('5'),
                        new Card('9'),
                        new Card('K')});

        Assertions.assertEquals(expected, pokerHand);
    }
}
