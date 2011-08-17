package com.james.view;

public class Screen {
    private final int height;
    private final int width;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;

    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}