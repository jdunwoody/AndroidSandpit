package com.camellia.domain;

public class ContactPoint {
    private final String           text;
    private final ContactPointType type;

    public ContactPoint(ContactPointType type, String text) {
        this.type = type;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public ContactPointType getType() {
        return type;
    }
}