package com.james.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AbsoluteLayout;

import com.james.activity.customplacement.MainPanel;

@SuppressWarnings("deprecation")
public class CustomPlacementActivity extends Activity {

    private MainPanel mainPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AbsoluteLayout layout = new AbsoluteLayout(this);
        super.setContentView(layout);

        mainPanel = new MainPanel(this);
        layout.addView(mainPanel);
    }
}
