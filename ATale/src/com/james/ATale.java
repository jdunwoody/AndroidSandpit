package com.james;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public MySurfaceView(Context context) {
        super(context);
    }

    // http://developer.android.com/reference/android/view/SurfaceView.html
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        Surface surface = holder.getSurface();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}

public class ATale extends Activity {
    // private Controller controller;
    // private GameState gameState;
    private boolean      imageA = true;
    private LinearLayout linearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        // try {
        // this.gameState = new GameState("map.data");
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        // this.controller = new Controller(this, gameState);
        setupButtonListeners();

        TableLayout table = (TableLayout) findViewById(R.id.tableLayout1);

        table.addView(new MySurfaceView(table.getContext()));
    }

    private void setupButtonListeners() {
        ImageButton forward = (ImageButton) findViewById(R.id.forward);
        ImageButton back = (ImageButton) findViewById(R.id.back);
        ImageButton left = (ImageButton) findViewById(R.id.left);
        ImageButton right = (ImageButton) findViewById(R.id.right);

        forward.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // controller.forward();
            }
        });

        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // controller.back();
            }

        });
        left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // controller.left();
            }
        });
        right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // controller.right();
            }
        });
    }

    private void setupMainImage() {
        linearLayout = new LinearLayout(this);

        ImageView mainImage = (ImageView) findViewById(R.id.mainImage);

        mainImage.setImageResource(R.drawable.icon);
        mainImage.setAdjustViewBounds(true);
        mainImage.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        // linearLayout.addView(mainImage);
    }

    // private View changeImage() {
    // ImageView mainImage = (ImageView) findViewById(R.id.mainImage);
    //
    // mainImage.setImageResource(currentImage());
    //
    // return mainImage;
    // }
    //
    // private int currentImage() {
    // if (imageA != imageA) {
    // return R.drawable.icon;
    // } else {
    // return R.drawable.imageb;
    // }
    // }
}