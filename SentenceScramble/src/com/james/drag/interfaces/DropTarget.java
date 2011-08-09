package com.james.drag.interfaces;

import android.graphics.Rect;

import com.james.drag.interfaces.DragSource;
import com.james.drag.view.DragView;

public interface DropTarget {

    boolean acceptDrop(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo);

    Rect estimateDropLocation(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo, Rect recycle);

    // These methods are implemented in Views
    void getHitRect(Rect outRect);

    int getLeft();

    void getLocationOnScreen(int[] loc);

    int getTop();

    void onDragEnter(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo);

    void onDragExit(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo);

    void onDragOver(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo);

    void onDrop(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo);
}
