package com.james.graphics.imageview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.james.R;

public class DrawableOverBackgroundActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = new FrameLayout(this);

        frameLayout.addView(new DrawableOverBackgroundView(this));
        frameLayout.addView(new AvatarView(this));

        setContentView(frameLayout);
    }
}

class AvatarView extends View {
    private final Bitmap avatarImage;

    public AvatarView(Context context) {
        super(context);
        avatarImage = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(avatarImage, 0, 0, null);
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

