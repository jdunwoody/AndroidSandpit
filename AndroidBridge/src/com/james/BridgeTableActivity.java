package com.james;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;

import com.james.view.AnimatedSmallCardsView;

public class BridgeTableActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        // MainView mainView = new MainView(this);
        // mainLayout.addView(mainView);

        Display display = getWindowManager().getDefaultDisplay();
        View animagedSmallCardsView = new AnimatedSmallCardsView(this, display.getWidth(), display.getHeight());
        mainLayout.addView(animagedSmallCardsView);
    }
}