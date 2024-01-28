package poker.hand.result;

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
