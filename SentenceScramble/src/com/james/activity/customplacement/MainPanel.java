package com.james.activity.customplacement;

import static com.james.activity.customplacement.MyButton.BUTTON_HEIGHT;
import static com.james.activity.customplacement.MyButton.BUTTON_WIDTH;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;

public class MainPanel extends ViewGroup implements OnTouchListener {

    // private static final float MAXP = 0.75f;
    //
    // private static final float MINP = 0.25f;
    private static final float   TOUCH_TOLERANCE = 4;

    private final List<MyButton> buttons;
    private MyButton             currentButton;
    private final Bitmap         mBitmap;
    private final Paint          mBitmapPaint;

    private final Canvas         mCanvas;
    private final Paint          mPaint;

    private final Path           mPath;

    private float                mX, mY;

    public MainPanel(Context c) {
        super(c);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xFFFF0000);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
        mBitmap = Bitmap.createBitmap(320, 480, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        MyButton button = new MyButton(c, Color.RED);
        button.setText("Button");
        button.setOnTouchListener(this);
        addView(button, new AbsoluteLayout.LayoutParams(BUTTON_WIDTH, BUTTON_HEIGHT, 0, 0));

        MyButton button2 = new MyButton(c, Color.BLUE);
        button2.setText("Button2");
        button2.setWidth(200);
        button2.setHeight(50);
        button2.setOnTouchListener(this);
        addView(button2, new AbsoluteLayout.LayoutParams(BUTTON_WIDTH, BUTTON_HEIGHT, 0, BUTTON_HEIGHT * 2));

        buttons = new LinkedList<MyButton>();
        buttons.add(button);
        buttons.add(button2);
    }

    @Override
    public boolean onTouch(View view, MotionEvent me) {
        if (!(view instanceof MyButton)) {
            return true;
        }

        if (me.getAction() == MotionEvent.ACTION_DOWN) {
            currentButton = (MyButton) view;
            currentButton.setBackgroundColor(Color.GREEN);
            // draggingButton.setVisibility(View.INVISIBLE);
            // panel.setVisibility(View.VISIBLE);
        }

        if (me.getAction() == MotionEvent.ACTION_UP) {
            if (currentButton != null) {
                log("Action_up: " + (int) me.getX() + ", " + (int) me.getY());
                currentButton.setLayoutParams(new AbsoluteLayout.LayoutParams(BUTTON_WIDTH, BUTTON_HEIGHT, (int) me.getX(), (int) me.getY()));
                // draggingButton.setVisibility(View.VISIBLE);
                currentButton.setBackgroundColor(Color.WHITE);
                currentButton.invalidate();
                currentButton = null;
                // panel.setVisibility(View.INVISIBLE);
            }
        } else if (me.getAction() == MotionEvent.ACTION_MOVE) {
            currentButton.set((int) me.getX(), (int) me.getY());
            log("Action_move: " + (int) me.getX() + ", " + (int) me.getY());
            // panel.invalidate();
        } else if (me.getAction() == MotionEvent.ACTION_CANCEL) {
            currentButton.setLayoutParams(new AbsoluteLayout.LayoutParams(BUTTON_WIDTH, BUTTON_HEIGHT, 0, 0));
            currentButton.invalidate();
            currentButton = null;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(0xFFAAAAAA);

        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);

        canvas.drawPath(mPath, mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (MyButton button : buttons) {
            int x = button.x;
            int y = button.y;
            button.layout(x, y, MyButton.BUTTON_WIDTH, MyButton.BUTTON_HEIGHT);
        }
    }

    //
    // @Override
    // protected void onDraw(Canvas canvas) {
    // super.onDraw(canvas);
    // canvas.drawRect(x, y, x + BUTTON_WIDTH, y + BUTTON_HEIGHT, paint);
    // invalidate();
    // }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void log(String message) {
        Log.d("MainPanel", message);
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_up() {
        mPath.lineTo(mX, mY);
        // commit the path to our offscreen
        mCanvas.drawPath(mPath, mPaint);
        // kill this so we don't double draw
        mPath.reset();
    }
}