package com.james.thing;

public class Monster implements Thing {

    private String     name;
    private final char icon;

    public Monster(String name, char icon) {
        this.name = name;
        this.icon = icon;
    }

    public String toString() {
        return "icon: " + icon + " name: " + name;
    }

    public String display() {
        return String.valueOf(icon);
    }
}