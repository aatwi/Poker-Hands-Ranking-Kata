package com.murex.ranking;

import com.murex.Hand;
import com.murex.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TwoPairCardRankingTest {

    @Test
    public void it_should_return_a_matching_result_when_the_black_player_has_two_pairs() {
        Hand blackHand = Hand.buildFrom("Black", "7H 7C TD TH AS");
        Hand whiteHand = Hand.buildFrom("White", "1H 4C 5H 7D AD");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Assertions.assertEquals(Result.aMatchResult("Black wins. - with two pairs: 7 and Ten"), twoPairCardRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_matching_result_when_the_white_player_has_two_pairs() {
        Hand blackHand = Hand.buildFrom("Black", "2H 7C 8D TH AS");
        Hand whiteHand = Hand.buildFrom("White", "1H 1C 6H 7D 7S");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Assertions.assertEquals(Result.aMatchResult("White wins. - with two pairs: 1 and 7"), twoPairCardRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_no_matching_result_when_none_have_two_pairs() {
        Hand blackHand = Hand.buildFrom("Black", "1H 7C 8D TH AS");
        Hand whiteHand = Hand.buildFrom("White", "2H 3C 6H 7D 8S");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Assertions.assertEquals(Result.aNoMatchResult(), twoPairCardRanking.getMatchingResult());
    }

    @Test
    public void it_should_return_a_no_matching_result_when_only_one_pair_exists() {
        Hand blackHand = Hand.buildFrom("Black", "2H 7C 8D TH AS");
        Hand whiteHand = Hand.buildFrom("White", "1H 1C 6H 7D 9S");

        TwoPairCardRanking twoPairCardRanking = new TwoPairCardRanking(blackHand, whiteHand);
        Assertions.assertEquals(Result.aMatchResult("White wins. - with two pairs: 1 and 7"), twoPairCardRanking.getMatchingResult());
    }
}