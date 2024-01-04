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

import java.util.*;

import static com.murex.HandBuilder.aHand;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

class PokerGame {
    private final Hand firstPlayerHand;
    private final Hand secondPlayerHand;
    private final List<RankingCategory> ranks = new ArrayList<>();

    public PokerGame(String firstPlayerName, String firstPlayerCards, String secondPlayerName, String secondPlayerCards) {
        this.firstPlayerHand = aHand().withPlayer(firstPlayerName).withCards(firstPlayerCards).build();
        this.secondPlayerHand = aHand().withPlayer(secondPlayerName).withCards(secondPlayerCards).build();
        addRankingCategories();
    }

    private void addRankingCategories() {
        ranks.add(new RoyalFlush(firstPlayerHand, secondPlayerHand));
        ranks.add(new StraightFlush(firstPlayerHand, secondPlayerHand));
        ranks.add(new FourOfAKind(firstPlayerHand, secondPlayerHand));
        ranks.add(new FullHouse(firstPlayerHand, secondPlayerHand));
        ranks.add(new Flush(firstPlayerHand, secondPlayerHand));
//        ranks.add(new Straight(firstPlayerHand, secondPlayerHand));
    }

    public String getWinner() {
        String result = ranks.stream().map(RankingCategory::evaluate).filter(Result::isMatch).findFirst().map(Result::getMessage).orElse(null);

        if (result == null) {
            boolean firstPlayerHasStraight = true;
            for (int i = 1; i < firstPlayerHand.cards().length; i++) {
                int previousCard = firstPlayerHand.getCardAt(i - 1).getIntValue();
                int currentCard = firstPlayerHand.getCardAt(i).getIntValue();
                if (currentCard != previousCard + 1) {
                    firstPlayerHasStraight = false;
                    break;
                }
            }

            boolean secondPlayerHasStraight = true;
            for (int i = 1; i < secondPlayerHand.cards().length; i++) {
                int previousCard = secondPlayerHand.getCardAt(i - 1).getIntValue();
                int currentCard = secondPlayerHand.getCardAt(i).getIntValue();
                if (currentCard != previousCard + 1) {
                    secondPlayerHasStraight = false;
                    break;
                }
            }

            if (!firstPlayerHasStraight && !secondPlayerHasStraight) {
                result = null;
            } else if (firstPlayerHasStraight && secondPlayerHasStraight) {
                if (firstPlayerHand.getCardAt(0).getIntValue() > secondPlayerHand.getCardAt(0).getIntValue()) {
                    result = String.format("Player \"%s\" wins with a %s hand%s", firstPlayerHand.playerName(), "STRAIGHT", " and higher cards");
                } else if (secondPlayerHand.getCardAt(0).getIntValue() > firstPlayerHand.getCardAt(0).getIntValue()) {
                    result = String.format("Player \"%s\" wins with a %s hand%s", secondPlayerHand.playerName(), "STRAIGHT", " and higher cards");
                }
            } else if (firstPlayerHasStraight) {
                result = String.format("Player \"%s\" wins with a %s hand", firstPlayerHand.playerName(), "STRAIGHT");
            } else if (secondPlayerHasStraight) {
                result = String.format("Player \"%s\" wins with a %s hand", secondPlayerHand.playerName(), "STRAIGHT");
            } else {
                result = null;
            }
        }

        if (result == null) {
            Map<CardNumber, Long> firstPlayerThreeCardsMap = Arrays.stream(firstPlayerHand.cards()).collect(groupingBy(Card::getCardNumber, counting()));
            Optional<CardNumber> firstPlayerThreeOfAKindCards = firstPlayerThreeCardsMap.keySet().stream().filter(x -> firstPlayerThreeCardsMap.get(x) == 3).findFirst();

            Map<CardNumber, Long> secondPlayerThreeCardsMap = Arrays.stream(secondPlayerHand.cards()).collect(groupingBy(Card::getCardNumber, counting()));
            Optional<CardNumber> secondPlayerThreeOfAKindCards = secondPlayerThreeCardsMap.keySet().stream().filter(x -> secondPlayerThreeCardsMap.get(x) == 3).findFirst();

            if (firstPlayerThreeOfAKindCards.isEmpty() && secondPlayerThreeOfAKindCards.isEmpty()) {
                result = null;
            } else if (firstPlayerThreeOfAKindCards.isPresent() && secondPlayerThreeOfAKindCards.isPresent()) {
                CardNumber firstPlayerCard = firstPlayerThreeOfAKindCards.get();
                CardNumber secondPlayerCard = secondPlayerThreeOfAKindCards.get();

                if (firstPlayerCard.getIntValue() > secondPlayerCard.getIntValue()) {
                    result = String.format("Player \"%s\" wins with a %s hand%s%s", firstPlayerHand.playerName(), "THREE OF A KIND", " (" + firstPlayerCard + ")", " and higher cards");
                } else if (secondPlayerCard.getIntValue() > firstPlayerCard.getIntValue()) {
                    result = String.format("Player \"%s\" wins with a %s hand%s%s", secondPlayerHand.playerName(), "THREE OF A KIND", " (" + secondPlayerCard + ")", " and higher cards");
                }
            } else if (firstPlayerThreeOfAKindCards.isPresent()) {
                result = String.format("Player \"%s\" wins with a %s hand%s", firstPlayerHand.playerName(), "THREE OF A KIND", " (" + firstPlayerThreeOfAKindCards.get() + ")");
            } else if (secondPlayerThreeOfAKindCards.isPresent()) {
                result = String.format("Player \"%s\" wins with a %s hand%s", secondPlayerHand.playerName(), "THREE OF A KIND", " (" + secondPlayerThreeOfAKindCards.get() + ")");
            } else {
                result = null;
            }
        }

        if (result == null) {
            Map<CardNumber, Long> firstPlayerTwoPairsMap = Arrays.stream(firstPlayerHand.cards()).collect(groupingBy(Card::getCardNumber, counting()));
            List<CardNumber> firstPlayerPairOfCards = firstPlayerTwoPairsMap.keySet().stream().filter(x -> firstPlayerTwoPairsMap.get(x) == 2).sorted().toList();

            Map<CardNumber, Long> secondPlayerTwoPairsMap = Arrays.stream(secondPlayerHand.cards()).collect(groupingBy(Card::getCardNumber, counting()));
            List<CardNumber> secondPlayerPairOfCards = secondPlayerTwoPairsMap.keySet().stream().filter(x -> secondPlayerTwoPairsMap.get(x) == 2).sorted().toList();

            if (firstPlayerPairOfCards.size() != 2 && secondPlayerPairOfCards.size() != 2) {
                result = null;
            } else if (firstPlayerPairOfCards.size() == 2 && secondPlayerPairOfCards.size() == 2) {
                if (firstPlayerPairOfCards.get(1).getIntValue() < secondPlayerPairOfCards.get(1).getIntValue()) {
                    result = String.format("Player \"%s\" wins with a %s hand%s%s", secondPlayerHand.playerName(), "TWO PAIRS", " (" + secondPlayerPairOfCards.get(0) + ", " + secondPlayerPairOfCards.get(1) + ")", " and higher cards");
                } else if (firstPlayerPairOfCards.get(1).getIntValue() > secondPlayerPairOfCards.get(1).getIntValue()) {
                    result = String.format("Player \"%s\" wins with a %s hand%s%s", firstPlayerHand.playerName(), "TWO PAIRS", " (" + firstPlayerPairOfCards.get(0) + ", " + firstPlayerPairOfCards.get(1) + ")", " and higher cards");
                } else if (firstPlayerPairOfCards.get(0).getIntValue() < secondPlayerPairOfCards.get(0).getIntValue()) {
                    result = String.format("Player \"%s\" wins with a %s hand%s%s", secondPlayerHand.playerName(), "TWO PAIRS", " (" + secondPlayerPairOfCards.get(0) + ", " + secondPlayerPairOfCards.get(1) + ")", " and higher cards");
                } else if (firstPlayerPairOfCards.get(0).getIntValue() > secondPlayerPairOfCards.get(0).getIntValue()) {
                    result = String.format("Player \"%s\" wins with a %s hand%s%s", firstPlayerHand.playerName(), "TWO PAIRS", " (" + firstPlayerPairOfCards.get(0) + ", " + firstPlayerPairOfCards.get(1) + ")", " and higher cards");
                } else {
                    result = null;
                }
            } else {
                if (firstPlayerPairOfCards.size() == 2) {
                    result = String.format("Player \"%s\" wins with a %s hand%s", firstPlayerHand.playerName(), "TWO PAIRS", " (" + firstPlayerPairOfCards.get(0) + ", " + firstPlayerPairOfCards.get(1) + ")");
                } else if (secondPlayerPairOfCards.size() == 2) {
                    result = String.format("Player \"%s\" wins with a %s hand%s", secondPlayerHand.playerName(), "TWO PAIRS", " (" + secondPlayerPairOfCards.get(0) + ", " + secondPlayerPairOfCards.get(1) + ")");
                } else {
                    result = null;
                }
            }

            if (result == null) {
                Map<CardNumber, Long> firstPlayerCardsPairMap = Arrays.stream(firstPlayerHand.cards()).collect(groupingBy(Card::getCardNumber, counting()));
                Optional<CardNumber> firstPlayerPairCards = firstPlayerCardsPairMap.keySet().stream().filter(card -> firstPlayerCardsPairMap.get(card) == 2).findAny();

                Map<CardNumber, Long> secondPlayerCardsPairMap = Arrays.stream(secondPlayerHand.cards()).collect(groupingBy(Card::getCardNumber, counting()));
                Optional<CardNumber> secondPlayerPairCards = secondPlayerCardsPairMap.keySet().stream().filter(card -> secondPlayerCardsPairMap.get(card) == 2).findAny();

                if (firstPlayerPairCards.isEmpty() && secondPlayerPairCards.isEmpty()) {
                    result = null;
                } else if (firstPlayerPairCards.isPresent() && secondPlayerPairCards.isPresent()) {
                    int comparison = firstPlayerPairCards.get().compareTo(secondPlayerPairCards.get());
                    if (comparison == 0) {
                        result = null;
                    } else {
                        result = comparison > 0 ?
                                String.format("Player \"%s\" wins with a %s hand%s%s", firstPlayerHand.playerName(), "PAIR", " (" + firstPlayerPairCards.get() + ")", " and higher cards") :
                                String.format("Player \"%s\" wins with a %s hand%s%s", secondPlayerHand.playerName(), "PAIR", " (" + secondPlayerPairCards.get() + ")", " and higher cards");
                    }
                } else {
                    result = firstPlayerPairCards.isPresent() ?
                            String.format("Player \"%s\" wins with a %s hand%s", firstPlayerHand.playerName(), "PAIR", " (" + firstPlayerPairCards.get() + ")") :
                            String.format("Player \"%s\" wins with a %s hand%s", secondPlayerHand.playerName(), "PAIR", " (" + secondPlayerPairCards.get() + ")");
                }
            }

            if (result == null) {
                for (int index = 4; index >= 0; index--) {
                    int cardComparison = firstPlayerHand.getCardAt(index).compareTo(secondPlayerHand.getCardAt(index));
                    if (cardComparison != 0) {
                        result = cardComparison > 0 ?
                                String.format("Player \"%s\" wins with a %s hand%s", firstPlayerHand.playerName(), "HIGH CARD", " (" + firstPlayerHand.getCardAt(index).getCardNumber() + ")") :
                                String.format("Player \"%s\" wins with a %s hand%s", secondPlayerHand.playerName(), "HIGH CARD", " (" + secondPlayerHand.getCardAt(index).getCardNumber() + ")");
                        break;
                    }
                }
            }

            if (result == null) {
                for (int index = 0; index < 5; index++) {
                    if (firstPlayerHand.getCardAt(index).compareTo(secondPlayerHand.getCardAt(index)) != 0) {
                        return null;
                    }
                }
                result = "Tie.";
            }
            return result;
        }
        return result;
    }
}

