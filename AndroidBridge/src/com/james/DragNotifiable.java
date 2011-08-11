package com.james;

import android.view.MotionEvent;

public interface DragNotifiable {

    void dragLeft();

    void dragRight();

    boolean onTouchEvent(MotionEvent event);
}