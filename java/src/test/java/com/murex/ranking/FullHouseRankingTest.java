package com.murex.ranking;

import com.murex.Hand;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.*;

class FullHouseRankingTest {

    private static FullHouseRanking buildFullHouseCardRanking(String blackCards, String whiteCards) {
        Hand blackHand = Hand.buildFrom("Black", blackCards);
        Hand whiteHand = Hand.buildFrom("White", whiteCards);

        return new FullHouseRanking(blackHand, whiteHand);
    }

    @Test
    public void it_should_return_a_no_matching_result_when_neither_hands_have_full_house() {
        FullHouseRanking fullHouseCardRanking = buildFullHouseCardRanking("3H 3S 4C AH AD", "4D 5S 7D JS AC");
        assertEquals(aNoMatchResult(), fullHouseCardRanking.getMatchingResult());
    }

    @Disabled
    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_hearts() {
        FullHouseRanking fullHouseCardRanking = buildFullHouseCardRanking("3H 3S 3C AH AD", "4D 5S 7D JS AC");
        assertEquals(aMatchResult("Black wins. - with full house"), fullHouseCardRanking.getMatchingResult());
    }
}