package com.murex;

import java.util.Objects;

public class Card implements Comparable<Card> {

    private final CardNumber cardNumber;
    private final Suite suite;

    public Card(char value, char suite) {
        this.cardNumber = CardNumber.from(value);
        this.suite = Suite.valueOf(String.valueOf(suite));
    }

    public String getName() {
        return cardNumber.toString();
    }

    public char getCharValue() {
        return cardNumber.getCharValue();
    }

    public int getIntValue() {
        return cardNumber.getIntValue();
    }

    public Suite getSuite() {
        return suite;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    @Override
    public int compareTo(Card otherCard) {
        return Integer.compare(cardNumber.getIntValue(), otherCard.getIntValue());
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber=" + cardNumber +
                ", suite=" + suite +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardNumber == card.cardNumber && suite == card.suite;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, suite);
    }
}
