package com.james;

import static com.james.logging.Logging.log;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

public class AnimatedSmallCardsView extends View {
    private final List<Drawable> cards;
    private final Context        context;
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
    public void onDraw(Canvas canvas) {
        log("AnimatedSmallCardsView draw");
        for (Drawable card : cards) {
            card.draw(canvas);
        }
    }

    private Drawable loadImage(Resources res, int resource, int offset) {
        Drawable image = res.getDrawable(resource);

        float imageRatio = (float) image.getIntrinsicWidth() / image.getIntrinsicHeight();
        int imageWidth = (int) (240 * imageRatio);
        int imageHeight = (int) (400 * imageRatio);
        log("" + screenWidth + "-" + imageWidth + "(" + (screenWidth - imageWidth) + ") , " + screenHeight + "-" + imageHeight + "," + imageWidth
                + "," + imageHeight);
        int left = screenWidth - imageWidth - offset;
        int top = screenHeight - imageHeight;
        int right = screenWidth - offset;
        int bottom = screenHeight;

        image.setBounds(left, top, right, bottom);
        return image;
    }
}