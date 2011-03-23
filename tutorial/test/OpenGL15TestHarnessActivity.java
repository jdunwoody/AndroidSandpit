package com.ai.android.OpenGL.SDK15;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

//filename: OpenGLTestHarnessActivity.java
public class OpenGL15TestHarnessActivity extends Activity {
    private GLSurfaceView mTestHarness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTestHarness = new GLSurfaceView(this);
        mTestHarness.setEGLConfigChooser(false);
        // mTestHarness.setRenderer(new SimpleRectRenderer(this));
        // mTestHarness.setRenderer(new SimpleTriangleRenderer(this));
        mTestHarness.setRenderer(new PolygonRenderer(this));
        mTestHarness.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setContentView(mTestHarness);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTestHarness.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTestHarness.onPause();
    }
}
