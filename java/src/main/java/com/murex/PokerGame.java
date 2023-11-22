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
        String blackHand1 = checkHighCard();
        if (blackHand1 != null) return blackHand1;
        boolean isTie = true;
        for (int i = 0; i < 5; i++) {
            if (compareCardAt(i) != 0) {
                isTie = false;
                break;
            }
        }
        if (isTie) {
            return "Tie.";
        }

        return null;
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
