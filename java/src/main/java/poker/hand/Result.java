package poker.hand;

import java.util.Objects;

public abstract class Result {

    private final boolean isMatch;
    private final String message;

    Result(boolean isMatch, String message) {
        this.isMatch = isMatch;
        this.message = message;
    }

    public boolean isMatch() {
        return isMatch;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return isMatch == result.isMatch && Objects.equals(message, result.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isMatch, message);
    }
}

class NoWinner extends Result {
    public NoWinner() {
        super(false, "");
    }

    @Override
    public String toString() {
        return "NoWinner{" +
                "isMatch=" + isMatch() +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}

class Winner extends Result {

    Winner(String message) {
        super(true, message);
    }

    @Override
    public String toString() {
        return "Winner{" +
                "isMatch=" + isMatch() +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}

class Tie extends Result {

    public Tie() {
        super(true, "Tie.");
    }

    @Override
    public String toString() {
        return "Tie{" +
                "isMatch=" + isMatch() +
                ", message='" + getMessage() +
                '}';
    }
}