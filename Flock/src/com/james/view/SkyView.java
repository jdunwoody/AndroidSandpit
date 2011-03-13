package com.james.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.Shape;
import android.util.Log;
import android.view.View;

public class SkyView extends View {
    private ShapeDrawable drawable;
    private int           x = 0;
    private int           y = 0;

    public SkyView(Context context) {
        super(context);

        drawable = new ShapeDrawable(createBirdShape());
        drawable.getPaint().setColor(Color.RED);
    }

    public void touched(float newX, float newY) {
        Log.i(VIEW_LOG_TAG, "touched");
        this.x = (int) newX;
        this.y = (int) newY;
        this.drawable.setBounds(x - 5, y - 5, x + 5, y + 5);
        invalidate();
    }

    private Shape createBirdShape() {
        Path path = new Path();
        path.moveTo(x, y);
        path.lineTo(x + 5, y + 5);
        path.lineTo(x - 5, y + 5);
        path.lineTo(x, y);

        PathShape pathShape = new PathShape(path, 10, 10);
        return pathShape;
    }

    protected void onDraw(Canvas canvas) {
        drawable.draw(canvas);
    }
}