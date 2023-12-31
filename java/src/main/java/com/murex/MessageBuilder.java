package com.murex;

public class MessageBuilder {

    String status = "";

    public MessageBuilder() {

    }

    public static MessageBuilder aMessage() {
        return new MessageBuilder();
    }

    public static Result aTieResult() {
        return null;
    }

    public String buildMessage() {
        return "";
    }
}
