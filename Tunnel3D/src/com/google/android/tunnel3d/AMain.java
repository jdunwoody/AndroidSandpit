package com.google.android.tunnel3d;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;

public class AMain extends Activity implements Runnable {
    private GLView glview;
    private Thread th;
    private boolean done;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);

        glview = new GLView(this);
        setContentView(glview);

        done = false;
        th = new Thread(this);
        th.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        done = true;
        try {
            th.join();
        } catch (Exception _ex) {
        }
    }

    public void run() {
        while (!done) {
            glview.render();
        }
    }
}