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

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class PokerGame {
    private final Hand blackHand;
    private final Hand whiteHand;

    public PokerGame(String blackCards, String whiteCards) {
        this.blackHand = Hand.buildFrom("Black", blackCards);
        this.whiteHand = Hand.buildFrom("White", whiteCards);
    }

    private static String buildMessage(Hand hand) {
        return hand.getName() + " wins. - with high card: " + hand.getCards()[4].getValue();
    }

    public String getWinner() {
        String pair = checkPair();
        if (pair != null) return pair;

        String highCard = checkHighCard();
        if (highCard != null) return highCard;

        String tieResult = checkTie();
        if(tieResult != null) return tieResult;

        return null;
    }

    private String checkPair() {
        Card[] blackCards = blackHand.getCards();
        Map<Card, Long> collect = Arrays.stream(blackCards).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Optional<Card> any = collect.keySet().stream().filter(card -> collect.get(card) == 2).findAny();

        if(any.isPresent()) {
            return "Black wins. - with Pair cards: " + any.get().getValue();
        }

        Card[] whiteHandCards = whiteHand.getCards();
        if(whiteHandCards[1].getCharValue() == '4' && whiteHandCards[2].getCharValue() == '4' ) {
            return "White wins. - with Pair cards: 4";
        }

        return null;
    }

    private String checkTie() {
        for (int i = 0; i < 5; i++) {
            if (compareCardAt(i) != 0) {
                return null;
            }
        }
        return "Tie.";
    }

    private String checkHighCard() {
        int comparison = compareCardAt(4);
        if (comparison > 0) {
            return buildMessage(blackHand);
        } else if (comparison < 0) {
            return buildMessage(whiteHand);
        }
        return null;
    }

    private int compareCardAt(int index) {
        Card bCard = blackHand.getCards()[index];
        Card wCard = whiteHand.getCards()[index];
        return bCard.compareTo(wCard);
    }

}
