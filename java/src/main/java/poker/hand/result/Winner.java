package poker.hand.result;

import java.util.Objects;

public class Winner implements Result {

    private final String message;

    Winner(String message) {
        this.message = message;
    }

    @Override
    public boolean isMatch() {
        return true;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return "Winner{message='" + message + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Winner winner = (Winner) o;
        return Objects.equals(message, winner.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
