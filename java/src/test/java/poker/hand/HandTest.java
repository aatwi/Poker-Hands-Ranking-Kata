package poker.hand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HandTest {

    @Test
    public void
    it_builds_a_poker_hand_from_string_cards() {
        String input = "2H 3D 5S 9C KD";
        Hand pokerHand = HandBuilder.aHand().withPlayer("player").withCards(input).build();
        Hand expected = new Hand("player",
                new Card[]{
                        new Card('2', 'H'),
                        new Card('3', 'D'),
                        new Card('5', 'S'),
                        new Card('9', 'C'),
                        new Card('K', 'D')});

        Assertions.assertEquals(expected, pokerHand);
    }
}
