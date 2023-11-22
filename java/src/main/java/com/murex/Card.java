package com.murex;

import java.util.Objects;

public class Card implements Comparable<Card> {

    private final char value;

    public Card(char value) {
        this.value = value;
    }

    public String getValue() {
        return CardValueConverter.getCardName(value);
    }

    public char getCharValue() {
        return value;
    }

    @Override
    public int compareTo(Card otherCard) {
        int otherValue = CardValueConverter.getIntegerValueOf(otherCard.value);
        int currentValue = CardValueConverter.getIntegerValueOf(this.value);

        return Integer.compare(currentValue, otherValue);
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
