package com.murex;

public enum Suite {
    CLUBS {
        @Override
        char getValue() {
            return 'C';
        }
    },
    SPADES {
        @Override
        char getValue() {
            return 'S';
        }
    },
    HEARTS {
        @Override
        char getValue() {
            return 'H';
        }
    },
    DIAMONDS {
        @Override
        char getValue() {
            return 'D';
        }
    };

    abstract char getValue();
}
