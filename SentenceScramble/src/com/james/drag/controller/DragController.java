package com.james.drag.controller;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.james.drag.interfaces.DragSource;
import com.james.drag.interfaces.DropTarget;
import com.james.drag.view.DragView;

/**
 * This class is used to initiate a drag within a view or across multiple views. When a drag starts it creates a special view (a DragView) that moves
 * around the screen until the user ends the drag. As feedback to the user, this object causes the device to vibrate as the drag begins.
 * 
 */

public class DragController {

    public static int            DRAG_ACTION_COPY            = 1;
    public static int            DRAG_ACTION_MOVE            = 0;
    private static final boolean PROFILE_DRAWING_DURING_DRAG = false;
    private static final String  TAG                         = "DragController";
    private static final int     VIBRATE_DURATION            = 35;

    private static int clamp(int val, int min, int max) {
        if (val < min) {
            return min;
        } else if (val >= max) {
            return max - 1;
        } else {
            return val;
        }
    }

    private final Context               context;
    private final int[]                 coordinatesTemp = new int[2];
    private final DisplayMetrics        displayMetrics  = new DisplayMetrics();
    private boolean                     dragging;
    private Object                      dragInfo;
    private DragView                    dragView;
    private final ArrayList<DropTarget> dropTargets     = new ArrayList<DropTarget>();
    private InputMethodManager          inputMethodManager;
    private DropTarget                  lastDropTarget;
    private DragListener                listener;
    private DragSource                  dragSource;
    private View                        moveTarget;
    private float                       motionDownX;
    private float                       motionDownY;
    private IBinder                     windowToken;
    private View                        originator;
    // temporaries to avoid gc thrash
    private final Rect                  rectTemp        = new Rect();
    private float                       touchOffsetX;
    private float                       touchOffsetY;
    private final Vibrator              vibrator;

    public DragController(Context context) {
        this.context = context;
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

    }

    public void addDropTarget(DropTarget target) {
        dropTargets.add(target);
    }

    public void cancelDrag() {
        endDrag();
    }

    /**
     * Call this from a drag source view like this:
     * 
     * <pre>
     *  @Override
     *  public boolean dispatchKeyEvent(KeyEvent event) {
     *      return mDragController.dispatchKeyEvent(this, event)
     *              || super.dispatchKeyEvent(event);
     * </pre>
     */
    public boolean dispatchKeyEvent(KeyEvent event) {
        return dragging;
    }

    public boolean dispatchUnhandledMove(View focused, int direction) {
        return moveTarget != null && moveTarget.dispatchUnhandledMove(focused, direction);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            recordScreenSize();
        }

        final int screenX = clamp((int) ev.getRawX(), 0, displayMetrics.widthPixels);
        final int screenY = clamp((int) ev.getRawY(), 0, displayMetrics.heightPixels);

        switch (action) {
            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_DOWN:
                // Remember location of down touch
                motionDownX = screenX;
                motionDownY = screenY;
                lastDropTarget = null;
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (dragging) {
                    drop(screenX, screenY);
                }
                endDrag();
                break;
        }

        return dragging;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (!dragging) {
            return false;
        }

        final int action = ev.getAction();
        final int screenX = clamp((int) ev.getRawX(), 0, displayMetrics.widthPixels);
        final int screenY = clamp((int) ev.getRawY(), 0, displayMetrics.heightPixels);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d("drag", "controller.touch down");
                // Remember where the motion event started
                motionDownX = screenX;
                motionDownY = screenY;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("drag", "controller.touch move");
                // Update the drag view. Don't use the clamped pos here so the dragging looks
                // like it goes off screen a little, intead of bumping up against the edge.
                dragView.move((int) ev.getRawX(), (int) ev.getRawY());
                // Drop on someone?
                final int[] coordinates = coordinatesTemp;
                DropTarget dropTarget = findDropTarget(screenX, screenY, coordinates);
                if (dropTarget != null) {
                    if (lastDropTarget == dropTarget) {
                        dropTarget
                                .onDragOver(dragSource, coordinates[0], coordinates[1], (int) touchOffsetX, (int) touchOffsetY, dragView, dragInfo);
                    } else {
                        if (lastDropTarget != null) {
                            lastDropTarget.onDragExit(dragSource, coordinates[0], coordinates[1], (int) touchOffsetX, (int) touchOffsetY, dragView,
                                    dragInfo);
                        }
                        dropTarget.onDragEnter(dragSource, coordinates[0], coordinates[1], (int) touchOffsetX, (int) touchOffsetY, dragView,
                                dragInfo);
                    }
                } else {
                    if (lastDropTarget != null) {
                        lastDropTarget.onDragExit(dragSource, coordinates[0], coordinates[1], (int) touchOffsetX, (int) touchOffsetY, dragView,
                                dragInfo);
                    }
                }
                lastDropTarget = dropTarget;

