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

package poker.hand;

import poker.hand.ranking.RankingCategory;
import poker.hand.result.Result;

import java.util.ArrayList;
import java.util.List;

import static poker.hand.HandBuilder.aHand;
import static poker.hand.ranking.Flush.aFlush;
import static poker.hand.ranking.FourOfAKind.aFourOfAKind;
import static poker.hand.ranking.FullHouse.aFullHouse;
import static poker.hand.ranking.HighCard.aHighCard;
import static poker.hand.ranking.Pair.aPair;
import static poker.hand.ranking.RoyalFlush.aRoyalFlush;
import static poker.hand.ranking.Straight.aStraight;
import static poker.hand.ranking.StraightFlush.aStraightFlush;
import static poker.hand.ranking.ThreeOfAKind.aThreeOfAKind;
import static poker.hand.ranking.Tie.aTie;
import static poker.hand.ranking.TwoPairs.aTwoPairs;

class PokerGame {
    private final Hand firstPlayerHand;
    private final Hand secondPlayerHand;
    private final List<RankingCategory> ranks = new ArrayList<>();

    public PokerGame(String firstPlayerName, String firstPlayerCards, String secondPlayerName, String secondPlayerCards) {
        this.firstPlayerHand = aHand().withPlayer(firstPlayerName).withCards(firstPlayerCards).build();
        this.secondPlayerHand = aHand().withPlayer(secondPlayerName).withCards(secondPlayerCards).build();
        initializeRankingCategories();
    }

    private void initializeRankingCategories() {
        ranks.add(aRoyalFlush(firstPlayerHand, secondPlayerHand));
        ranks.add(aStraightFlush(firstPlayerHand, secondPlayerHand));
        ranks.add(aFourOfAKind(firstPlayerHand, secondPlayerHand));
        ranks.add(aFullHouse(firstPlayerHand, secondPlayerHand));
        ranks.add(aFlush(firstPlayerHand, secondPlayerHand));
        ranks.add(aStraight(firstPlayerHand, secondPlayerHand));
        ranks.add(aThreeOfAKind(firstPlayerHand, secondPlayerHand));
        ranks.add(aTwoPairs(firstPlayerHand, secondPlayerHand));
        ranks.add(aPair(firstPlayerHand, secondPlayerHand));
        ranks.add(aHighCard(firstPlayerHand, secondPlayerHand));
        ranks.add(aTie(firstPlayerHand, secondPlayerHand));
    }

    public String play() {
        return ranks.stream().map(RankingCategory::evaluate).filter(Result::isMatch).findFirst().map(Result::getMessage).orElse(null);
    }
}

