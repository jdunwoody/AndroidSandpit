package com.james.fingerpaint.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.james.fingerpaint.domain.Node;
import com.james.fingerpaint.domain.Nodes;

public class MyView extends View {
    private final Paint bitmapPaint;
    private final Nodes nodes;
    private float       mX;
    private float       mY;
    private Node        last = null;

    public MyView(Context c) {
        super(c);
        nodes = new Nodes();
        bitmapPaint = new Paint(Paint.DITHER_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        bitmapPaint.setColor(Color.RED);

        for (Node message : nodes.getNodes()) {
            canvas.drawCircle(message.getX(), message.getY(), 10, bitmapPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        this.mX = x;
        this.mY = y;
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

    private void touch_start(float x, float y) {
        Node touched = nodes.matches(x, y);
        if (touched == null) {
            last = new Node(x, y);
            nodes.add(last);
        } else {
            last = touched;
        }
    }

    private void touch_move(float x, float y) {
        last.set(x, y);
    }

    private void touch_up() {
        last.set(mX, mY);
    }
}