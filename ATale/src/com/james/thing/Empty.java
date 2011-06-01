package com.james.thing;

public class Empty extends AbstractThing {
    public Empty(ThingRenderer renderer) {
        super(renderer);
    }

    @Override
    public void display() {
        getRenderer().display();
        // return " ";
    }
}
