package com.james.graphics.imageview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;

import com.james.R;

public class DrawableOverBackgroundActivity extends Activity {
    public static final String LOG_CATEGORY = "DrawableOverBackgroundActivity";
    private DisplayMetrics     metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = new FrameLayout(this);

        getDisplayMatrics();

        frameLayout.addView(new DrawableOverBackgroundView(this));
        frameLayout.addView(new AvatarView(this, metrics));

        setContentView(frameLayout);
    }

    private void getDisplayMatrics() {
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
    }
}

class AvatarController implements OnTouchListener {
    private static final float TOUCH_TOLERANCE = 4;
    private boolean            determiningDragDirection;
    private float              startX, startY;
    private final AvatarView   view;

    public AvatarController(AvatarView view) {
        this.view = view;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                view.invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                view.invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                view.invalidate();
                break;
        }
        return true;
    }

    private void touch_move(float x, float y) {
        if (determiningDragDirection) {
            float dx = Math.abs(x - startX);
            float dy = Math.abs(y - startY);

            if (Math.abs(dx) >= TOUCH_TOLERANCE || Math.abs(dy) >= TOUCH_TOLERANCE) {
                if (dx > dy) {
                    if (x - startX >= 0) {
                        view.moveRight();
                    } else {
                        view.moveLeft();
                    }
                } else {
                    if (y - startY >= 0) {
                        view.moveDown();
                    } else {
                        view.moveUp();
                    }
                }
                determiningDragDirection = false;
            }
        }
    }

    private void touch_start(float x, float y) {
        determiningDragDirection = true;
        startX = x;
        startY = y;
    }

    private void touch_up() {
        determiningDragDirection = false;
    }
}

class AvatarView extends View {
    private static final int HEIGHT = 800;
    private static final int WIDTH  = 480;
    private final int        avatarHeight;
    private final Bitmap     avatarImage;
    private final int        avatarWidth;
    private int              x;
    private int              y;

    public AvatarView(Context context, DisplayMetrics metrics) {
        super(context);
        x = 0;
        y = 0;
        avatarImage = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
        avatarWidth = avatarImage.getWidth();// ScaledWidth(metrics);
        avatarHeight = avatarImage.getHeight();// ScaledHeight(metrics);
        Log.i(DrawableOverBackgroundActivity.LOG_CATEGORY, "AvatarWidth: " + avatarWidth);
        Log.i(DrawableOverBackgroundActivity.LOG_CATEGORY, "AvatarHeight: " + avatarHeight);
        setOnTouchListener(new AvatarController(this));
    }

    public void moveDown() {
        Log.i(DrawableOverBackgroundActivity.LOG_CATEGORY, "down");
        if (y + avatarHeight < HEIGHT) {
            if (y + avatarHeight > HEIGHT) {
                y = HEIGHT - avatarHeight;
            } else {
                y += avatarHeight;
            }
            invalidate();
        }
    }

    public void moveLeft() {
        Log.i(DrawableOverBackgroundActivity.LOG_CATEGORY, "left");
        if (x > 0) {
            if (x - avatarWidth <= 0) {
                x = 0;
            } else {
                x -= avatarWidth;
            }
            invalidate();
        }
    }

    public void moveRight() {
        Log.i(DrawableOverBackgroundActivity.LOG_CATEGORY, "right");
        if (x + avatarWidth < WIDTH) {
            if (x + avatarWidth > WIDTH) {
                x = WIDTH - avatarWidth;
            } else {
                x += avatarWidth;
            }
            invalidate();
        }
    }

    public void moveUp() {
        Log.i(DrawableOverBackgroundActivity.LOG_CATEGORY, "up");
        if (y > 0) {
            if (y - avatarHeight <= 0) {
                y = 0;
            } else {
                y -= avatarHeight;
            }
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(DrawableOverBackgroundActivity.LOG_CATEGORY, "Drawing: " + x + ", " + y);
        canvas.drawBitmap(avatarImage, x, y, null);
    }
}

class DrawableOverBackgroundView extends View {
    private final Bitmap backgroundImage;

    public DrawableOverBackgroundView(Context context) {
        super(context);
        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.hayley_480px);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(backgroundImage, 0, 0, null);
    }
}
// @Override
// public boolean onTrackballEvent(MotionEvent event) {
// float x = event.getX();
// float y = event.getY();
// Log.i("DrawableOverBackgroundActivity", "Activity: Trackball Event: " + x + ", " + y);
//
// return true;
// }
// @Override
// public boolean onTrackballEvent(MotionEvent event) {
// float x = event.getX();
// float y = event.getY();
// Log.i("DrawableOverBackgroundActivity", "View: Trackball Event: " + x + ", " + y);
//
// return true;
// }
// private ImageView createMainImage() {
// ImageView imageView = new ImageView(this);
// imageView.setImageResource(R.drawable.hayley_480px);
// imageView.setAdjustViewBounds(true);
// imageView.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
// return imageView;
// }

// class AvatarImage {
//
// }

// class AvatarView extends View {
// private final ShapeDrawable drawable;
//
// public AvatarView(Context context) {
// super(context);
//
// drawable = new ShapeDrawable(new AvatarImage());
// drawable.getPaint().setColor(Color.GREEN);
// drawable.setBounds(10, 10, 200, 300);
// }
//
// @Override
// protected void onDraw(Canvas canvas) {
// drawable.draw(canvas);
// }
// }
