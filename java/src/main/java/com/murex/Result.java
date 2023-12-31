package com.murex;

import java.util.Objects;

public interface Result {
    boolean isMatch();

    String getMessage();
}

class NoWinner implements Result {
    private final boolean isMatch = false;
    private final String message  = "";
    public NoWinner() {
    }

    @Override
    public boolean isMatch() {
        return false;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "NoWinner{" +
                "isMatch=" + isMatch +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoWinner noWinner = (NoWinner) o;
        return isMatch == noWinner.isMatch && Objects.equals(message, noWinner.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isMatch, message);
    }
}

class Winner implements Result {

    private final boolean isMatch;
    private final String message;

    Winner(String message) {
        this.isMatch = true;
        this.message = message;
    }

    @Override
    public boolean isMatch() {
        return isMatch;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Winner{" +
                "isMatch=" + isMatch +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Winner winner = (Winner) o;
        return isMatch == winner.isMatch && Objects.equals(message, winner.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isMatch, message);
    }
}

class Tie implements Result {
    private final String message = "Tie.";
    private final boolean isMatch = true;

    public Tie() {
    }

    @Override
    public boolean isMatch() {
        return isMatch;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Tie{" +
                "message='" + message + '\'' +
                ", isMatch=" + isMatch +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tie tie = (Tie) o;
        return isMatch == tie.isMatch && Objects.equals(message, tie.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, isMatch);
    }
}