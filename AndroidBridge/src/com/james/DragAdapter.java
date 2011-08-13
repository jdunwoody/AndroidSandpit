package com.james;

import static com.james.Direction.DRAG_DOWN;
import static com.james.Direction.DRAG_LEFT;
import static com.james.Direction.DRAG_RIGHT;
import static com.james.Direction.DRAG_UP;
import static com.james.Direction.NONE;
import android.view.MotionEvent;

public class DragAdapter {

    private static final float   MIN_CHANGE = 5;
    private boolean              isDragging = false;
    private final DragNotifiable notifier;
    private float                startX;
    private float                startY;

    public DragAdapter(DragNotifiable notifier) {
        this.notifier = notifier;
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDragging = true;
                startX = event.getX();
                startY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                if (isDragging) {
                    Direction direction = isDraggedEnough(event);
                    if (direction != NONE) {
                        isDragging = false;
                        notifier.drag(direction);
                    }
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (isDragging) {
                    Direction direction = isDraggedEnough(event);
                    notifier.drag(direction);
                    isDragging = false;
                }
                return true;
        }
        return false;
    }

    private Direction isDraggedEnough(MotionEvent event) {
        float xDifference = event.getX() - startX;
        float absXDifference = Math.abs(xDifference);
        float yDifference = event.getY() - startY;
        float absYDifference = Math.abs(yDifference);

        if (absXDifference < MIN_CHANGE && absYDifference < MIN_CHANGE) {
            return NONE;
        }

        if (absXDifference >= absYDifference) {
            if (xDifference >= 0) {
                return DRAG_RIGHT;
            } else {
                return DRAG_LEFT;
            }
        } else {
            if (yDifference >= 0) {
                return DRAG_DOWN;
            } else {
                return DRAG_UP;
            }
        }
    }
}
