package com.james;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

public class SentenceScramble extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goFullscreen();

        setContentView(R.layout.main);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        Button button = new Button(this);
        button.setText("Hello");
        frameLayout.addView(button);

    }

    private void goFullscreen() {
        // otherwise add android:theme="@android:style/Theme.NoTitleBar.Fullscreen" to AndroidManifest.xml
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}