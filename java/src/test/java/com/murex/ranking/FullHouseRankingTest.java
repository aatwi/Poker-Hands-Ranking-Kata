package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Test;

import static com.murex.Result.aMatchResult;
import static com.murex.Result.aNoMatchResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FullHouseRankingTest {

    private static FullHouseRanking buildFullHouseCardRanking(String blackCards, String whiteCards) {
        Hand blackHand = Hand.buildFrom("Black", blackCards);
        Hand whiteHand = Hand.buildFrom("White", whiteCards);

        return new FullHouseRanking(blackHand, whiteHand);
    }

    private static void assertNoMatchingResults(String blackCards, String whiteCards) {
        FullHouseRanking fullHouseCardRanking = buildFullHouseCardRanking(blackCards, whiteCards);
        assertEquals(aNoMatchResult(), fullHouseCardRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_no_matching_result_when_neither_hands_have_full_house() {
        FullHouseRanking fullHouseCardRanking = buildFullHouseCardRanking("3H 3S 4C AH AD", "4D 5S 7D JS AC");
        assertEquals(aNoMatchResult(), fullHouseCardRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_with_black_as_winner_having_flush_with_hearts() {
        FullHouseRanking fullHouseCardRanking = buildFullHouseCardRanking("3H 3S 3C AH AD", "4D 5S 7D JS AC");
        assertEquals(aMatchResult("Black wins. - with full house"), fullHouseCardRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_a_no_matching_result_result_when_black_has_only_3_cards_of_same_value() {
        assertNoMatchingResults("3H 3S 3C KH AD", "4D 5S 7D JS AC");
    }

    @Test
    public void it_should_return_a_matching_result_with_white_as_winner_having_flush_with_hearts() {
        FullHouseRanking fullHouseCardRanking = buildFullHouseCardRanking("4D 5S 7D JS AC", "5H 5S 5C KH KD");
        assertEquals(aMatchResult("White wins. - with full house"), fullHouseCardRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_no_matching_result_result_when_white_has_only_3_cards_of_same_value() {
        assertNoMatchingResults("4D 5S 7D JS AC", "4H 4S 4C KH AD");
    }

    @Test
    public void it_should_return_a_matching_result_result_when_white_and_black_have_full_house_and_higher_cards_for_white() {
        FullHouseRanking fullHouseCardRanking = buildFullHouseCardRanking("4D 4S 4D JS JC", "5H 5S 5C TH TD");
        assertEquals(aMatchResult("White wins. - with full house and higher hand"), fullHouseCardRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_result_when_white_and_black_have_full_house_and_higher_cards_for_black() {
        FullHouseRanking fullHouseCardRanking = buildFullHouseCardRanking("5H 5S 5C TH TD", "4D 4S 4D JS JC");
        assertEquals(aMatchResult("Black wins. - with full house and higher hand"), fullHouseCardRanking.getMatchingResult());
    }
}