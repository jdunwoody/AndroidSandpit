package com.james.view;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class CardFactory {

    public Card newInstance(Resources res, int resourceId) {
        Drawable image = res.getDrawable(resourceId);

        float imageRatio = (float) image.getIntrinsicWidth() / image.getIntrinsicHeight();
        int imageWidth = (int) (240 * imageRatio);
        int imageHeight = (int) (400 * imageRatio);

        return new Card(imageWidth, imageHeight, image);
    }
}