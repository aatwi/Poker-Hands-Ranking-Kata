package com.murex;

import java.util.Objects;

public class Card implements Comparable<Card> {

    private final char value;
    private final CardNumber cardNumber;

    private final Suite suite;

    public Card(char value, char suite) {
        this.value = value;
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

    @Override
    public int compareTo(Card otherCard) { 
        return Integer.compare(cardNumber.getIntValue(), otherCard.getIntValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Card{" +
               "value=" + value +
               '}';
    }
}
