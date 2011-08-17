package com.james.view;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.james.domain.Card;
import com.james.domain.Rank;
import com.james.domain.Suit;

public class CardFactory {
    private final Screen screen;

    public CardFactory(Screen screen) {
        this.screen = screen;
    }

    public Card newInstance(Resources res, int resourceId, Rank rank, Suit suit) {
        Drawable image = res.getDrawable(resourceId);

        float imageRatio = (float) image.getIntrinsicWidth() / image.getIntrinsicHeight();
        int imageWidth = (int) (120 * imageRatio); // 240
        int imageHeight = (int) (200 * imageRatio); // 400

        return new Card(new CardView(screen, imageWidth, imageHeight, image), rank, suit);
    }
}