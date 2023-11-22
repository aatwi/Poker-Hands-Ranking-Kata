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

    private final String blackCards;
    private final String whiteCards;
    private final Hand blackHand;

    public PokerGame(String blackCards, String whiteCards) {
        this.blackCards = blackCards;
        blackHand = Hand.buildFrom("Black", blackCards);
        this.whiteCards = whiteCards;
    }

    private static int compareCardAt(int index, String[] blackCards, String[] whiteCards) {
        Card bCard = new Card(blackCards[index].charAt(0));
        Card wCard = new Card(whiteCards[index].charAt(0));
        return bCard.compareTo(wCard);
    }

    private static String buildMessage(String winner, String card) {
        return winner + " wins. - with high card: " + card;
    }

    public String getWinner() {
        String[] blackCards = this.blackCards.split(" ");
        String[] whiteCards = this.whiteCards.split(" ");

        Card blackCard = new Card(blackCards[4].charAt(0));
        Card whiteCard = new Card(whiteCards[4].charAt(0));

        int comparison =  compareCardAt(4, blackCards, whiteCards);
        if (comparison > 0) {
            return buildMessage("Black", blackCard.getValue());
        } else if (comparison < 0) {
            return buildMessage("White", whiteCard.getValue());
        } else {
            boolean isTie = true;
            for (int i = 0; i < 5; i++) {
                if(compareCardAt(i, blackCards, whiteCards) != 0) {
                    isTie = false;
                    break;
                }
            }
            if(isTie) {
                return "Tie.";
            }
        }
        return null;
    }

    private static String getString(String blackHand, String whiteHand) {
        String[] blackCards = blackHand.split(" ");
        String[] whiteCards = whiteHand.split(" ");

        Card blackCard = new Card(blackCards[4].charAt(0));
        Card whiteCard = new Card(whiteCards[4].charAt(0));

        int comparison =  compareCardAt(4, blackCards, whiteCards);
        if (comparison > 0) {
            return buildMessage("Black", blackCard.getValue());
        } else if (comparison < 0) {
            return buildMessage("White", whiteCard.getValue());
        } else {
            boolean isTie = true;
            for (int i = 0; i < 5; i++) {
                if(compareCardAt(i, blackCards, whiteCards) != 0) {
                    isTie = false;
                    break;
                }
            }
            if(isTie) {
                return "Tie.";
            }
        }
        return null;
    }
}
