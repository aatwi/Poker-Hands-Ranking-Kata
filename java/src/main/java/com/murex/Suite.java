package com.murex;

public enum Suite {
    C {
        @Override
        String getName() {
            return "Clubs";
        }
    },
    S {
        @Override
        String getName() {
            return "Spades";
        }
    },
    H {
        @Override
        String getName() {
            return "Hearts";
        }
    },
    D {
        @Override
        String getName() {
            return "Diamonds";
        }
    };

    abstract String getName();

}
