package com.james;

import static com.james.logging.Logging.log;
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

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        log("width: " + width + " height: " + height);

        Display display = getWindowManager().getDefaultDisplay();
        log("width: " + display.getWidth() + " height: " + display.getHeight());

        View animagedSmallCardsView = new AnimatedSmallCardsView(this, width, height);
        mainLayout.addView(animagedSmallCardsView);
    }
}