package com.james.drag.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * A DragView is a special view used by a DragController. During a drag operation, what is actually moving on the screen is a DragView. A DragView is
 * constructed using a bitmap of the view the user really wants to move.
 */

public class DragView extends View {
    // Number of pixels to add to the dragged item for scaling. Should be even for pixel alignment.
    private static final int           DRAG_SCALE     = 0;   // In Launcher, value is 40

    private final float                animationScale = 1.0f;
    private final Bitmap               bitmap;
    private WindowManager.LayoutParams layoutParams;
    private float                      mScale;

    private final WindowManager        windowManager;
    private Paint                      paint;

    private final int                  registrationX;
    private final int                  registrationY;

    public DragView(Context context, Bitmap bitmap, int registrationX, int registrationY, int left, int top, int width, int height) {
        super(context);

        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        Matrix scale = new Matrix();
        float scaleFactor = width;
        scaleFactor = mScale = (scaleFactor + DRAG_SCALE) / scaleFactor;
        scale.setScale(scaleFactor, scaleFactor);
        this.bitmap = Bitmap.createBitmap(bitmap, left, top, width, height, scale, true);

        // The point in our scaled bitmap that the touch events are located
        this.registrationX = registrationX + DRAG_SCALE / 2;
        this.registrationY = registrationY + DRAG_SCALE / 2;
    }

    public void move(int touchX, int touchY) {
        WindowManager.LayoutParams lp = layoutParams;
        lp.x = touchX - registrationX;
        lp.y = touchY - registrationY;
        windowManager.updateViewLayout(this, lp);
    }

    public void remove() {
        windowManager.removeView(this);
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
        invalidate();
    }

    public void show(IBinder windowToken, int touchX, int touchY) {

        int pixelFormat = PixelFormat.TRANSLUCENT;

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                touchX - registrationX, touchY - registrationY, WindowManager.LayoutParams.TYPE_APPLICATION_SUB_PANEL,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                /* | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM */, pixelFormat);
        // lp.token = mStatusBarView.getWindowToken();
        lp.gravity = Gravity.LEFT | Gravity.TOP;
        lp.token = windowToken;
        lp.setTitle("DragView");
        layoutParams = lp;

        windowManager.addView(this, lp);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        bitmap.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (true) {
            // for debugging
            Paint p = new Paint();
            p.setStyle(Paint.Style.FILL);
            p.setColor(0x88dd0011);
            canvas.drawRect(0, 0, getWidth(), getHeight(), p);
        }
        float scale = animationScale;
        if (scale < 0.999f) { // allow for some float error
            float width = bitmap.getWidth();
            float offset = (width - width * scale) / 2;
            canvas.translate(offset, offset);
            canvas.scale(scale, scale);
        }
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(bitmap.getWidth(), bitmap.getHeight());
    }
}