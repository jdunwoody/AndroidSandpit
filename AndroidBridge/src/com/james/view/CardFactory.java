package com.james.view;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.james.domain.Card;
import com.james.domain.Rank;
import com.james.domain.Suit;

public class CardFactory {

    public Card newInstance(Resources res, int resourceId, Rank rank, Suit suit) {
        Drawable image = res.getDrawable(resourceId);

        float imageRatio = (float) image.getIntrinsicWidth() / image.getIntrinsicHeight();
        int imageWidth = (int) (240 * imageRatio);
        int imageHeight = (int) (400 * imageRatio);

        return new Card(new CardView(imageWidth, imageHeight, image), rank, suit);
    }
}