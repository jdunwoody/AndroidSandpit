package com.james;

import android.view.MotionEvent;

public interface DragNotifiable {

    void drag(Direction direction);

    boolean onTouchEvent(MotionEvent event);
}