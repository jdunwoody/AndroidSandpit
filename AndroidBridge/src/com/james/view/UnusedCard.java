package com.james.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

public class UnusedCard extends View {
    private Bitmap      mImage;
    private final Paint mPaint         = new Paint();
    private final Point mSize          = new Point();
    private final Point mStartPosition = new Point();

    public UnusedCard(Context context) {
        super(context);
    }
    //
    // public final Bitmap getImage() {
    // return mImage;
    // }
    //
    // public final Point getPosition() {
    // Rect bounds = mRegion.getBounds();
    // return new Point(bounds.left, bounds.top);
    // }
    //
    // public final Point getSize() {
    // return mSize;
    // }
    //
    // @Override
    // public boolean onTouchEvent(MotionEvent event) {
    // // Is the event inside of this view?
    // if (!mRegion.contains((int) event.getX(), (int) event.getY())) {
    // return super.onTouchEvent(event);
    // }
    //
    // if (event.getAction() == MotionEvent.ACTION_DOWN) {
    // mStartPosition.x = (int) event.getX();
    // mStartPosition.y = (int) event.getY();
    // bringToFront();
    // onSelected();
    // return true;
    // } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
    // int x = 0, y = 0;
    //
    // if (mLock == DirectionLock.FREE || mLock == DirectionLock.HORIZONTAL_ONLY) {
    // x = (int) event.getX() - mStartPosition.x;
    // }
    //
    // if (mLock == DirectionLock.FREE || mLock == DirectionLock.VERTICAL_ONLY) {
    // y = (int) event.getY() - mStartPosition.y;
    // }
    //
    // mRegion.translate(x, y);
    // mStartPosition.x = (int) event.getX();
    // mStartPosition.y = (int) event.getY();
    //
    // invalidate();
    //
    // return true;
    // } else {
    // return super.onTouchEvent(event);
    // }
    // }
    //
    // public final void setImage(Bitmap image) {
    // mImage = image;
    // setSize(mImage.getWidth(), mImage.getHeight());
    // }
    //
    // public final void setPosition(final Point position) {
    // mRegion.set(position.x, position.y, position.x + mSize.x, position.y + mSize.y);
    // }
    //
    // public final void setSize(int width, int height) {
    // mSize.x = width;
    // mSize.y = height;
    //
    // Rect bounds = mRegion.getBounds();
    // mRegion.set(bounds.left, bounds.top, bounds.left + width, bounds.top + height);
    // }
    //
    // @Override
    // protected void onDraw(Canvas canvas) {
    // Point position = getPosition();
    // canvas.drawBitmap(mImage, position.x, position.y, mPaint);
    // }
}