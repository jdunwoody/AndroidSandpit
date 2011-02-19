package com.james.fingerpaint;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.james.fingerpaint.view.MyView;

public class FingerPaint extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new CustomExceptionHandler("/sdcard/finger_paint.log"));

		View view = new MyView(this);
		setContentView(view);
		view.requestFocus();

		// setContentView(R.layout.baseline_1);
	}
}