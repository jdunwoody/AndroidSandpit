package com.james.view;

import static com.james.logging.Logging.log;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class CardView {
    public int             x;
    private final int      height;
    private final Drawable image;
    private final Screen   screen;
    private final int      width;
    int                    y;

    public CardView(Screen screen, int width, int height, Drawable image) {
        this.screen = screen;
        this.width = width;
        this.height = height;
        this.image = image;
        x = 0;
        y = 0;
        image.setBounds(x, y, width, height);
    }

    public void draw(Canvas canvas) {
        image.draw(canvas);
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        int left = x;
        int top = y;
        int right = x + width;
        int bottom = y + height;

        image.setBounds(left, top, right, bottom);

        log("Moving to " + x + ", " + y);
    }

    public void moveToBottom() {
        move(screen.getWidth() / 2, screen.getHeight());
    }

    public void moveToLeft() {
        move(0, screen.getHeight() / 2);
    }

    public void moveToRight() {
        move(screen.getWidth() - width, screen.getHeight() / 2);
    }

    public void moveToTop() {
        move(screen.getWidth() / 2, 0);
    }
}