                /*
                 * The original Launcher activity supports a delete region and scrolling. It is not needed in this example.
                 * 
                 * // Scroll, maybe, but not if we're in the delete region. boolean inDeleteRegion = false; if (mDeleteRegion != null) {
                 * inDeleteRegion = mDeleteRegion.contains(screenX, screenY); } //Log.d(TAG, "inDeleteRegion=" + inDeleteRegion + " screenX=" +
                 * screenX // + " mScrollZone=" + mScrollZone); if (!inDeleteRegion && screenX < mScrollZone) { if (mScrollState ==
                 * SCROLL_OUTSIDE_ZONE) { mScrollState = SCROLL_WAITING_IN_ZONE; mScrollRunnable.setDirection(SCROLL_LEFT);
                 * mHandler.postDelayed(mScrollRunnable, SCROLL_DELAY); } } else if (!inDeleteRegion && screenX > scrollView.getWidth() - mScrollZone)
                 * { if (mScrollState == SCROLL_OUTSIDE_ZONE) { mScrollState = SCROLL_WAITING_IN_ZONE; mScrollRunnable.setDirection(SCROLL_RIGHT);
                 * mHandler.postDelayed(mScrollRunnable, SCROLL_DELAY); } } else { if (mScrollState == SCROLL_WAITING_IN_ZONE) { mScrollState =
                 * SCROLL_OUTSIDE_ZONE; mScrollRunnable.setDirection(SCROLL_RIGHT); mHandler.removeCallbacks(mScrollRunnable); } }
                 */
                break;
            case MotionEvent.ACTION_UP:
                Log.d("drag", "controller.touch up");
                if (dragging) {
                    drop(screenX, screenY);
                }
                endDrag();

                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("drag", "controller.touch cancel");
                cancelDrag();
        }

        return true;
    }

    /**
     * Remove a previously installed drag listener.
     */
    public void removeDragListener(DragListener l) {
        listener = null;
    }

    /**
     * Don't send drop events to <em>target</em> any more.
     */
    public void removeDropTarget(DropTarget target) {
        dropTargets.remove(target);
    }

    public void setDragListener(DragListener l) {
        listener = l;
    }

    public void setWindowToken(IBinder token) {
        windowToken = token;
    }

    public void startDrag(Bitmap b, int screenX, int screenY, int textureLeft, int textureTop, int textureWidth, int textureHeight,
            DragSource source, Object dragInfo, int dragAction) {
        Log.d("drag", "controller.startDrag 1");
        if (PROFILE_DRAWING_DURING_DRAG) {
            android.os.Debug.startMethodTracing("Launcher");
        }

        // Hide soft keyboard, if visible
        if (inputMethodManager == null) {
            inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0);

        if (listener != null) {
            listener.onDragStart(source, dragInfo, dragAction);
        }

        int registrationX = (int) motionDownX - screenX;
        int registrationY = (int) motionDownY - screenY;

        touchOffsetX = motionDownX - screenX;
        touchOffsetY = motionDownY - screenY;

        dragging = true;
        dragSource = source;
        this.dragInfo = dragInfo;

        vibrator.vibrate(VIBRATE_DURATION);
        dragView = new DragView(context, b, registrationX, registrationY, textureLeft, textureTop, textureWidth, textureHeight);
        dragView.show(windowToken, (int) motionDownX, (int) motionDownY);
    }

    public void startDrag(View v, DragSource source, Object dragInfo, int dragAction) {
        Log.d("drag", "controller.startDrag 2");

        originator = v;

        Bitmap b = getViewBitmap(v);

        if (b == null) {
            // out of memory?
            return;
        }

        int[] loc = coordinatesTemp;
        v.getLocationOnScreen(loc);
        int screenX = loc[0];
        int screenY = loc[1];

        startDrag(b, screenX, screenY, 0, 0, b.getWidth(), b.getHeight(), source, dragInfo, dragAction);

        b.recycle();

        if (dragAction == DRAG_ACTION_MOVE) {
            v.setVisibility(View.GONE);
        }
    }

    private boolean drop(float x, float y) {

        final int[] coordinates = coordinatesTemp;
        DropTarget dropTarget = findDropTarget((int) x, (int) y, coordinates);

        if (dropTarget != null) {
            dropTarget.onDragExit(dragSource, coordinates[0], coordinates[1], (int) touchOffsetX, (int) touchOffsetY, dragView, dragInfo);
            if (dropTarget.acceptDrop(dragSource, coordinates[0], coordinates[1], (int) touchOffsetX, (int) touchOffsetY, dragView, dragInfo)) {
                dropTarget.onDrop(dragSource, coordinates[0], coordinates[1], (int) touchOffsetX, (int) touchOffsetY, dragView, dragInfo);
                dragSource.onDropCompleted((View) dropTarget, true);
                return true;
            } else {
                dragSource.onDropCompleted((View) dropTarget, false);
                return true;
            }
        }
        return false;
    }

    private void endDrag() {
        if (dragging) {
            dragging = false;
            if (originator != null) {
                originator.setVisibility(View.VISIBLE);
            }
            if (listener != null) {
                listener.onDragEnd();
            }
            if (dragView != null) {
                dragView.remove();
                dragView = null;
            }
        }
    }

    private DropTarget findDropTarget(int x, int y, int[] dropCoordinates) {
        final Rect r = rectTemp;

        final ArrayList<DropTarget> dropTargets = this.dropTargets;
        final int count = dropTargets.size();
        for (int i = count - 1; i >= 0; i--) {
            final DropTarget target = dropTargets.get(i);
            target.getHitRect(r);
            target.getLocationOnScreen(dropCoordinates);
            r.offset(dropCoordinates[0] - target.getLeft(), dropCoordinates[1] - target.getTop());
            if (r.contains(x, y)) {
                dropCoordinates[0] = x - dropCoordinates[0];
                dropCoordinates[1] = y - dropCoordinates[1];
                return target;
            }
        }
        return null;
    }

    private Bitmap getViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);

        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);

        // Reset the drawing cache background color to fully transparent
        // for the duration of this operation
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);

        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            Log.e(TAG, "failed getViewBitmap(" + v + ")", new RuntimeException());
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        // Restore the view
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);

        return bitmap;
    }

    /**
     * Get the screen size so we can clamp events to the screen size so even if you drag off the edge of the screen, we find something.
     */
    private void recordScreenSize() {
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
    }

    void setMoveTarget(View view) {
        moveTarget = view;
    }

}
