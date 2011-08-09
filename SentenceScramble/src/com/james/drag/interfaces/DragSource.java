package com.james.drag.interfaces;

import android.view.View;

import com.james.drag.controller.DragController;

public interface DragSource {
    void onDropCompleted(View target, boolean success);

    void setDragController(DragController dragger);
}
