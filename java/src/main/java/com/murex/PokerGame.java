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

import static java.util.stream.Collectors.*;

class PokerGame {
    private final Hand blackHand;
    private final Hand whiteHand;

    public PokerGame(String blackCards, String whiteCards) {
        this.blackHand = Hand.buildFrom("Black", blackCards);
        this.whiteHand = Hand.buildFrom("White", whiteCards);
    }

    public String getWinner() {
        String pair = checkPair();
        if (pair != null) return pair;

        String highCard = checkHighCard();
        if (highCard != null) return highCard;

        String tieResult = new TieRank(blackHand, whiteHand).verify();
        if(tieResult != null) return tieResult;

        return null;
    }

    private String checkPair() {
        Optional<Card> blackPairCard = getCardOfPairs(blackHand);
        Optional<Card> whitePairCard = getCardOfPairs(whiteHand);

        if(blackPairCard.isPresent() && whitePairCard.isPresent()) {
            int comparison = blackPairCard.get().compareTo(whitePairCard.get());
            if (comparison > 0) {
                return buildPairCardsMessage(blackHand, blackPairCard.get());
            } else if (comparison < 0) {
                return buildPairCardsMessage(whiteHand, whitePairCard.get());
            }else {
                int highCardComparison = compareCardAt(4);
                if (highCardComparison > 0) {
                    return blackHand.getName() + " wins. - with Pair cards and higher rank: " + blackPairCard.get().getValue() + " and " + blackHand.getCards()[4].getValue();
                }
                if (highCardComparison < 0) {
                    return whiteHand.getName() + " wins. - with Pair cards and higher rank: " + whitePairCard.get().getValue() + " and " + whiteHand.getCards()[4].getValue();
                }
            }
        }

        if(blackPairCard.isPresent()) {
            return buildPairCardsMessage(blackHand, blackPairCard.get());
        }

        if(whitePairCard.isPresent()) {
            return buildPairCardsMessage(whiteHand, whitePairCard.get());
        }

        return null;
    }

    private static String buildPairCardsMessage(Hand hand, Card card) {
        return hand.getName() + " wins. - with Pair cards: " + card.getValue();
    }

    private Optional<Card> getCardOfPairs(Hand pokerHand) {
        Map<Card, Long> cardsPairMap = Arrays.stream(pokerHand.getCards()).collect(groupingBy(Function.identity(), counting()));
        return cardsPairMap.keySet().stream().filter(card -> cardsPairMap.get(card) == 2).findAny();
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

    private String buildMessage(Hand hand) {
        return hand.getName() + " wins. - with high card: " + hand.getCards()[4].getValue();
    }

    private int compareCardAt(int index) {
        Card bCard = blackHand.getCards()[index];
        Card wCard = whiteHand.getCards()[index];
        return bCard.compareTo(wCard);
    }

}
