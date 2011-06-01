package com.james;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.View;

public class DrawingThread extends Thread {
    public static final int     STATE_READY                = 3;
    public static final int     STATE_RUNNING              = 4;
    private Bitmap              backgroundImage;
    private int                 canvasHeight;
    private int                 canvasWidth;
    private final Handler       handler;
    private int                 readyRunningPauseLoseOrWinState;
    private final SurfaceHolder surfaceHolder;
    private boolean             surfaceIsCreatedAndRunning = false;

    public DrawingThread(SurfaceHolder surfaceHolder, Context context, Handler handler) {
        this.handler = handler;
        Resources res = context.getResources();
        backgroundImage = BitmapFactory.decodeResource(res, R.drawable.earthrise);

        this.surfaceHolder = surfaceHolder;
    }

    public boolean doKeyDown(int keyCode, KeyEvent msg) {
        return false;
    }

    public boolean doKeyUp(int keyCode, KeyEvent msg) {
        return false;
    }

    public void pause() {
    }

    public void restoreState(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run() {
        while (surfaceIsCreatedAndRunning) {
            Canvas c = null;
            try {
                c = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder) {
                    if (readyRunningPauseLoseOrWinState == STATE_RUNNING) {
                        updatePhysics();
                    }
                    doDraw(c);
                }
            } finally {
                // do this in a finally so that if an exception is thrown
                // during the above, we don't leave the Surface in an
                // inconsistent state
                if (c != null) {
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }

    /**
     * Used to signal the thread whether it should be running or not. Passing true allows the thread to run; passing false will shut it down if it's
     * already running. Calling start() after this was most recently called with false will result in an immediate shutdown.
     * 
     * @param b
     *            true to run, false to shut down
     */
    public void setRunning(boolean b) {
        surfaceIsCreatedAndRunning = b;
    }

    public void setState(int mode) {
        synchronized (surfaceHolder) {
            setState(mode, null);
        }
    }

    public void setState(int mode, CharSequence message) {
        /*
         * This method optionally can cause a text message to be displayed to the user when the mode changes. Since the View that actually renders
         * that text is part of the main View hierarchy and not owned by this thread, we can't touch the state of that View. Instead we use a Message
         * + Handler to relay commands to the main thread, which updates the user-text View.
         */
        synchronized (surfaceHolder) {
            readyRunningPauseLoseOrWinState = mode;

            if (readyRunningPauseLoseOrWinState == STATE_RUNNING) {
                Message msg = handler.obtainMessage();
                Bundle b = new Bundle();
                b.putString("text", "");
                b.putInt("viz", View.INVISIBLE);
                msg.setData(b);
                handler.sendMessage(msg);
            } else {

                Message msg = handler.obtainMessage();
                Bundle b = new Bundle();
                b.putString("text", "starting");
                b.putInt("viz", View.VISIBLE);
                msg.setData(b);
                handler.sendMessage(msg);
            }
        }
    }

    public void setSurfaceSize(int width, int height) {
        // synchronized to make sure these all change atomically
        synchronized (surfaceHolder) {
            canvasWidth = width;
            canvasHeight = height;

            // don't forget to resize the background image
            backgroundImage = Bitmap.createScaledBitmap(backgroundImage, width, height, true);
        }
    }

    /**
     * Draws the ship, fuel/speed bars, and background to the provided Canvas.
     */
    private void doDraw(Canvas canvas) {
        // Draw the background image. Operations on the Canvas accumulate
        // so this is like clearing the screen.
        canvas.drawBitmap(backgroundImage, 0, 0, null);

        // int yTop = mCanvasHeight - ((int) mY + mLanderHeight / 2);
        // int xLeft = (int) mX - mLanderWidth / 2;
        //
        // // Draw the fuel gauge
        // int fuelWidth = (int) (UI_BAR * mFuel / PHYS_FUEL_MAX);
        // mScratchRect.set(4, 4, 4 + fuelWidth, 4 + UI_BAR_HEIGHT);
        // canvas.drawRect(mScratchRect, mLinePaint);
        //
        // // Draw the speed gauge, with a two-tone effect
        // double speed = Math.sqrt(mDX * mDX + mDY * mDY);
        // int speedWidth = (int) (UI_BAR * speed / PHYS_SPEED_MAX);
        //
        // if (speed <= mGoalSpeed) {
        // mScratchRect.set(4 + UI_BAR + 4, 4, 4 + UI_BAR + 4 + speedWidth, 4 + UI_BAR_HEIGHT);
        // canvas.drawRect(mScratchRect, mLinePaint);
        // } else {
        // // Draw the bad color in back, with the good color in front of
        // // it
        // mScratchRect.set(4 + UI_BAR + 4, 4, 4 + UI_BAR + 4 + speedWidth, 4 + UI_BAR_HEIGHT);
        // canvas.drawRect(mScratchRect, mLinePaintBad);
        // int goalWidth = UI_BAR * mGoalSpeed / PHYS_SPEED_MAX;
        // mScratchRect.set(4 + UI_BAR + 4, 4, 4 + UI_BAR + 4 + goalWidth, 4 + UI_BAR_HEIGHT);
        // canvas.drawRect(mScratchRect, mLinePaint);
        // }
        //
        // // Draw the landing pad
        // canvas.drawLine(mGoalX, 1 + mCanvasHeight - TARGET_PAD_HEIGHT, mGoalX + mGoalWidth, 1 + mCanvasHeight - TARGET_PAD_HEIGHT, mLinePaint);
        //
        // // Draw the ship with its current rotation
        // canvas.save();
        // canvas.rotate((float) mHeading, (float) mX, mCanvasHeight - (float) mY);
        // if (mMode == STATE_LOSE) {
        // mCrashedImage.setBounds(xLeft, yTop, xLeft + mLanderWidth, yTop + mLanderHeight);
        // mCrashedImage.draw(canvas);
        // } else if (mEngineFiring) {
        // mFiringImage.setBounds(xLeft, yTop, xLeft + mLanderWidth, yTop + mLanderHeight);
        // mFiringImage.draw(canvas);
        // } else {
        // mLanderImage.setBounds(xLeft, yTop, xLeft + mLanderWidth, yTop + mLanderHeight);
        // mLanderImage.draw(canvas);
        // }
        canvas.restore();
    }

    private void updatePhysics() {
        // TODO Auto-generated method stub

    }

}
