package com.james.graphics.imageview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class CustomDrawableActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.addView(new CustomDrawableView(this));

        setContentView(layout);
    }
}

class CustomDrawableView extends View {

    private final ShapeDrawable drawable;

    public CustomDrawableView(Context context) {
        super(context);

        drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setColor(Color.GREEN);
        drawable.setBounds(10, 10, 200, 300);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawable.draw(canvas);
    }
}