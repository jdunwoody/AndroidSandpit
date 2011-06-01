package com.james.thing;

public abstract class AbstractThing implements Thing {
    private final ThingRenderer renderer;

    public AbstractThing(ThingRenderer renderer) {
        this.renderer = renderer;
    }

    protected ThingRenderer getRenderer() {
        return renderer;
    }
}
