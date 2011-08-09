package com.james.drag.controller;

import com.james.drag.interfaces.DragSource;

interface DragListener {
    void onDragEnd();

    void onDragStart(DragSource source, Object info, int dragAction);
}
