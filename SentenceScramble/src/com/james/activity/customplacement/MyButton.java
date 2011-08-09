package com.james.activity.customplacement;

import android.content.Context;
import android.graphics.Paint;
import android.widget.Button;

public class MyButton extends Button {
    public static final int BUTTON_HEIGHT = 50;
    public static final int BUTTON_WIDTH  = 200;

    private final Paint     paint;
    int                     x;
    int                     y;

    public MyButton(Context context, int color) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(color);
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
