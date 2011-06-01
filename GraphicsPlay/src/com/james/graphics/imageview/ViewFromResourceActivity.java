package com.james.graphics.imageview;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.james.R;

public class ViewFromResourceActivity extends Activity {
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView imageView = createMainImage();

        layout = new LinearLayout(this);
        layout.addView(imageView);
        setContentView(layout);
    }

    private ImageView createMainImage() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.hayley_480px);
        imageView.setAdjustViewBounds(true);
        imageView.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        return imageView;
    }
}
