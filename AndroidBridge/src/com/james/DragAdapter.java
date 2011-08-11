package com.james;

import android.view.MotionEvent;

public class DragAdapter {
    private static final int     DRAG_LEFT   = 1;
    private static final int     DRAG_RIGHT  = 2;
    private static final float   MIN_CHANGE  = 5;

    private static final int     NO_DRAGGING = 3;
    private boolean              isDragging  = false;
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
                    switch (isDraggedEnough(event)) {
                        case DRAG_LEFT:
                            notifier.dragLeft();
                            isDragging = false;
                            break;
                        case DRAG_RIGHT:
                            notifier.dragRight();
                            isDragging = false;
                            break;
                        default:
                            break;
                    }
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (isDragging) {
                    switch (isDraggedEnough(event)) {
                        case DRAG_LEFT:
                            notifier.dragLeft();
                            break;
                        case DRAG_RIGHT:
                            notifier.dragRight();
                            break;
                        default:
                            break;
                    }
                    isDragging = false;
                }
                return true;
        }
        return false;
    }

    private int isDraggedEnough(MotionEvent event) {
        float xDifference = event.getX() - startX;
        float absXDifference = Math.abs(xDifference);
        float yDifference = event.getY() - startY;
        float absYDifference = Math.abs(yDifference);

        if (absXDifference < MIN_CHANGE && absYDifference < MIN_CHANGE) {
            return NO_DRAGGING;
        }

        if (absXDifference >= absYDifference) {
            if (xDifference >= 0) {
                return DRAG_RIGHT;
            } else {
                return DRAG_LEFT;
            }
        }
        return NO_DRAGGING;
    }
}