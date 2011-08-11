package com.james.view;

import static com.james.logging.Logging.log;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

import com.james.DragAdapter;
import com.james.DragNotifiable;
import com.james.R;

public class AnimatedSmallCardsView extends View implements DragNotifiable {
    private final List<Drawable> cards;
    private final Context        context;
    private final DragAdapter    dragAdaper = new DragAdapter(this);
    private final int            screenHeight;
    private final int            screenWidth;

    public AnimatedSmallCardsView(Context context, int width, int height) {
        super(context);
        this.context = context;
        screenWidth = width;
        screenHeight = height;

        Resources res = context.getResources();

        cards = new ArrayList<Drawable>();
        cards.add(loadImage(res, R.drawable.king_hearts, 0));
        cards.add(loadImage(res, R.drawable.king_spades, 30));
        cards.add(loadImage(res, R.drawable.king_clubs, 60));
        cards.add(loadImage(res, R.drawable.king_diamonds, 90));
    }

    @Override
    public void dragLeft() {
        log("drag left");
    }

    @Override
    public void dragRight() {
        log("drag right");
    }

    @Override
    public void onDraw(Canvas canvas) {
        for (Drawable card : cards) {
            card.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return dragAdaper.onTouchEvent(event);
    }

    private Drawable loadImage(Resources res, int resource, int offset) {
        Drawable image = res.getDrawable(resource);

        float imageRatio = (float) image.getIntrinsicWidth() / image.getIntrinsicHeight();
        int imageWidth = (int) (240 * imageRatio);
        int imageHeight = (int) (400 * imageRatio);
        // log("" + screenWidth + "-" + imageWidth + "(" + (screenWidth - imageWidth) + ") , " + screenHeight + "-" + imageHeight + "," + imageWidth +
        // "," + imageHeight);
        int left = screenWidth - imageWidth - offset;
        int top = screenHeight - imageHeight;
        int right = screenWidth - offset;
        int bottom = screenHeight;

        image.setBounds(left, top, right, bottom);
        return image;
    }

}