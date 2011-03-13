package com.james.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.util.Log;
import android.view.View;

public class CustomDrawableView extends View {
    private ShapeDrawable drawable;
    private int           x = 0;
    private int           y = 0;

    public CustomDrawableView(Context context) {
        super(context);

        drawable = new ShapeDrawable(createBirdShape());
        drawable.getPaint().setColor(Color.RED);
        drawable.setBounds(x, y, 100, 100);
    }

    public void touched(float newX, float newY) {
        Log.i(VIEW_LOG_TAG, "touched");
        this.x = (int) newX;
        this.y = (int) newY;
        this.drawable.setBounds(x, y, x * 10, y * 10);
    }

    private Shape createBirdShape() {
        Shape shape = new OvalShape();
        return shape;
        // Path path = new Path();
        // path.lineTo(0, 5);
        // path.lineTo(5, 0);
        // path.lineTo(-5, 5);
        // path.lineTo(0, 5);
        //
        // PathShape pathShape = new PathShape(path, 10, 10);
        // return pathShape;
    }

    protected void onDraw(Canvas canvas) {
        drawable.draw(canvas);
    }
}