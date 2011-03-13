package com.james.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.james.view.SkyView;

public class FlockPageActivity extends Activity implements OnTouchListener {
    private SkyView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new SkyView(this);
        setContentView(view);

        Log.i(FlockPageActivity.class.getName(), "info creating activity");
        Log.w(FlockPageActivity.class.getName(), "warn creating activity");
        Log.e(FlockPageActivity.class.getName(), "fatal creating activity");

        view.setOnTouchListener(this);
    }

    public boolean onTouch(View baseView, MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            SkyView view = (SkyView) baseView;
            view.touched(event.getX(), event.getY());
            return true;
        }
        return false;
    }
}
