package poker.hand.result;

import java.util.Objects;

public class DefaultResult implements Result{
    private final String message = "";

    @Override
    public boolean isMatch() {
        return false;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
