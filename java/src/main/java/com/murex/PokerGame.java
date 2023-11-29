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

import com.murex.ranking.HighCardRanking;
import com.murex.ranking.PairCardRanking;
import com.murex.ranking.PokerHandRanking;
import com.murex.ranking.TieRanking;

import java.util.ArrayList;
import java.util.List;

class PokerGame {
    private final Hand blackHand;
    private final Hand whiteHand;
    private final List<PokerHandRanking> ranks = new ArrayList<>();

    public PokerGame(String blackCards, String whiteCards) {
        this.blackHand = Hand.buildFrom("Black", blackCards);
        this.whiteHand = Hand.buildFrom("White", whiteCards);
        addPossibleRanks();
    }

    public String getWinner() {
        return ranks.stream().map(PokerHandRanking::getMatchingResult).filter(Result::isMatch).findFirst().map(Result::message).orElse(null);
    }

    private void addPossibleRanks() {
        ranks.add(new PairCardRanking(blackHand, whiteHand));
        ranks.add(new HighCardRanking(blackHand, whiteHand));
        ranks.add(new TieRanking(blackHand, whiteHand));
    }

}
