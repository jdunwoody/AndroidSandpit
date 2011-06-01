package com.james;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.TextView;

public class AndroidGraphics extends Activity {
    private DrawingThread   mLunarThread;
    private GameSurfaceView mLunarView;
    private SurfaceView     surfaceView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // tell system to use the layout defined in our XML file
        setContentView(R.layout.main);

        // get handles to the LunarView from XML, and its LunarThread
        mLunarView = (GameSurfaceView) findViewById(R.id.lunar);
        mLunarThread = mLunarView.getThread();

        // give the LunarView a handle to the TextView used for messages
        mLunarView.setTextView((TextView) findViewById(R.id.text));

        if (savedInstanceState == null) {
            // we were just launched: set up a new game
            mLunarThread.setState(DrawingThread.STATE_READY);
            Log.w(this.getClass().getName(), "SIS is null");
        } else {
            // we are being restored: resume a previous game
            mLunarThread.restoreState(savedInstanceState);
            Log.w(this.getClass().getName(), "SIS is nonnull");
        }
    }
}

// super.onCreate(savedInstanceState);
//
// draw2d();
// }

// private void draw2d() {
// surfaceView = new GameSurfaceView(this);
// }
// }

// draw3d();
// private void draw3d() {
// surfaceView = new GLSurfaceView(this);
// surfaceView.setRenderer(new GameRenderer3D());
// setContentView(surfaceView);
// }
//
// @Override
// protected void onPause() {
// super.onPause();
// surfaceView.onPause();
// }
//
// @Override
// protected void onResume() {
// super.onResume();
// surfaceView.onResume();
// }
