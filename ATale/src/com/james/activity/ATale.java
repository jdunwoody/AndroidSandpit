package com.james.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.james.R;
import com.james.game.GameState;
import com.james.io.Controller;
import com.james.view.GameMapView;

public class ATale extends Activity {
    private Controller controller;
    private GameState  gameState;

    private void goFullscreen() {
        // otherwise add android:theme="@android:style/Theme.NoTitleBar.Fullscreen" to AndroidManifest.xml
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            gameState = new GameState(getResources().openRawResource(R.raw.map));
        } catch (Exception e) {
            showMessage(e.getMessage());
            throw new RuntimeException(e);
        }
        controller = new Controller(this, gameState);

        setContentView(new GameMapView(this));

        // goFullscreen();

        // setContentView(R.layout.main);

        // TableRow mapRow = (TableRow) findViewById(R.id.mapRow);
        // mapRow.addView(new GameMapView(this));
        // mapRow.setMinimumHeight(200);
        // mapRow.setMinimumWidth(200);

        // TableLayout table = (TableLayout) findViewById(R.id.tableLayout1);

        // table.addView(new GameMapView(table.getContext()));
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

// private void setupButtonListeners() {
// ImageButton forward = (ImageButton) findViewById(R.id.forward);
// ImageButton back = (ImageButton) findViewById(R.id.back);
// ImageButton left = (ImageButton) findViewById(R.id.left);
// ImageButton right = (ImageButton) findViewById(R.id.right);
//
// forward.setOnClickListener(new OnClickListener() {
// @Override
// public void onClick(View v) {
// // controller.forward();
// }
// });
//
// back.setOnClickListener(new OnClickListener() {
// @Override
// public void onClick(View v) {
// // controller.back();
// }
//
// });
// left.setOnClickListener(new OnClickListener() {
// @Override
// public void onClick(View v) {
// // controller.left();
// }
// });
// right.setOnClickListener(new OnClickListener() {
// @Override
// public void onClick(View v) {
// // controller.right();
// }
// });
// }

// private void setupMainImage() {
// linearLayout = new LinearLayout(this);
//
// ImageView mainImage = (ImageView) findViewById(R.id.mainImage);
//
// mainImage.setImageResource(R.drawable.icon);
// mainImage.setAdjustViewBounds(true);
// mainImage.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//
// // linearLayout.addView(mainImage);
// }

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
