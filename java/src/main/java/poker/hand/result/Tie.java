package poker.hand.result;

public class Tie extends Result {

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
