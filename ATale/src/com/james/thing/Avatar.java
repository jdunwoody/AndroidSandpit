package com.james.thing;

public class Avatar implements Thing {
    public static final int NORTH     = 0;
    public static final int EAST      = 1;
    public static final int SOUTH     = 2;
    public static final int WEST      = 3;
    public int              x;
    public int              y;
    public int              direction = 0;

    public Avatar(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void turnRight() {
        direction = (direction + 1) % 4;
    }

    public void turnLeft() {
        direction = (direction + 3) % 4;
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
    public String display() {
        return "*";
    }

    @Override
    public String toString() {
        return "Avatar";
    }
}
