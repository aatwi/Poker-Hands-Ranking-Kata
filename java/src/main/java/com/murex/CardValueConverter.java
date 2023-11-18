package com.murex;

import java.util.HashMap;

public class CardValueConverter {

    private static final HashMap<Character, Integer> CARD_INTEGER_VALUES = new HashMap<>() {{
        put('J', 11);
        put('Q', 12);
        put('K', 13);
        put('A', 14);
    }};
    private static final HashMap<Character, String> CARD_NAMES = new HashMap<>() {{
        put('J', "Jack");
        put('Q', "Queen");
        put('K', "King");
        put('A', "Ace");
    }};

    public static int getIntegerValueOf(char value) {
        return CARD_INTEGER_VALUES.getOrDefault(value, Character.getNumericValue(value));
    }

    public static String getCardName(char value) {
        return CARD_NAMES.getOrDefault(value, "" + value);
    }
}
