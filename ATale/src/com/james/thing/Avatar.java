package com.james.thing;

public class Avatar extends AbstractThing {
    public static final int EAST      = 1;
    public static final int NORTH     = 0;
    public static final int SOUTH     = 2;
    public static final int WEST      = 3;
    public int              direction = 0;
    public int              x;
    public int              y;

    public Avatar(ThingRenderer renderer, int x, int y) {
        super(renderer);
        this.x = x;
        this.y = y;
    }

    @Override
    public void display() {
        getRenderer().display();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Avatar";
    }

    public void turnLeft() {
        direction = (direction + 3) % 4;
    }

    public void turnRight() {
        direction = (direction + 1) % 4;
    }
}
