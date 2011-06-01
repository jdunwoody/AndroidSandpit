package com.james.thing;

public class Wall implements Thing {
    private final ThingRenderer renderer;

    public Wall(ThingRenderer renderer) {
        this.renderer = renderer;
    }

    // @Override
    // public String display() {
    // return "+";
    // }
}
