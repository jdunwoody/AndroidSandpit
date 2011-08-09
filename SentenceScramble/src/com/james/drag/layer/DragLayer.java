package com.james.drag.layer;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.james.drag.DragActivity;
import com.james.drag.controller.DragController;
import com.james.drag.interfaces.DragSource;
import com.james.drag.interfaces.DropTarget;
import com.james.drag.layout.MyAbsoluteLayout;
import com.james.drag.view.DragView;

public class DragLayer extends MyAbsoluteLayout implements DragSource, DropTarget {
    DragController dragController;

    public DragLayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean acceptDrop(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo) {
        return true;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return dragController.dispatchKeyEvent(event) || super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchUnhandledMove(View focused, int direction) {
        return dragController.dispatchUnhandledMove(focused, direction);
    }

    @Override
    public Rect estimateDropLocation(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo, Rect recycle) {
        return null;
    }

    @Override
    public void onDragEnter(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo) {
    }

    @Override
    public void onDragExit(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo) {
    }

    @Override
    public void onDragOver(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo) {
    }

    @Override
    public void onDrop(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo) {
        View v = (View) dragInfo;
        toast("DragLayer2.onDrop accepts view: " + v.getId() + "x, y, xO, yO :" + new Integer(x) + ", " + new Integer(y) + ", "
                + new Integer(xOffset) + ", " + new Integer(yOffset));

        int w = v.getWidth();
        int h = v.getHeight();
        int left = x - xOffset;
        int top = y - yOffset;
        DragLayer.LayoutParams lp = new DragLayer.LayoutParams(w, h, left, top);
        updateViewLayout(v, lp);
    }

    @Override
    public void onDropCompleted(View target, boolean success) {
        toast("DragLayer2.onDropCompleted: " + target.getId() + " Check that the view moved.");
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragController.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return dragController.onTouchEvent(ev);
    }

    @Override
    public void setDragController(DragController controller) {
        dragController = controller;
    }

    public void toast(String msg) {
        if (!DragActivity.Debugging) {
            return;
        }
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

}
