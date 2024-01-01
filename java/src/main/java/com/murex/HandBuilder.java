package com.murex;

import javax.net.ssl.SNIHostName;

public class HandBuilder {
    private String playerName;
    private Card[] cards;
    public HandBuilder() {

    }

    public static HandBuilder aHandBuilder() {
        return new HandBuilder();
    }

    public HandBuilder withPlayer(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public HandBuilder withCards(String cards) {
        this.cards = convertToCards(cards);
        return this;
    }

    private static Card[] convertToCards(String cards) {
        String[] cardsValues = cards.split(" ");
        Card[] handCards = new Card[5];
        for (int index = 0; index < 5; index++) {
            handCards[index] = new Card(cardsValues[index].charAt(0), cardsValues[index].charAt(1));
        }
        return handCards;
    }

    public Hand build(){
        return new Hand(playerName, cards);
    }
}
