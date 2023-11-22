package com.murex;

public record Result(boolean isMatch, String message) {

    public static Result aMatchResult(String message) {
        return new Result(true, message);
    }
    public static Result aNoMatchResult() {
        return new Result(false, "");
    }
}
