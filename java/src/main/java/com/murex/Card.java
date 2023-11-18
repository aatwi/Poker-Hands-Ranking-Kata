package com.murex;

public class Card implements Comparable<Card> {

    private final char value;

    public Card(char value) {
        this.value = value;
    }

    public String getValue() {
        return CardValueConverter.getCardName(value);
    }

    @Override
    public int compareTo(Card otherCard) {
        int otherValue = CardValueConverter.getIntegerValueOf(otherCard.value);
        int currentValue = CardValueConverter.getIntegerValueOf(this.value);

        return Integer.compare(currentValue, otherValue);
    }
}
