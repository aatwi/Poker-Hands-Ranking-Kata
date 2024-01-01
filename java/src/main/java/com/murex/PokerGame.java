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

import com.murex.ranking.*;
import com.murex.ranking.Tie;

import java.util.ArrayList;
import java.util.List;

import static com.murex.HandBuilder.aHand;

class PokerGame {
    private final Hand blackHand;
    private final Hand whiteHand;
    private final List<RankingCategory> ranks = new ArrayList<>();

    public PokerGame(String blackCards, String whiteCards) {
        this.blackHand = aHand().withPlayer("Black").withCards(blackCards).build();
        this.whiteHand = aHand().withPlayer("White").withCards(whiteCards).build();
        addRankingCategories();
    }

    public String getWinner() {
        return ranks.stream().map(RankingCategory::evaluate).filter(Result::isMatch).findFirst().map(Result::getMessage).orElse(null);
    }

    private void addRankingCategories() {
        ranks.add(new RoyalFlush(blackHand, whiteHand));
        ranks.add(new StraightFlush(blackHand, whiteHand));
        ranks.add(new FourOfAKind(blackHand, whiteHand));
        ranks.add(new FullHouse(blackHand, whiteHand));
        ranks.add(new Flush(blackHand, whiteHand));
        ranks.add(new Straight(blackHand, whiteHand));
        ranks.add(new ThreeOfAKind(blackHand, whiteHand));
        ranks.add(new TwoPairs(blackHand, whiteHand));
        ranks.add(new Pair(blackHand, whiteHand));
        ranks.add(new HighCard(blackHand, whiteHand));
        ranks.add(new Tie(blackHand, whiteHand));
    }

}
