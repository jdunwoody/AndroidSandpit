package com.james;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class WordAsCustomButtonActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final FrameLayout mainLayout = new FrameLayout(this);

        this.setContentView(mainLayout);
        View customView = new CustomView(this);
        mainLayout.addView(customView);
    }
}

class CustomView extends View {

    private final Paint paint;

    public CustomView(Context context) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(10, 10, 200, 200, paint);
        invalidate();
    }
}
