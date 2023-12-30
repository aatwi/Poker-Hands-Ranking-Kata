package com.murex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardNumberTest {

    @Test
    public void converts_chars_to_CardNumber() {
        Assertions.assertEquals(CardNumber.TEN, CardNumber.from('T'));
    }
}