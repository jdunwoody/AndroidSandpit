package com.james.view;

import static com.james.logging.Logging.log;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class CardView {
    public int             x;
    private final int      height;
    private final Drawable image;
    private final int      width;
    int                    y;

    public CardView(int width, int height, Drawable image) {
        this.width = width;
        this.height = height;
        this.image = image;
        x = 0;
        y = 0;
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
}