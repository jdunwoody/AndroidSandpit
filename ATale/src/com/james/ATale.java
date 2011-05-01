package com.james;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ATale extends Activity {
    private Controller   controller;
    private GameState    gameState;
    private boolean      imageA = true;
    private LinearLayout linearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        try {
            this.gameState = new GameState("map.data");
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.controller = new Controller(this, gameState);
        setupButtonListeners();
    }

    private void setupButtonListeners() {
        ImageButton forward = (ImageButton) findViewById(R.id.forward);
        ImageButton back = (ImageButton) findViewById(R.id.back);
        ImageButton left = (ImageButton) findViewById(R.id.left);
        ImageButton right = (ImageButton) findViewById(R.id.right);

        forward.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.forward();
            }
        });

        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.back();
            }

        });
        left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.left();
            }
        });
        right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.right();
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