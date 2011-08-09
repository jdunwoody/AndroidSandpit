package com.james.drag;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.james.R;
import com.james.drag.controller.DragController;
import com.james.drag.layer.DragLayer;

public class DragActivity extends Activity implements
// View.OnLongClickListener,
        View.OnClickListener
// View.OnTouchListener
{
    public static final boolean Debugging = true;
    private DragController      dragController;  // Object that sends out drag-drop events while a view is being moved.
    private DragLayer           mDragLayer;      // The ViewGroup that supports drag-drop.

    @Override
    public void onClick(View v) {
        Log.i("drag", "Activity.onClick");
        toast("You clicked. Try a long click");

        startDrag(v);
    }

    // @Override
    // public boolean onLongClick(View v) {
    // trace("onLongClick in view: " + v);
    //
    // // // Make sure the drag was started by a long press as opposed to a long click.
    // // // (Note: I got this from the Workspace object in the Android Launcher code.
    // // // I think it is here to ensure that the device is still in touch mode as we start the drag operation.)
    // // if (!v.isInTouchMode()) {
    // // toast("isInTouchMode returned false. Try touching the view again.");
    // return false;
    // // }
    // // return startDrag(v);
    // }

    // @Override
    // public boolean onTouch(View v, MotionEvent me) {
    // if (me.getAction() == MotionEvent.ACTION_DOWN) {
    // return startDrag(v);
    // // status = START_DRAGGING;
    // // image = new ImageView(this);
    // // image.setImageBitmap(btn.getDrawingCache());
    // // layout.addView(image, params);
    // }
    // // if (me.getAction() == MotionEvent.ACTION_UP) {
    // // status = STOP_DRAGGING;
    // // // Log.i("Drag", "Stopped Dragging");
    // // } else if (me.getAction() == MotionEvent.ACTION_MOVE) {
    // // if (status == START_DRAGGING) {
    // // System.out.println("Dragging");
    // // image.setPadding((int) me.getRawX(), (int) me.getRawY(), 0, 0);
    // // image.invalidate();
    // // }
    // // }
    //
    // return false;
    // }

    public boolean startDrag(View v) {
        Log.d("drag", "Activity.startDrag");
        // Let the DragController initiate a drag-drop sequence.
        // I use the dragInfo to pass along the object being dragged.
        // I'm not sure how the Launcher designers do this.
        Object dragInfo = v;
        dragController.startDrag(v, mDragLayer, dragInfo, DragController.DRAG_ACTION_MOVE);
        return true;
    }

    public void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void trace(String msg) {
        if (!Debugging) {
            return;
        }
        Log.d("DragActivity", msg);
        toast(msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dragController = new DragController(this);

        setContentView(R.layout.main);
        setupViews();
    }

    private void setupViews() {
        DragController dragController = this.dragController;

        mDragLayer = (DragLayer) findViewById(R.id.drag_layer);
        mDragLayer.setDragController(dragController);
        dragController.addDropTarget(mDragLayer);

        ImageView i1 = (ImageView) findViewById(R.id.Image1);
        ImageView i2 = (ImageView) findViewById(R.id.Image2);

        // i1.setOnTouchListener(this);
        i1.setOnClickListener(this);
        // i1.setOnLongClickListener(this);

        // i2.setOnTouchListener(this);
        i2.setOnClickListener(this);
        // i2.setOnLongClickListener(this);

        // TextView tv = (TextView) findViewById(R.id.Text1);
        // tv.setOnTouchListener(this);
        // tv.setOnLongClickListener(this);

        Toast.makeText(getApplicationContext(), "Press and hold to drag a view", Toast.LENGTH_LONG).show();
    }
}
