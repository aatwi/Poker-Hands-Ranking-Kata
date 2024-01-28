package poker.hand.result;

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
