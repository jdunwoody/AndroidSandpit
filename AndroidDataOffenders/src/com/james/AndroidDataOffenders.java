package com.james;

import android.app.Activity;
import android.net.TrafficStats;
import android.os.Bundle;
import android.widget.TextView;

public class AndroidDataOffenders extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			TextView textView = (TextView) findViewById(R.id.output);
			textView.setText(String.valueOf(TrafficStats.getMobileRxBytes()));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		setContentView(R.layout.main);
	}
}

// stats.getTotalRxBytes();
//
// int myUid = Process.myUid();
// ActivityManager activityManager = (ActivityManager)
// getSystemService(ACTIVITY_SERVICE);
//
// for (RunningAppProcessInfo processInfo :
// activityManager.getRunningAppProcesses()) {
// System.out.println(processInfo.processName + " hase used " +
// stats.getUidRxBytes(processInfo.uid) + " bytes");
// }
