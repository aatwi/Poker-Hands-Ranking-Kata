package poker.hand;

public enum CardNumber {
    TWO(2, '2'),
    THREE(3, '3'),
    FOUR(4, '4'),
    FIVE(5, '5'),
    SIX(6, '6'),
    SEVEN(7, '7'),
    EIGHT(8, '8'),
    NINE(9, '9'),
    TEN(10, 'T'),
    JACK(11, 'J'),
    QUEEN(12, 'Q'),
    KING(13, 'K'),
    ACE(14, 'A');

    private final char charValue;
    private final int intValue;

    public int getIntValue() {
        return intValue;
    }

    CardNumber(int intValue, char charValue) {
        this.intValue = intValue;
        this.charValue = charValue;
    }

    public static CardNumber from(char value) {
        for (CardNumber cardNumber : CardNumber.values()) {
            if(cardNumber.charValue == value) {
                return cardNumber;
            }
        }
        return null;
    }
}
