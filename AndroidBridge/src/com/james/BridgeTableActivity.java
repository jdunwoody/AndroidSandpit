package com.james;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class BridgeTableActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        MainView mainView = new MainView(this);
        mainLayout.addView(mainView);
    }
}