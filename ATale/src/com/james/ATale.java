package com.james;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ATale extends Activity {
	private LinearLayout linearLayout;
	private boolean imageA = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		setupButtonListener();
		// setupMainImage();
	}

	private OnClickListener buttonClickListener = new OnClickListener() {
		public void onClick(View v) {
			TextView textView = (TextView) findViewById(R.id.messageView);
			textView.setText("excellent");
		}
	};

	// private View changeImage() {
	// ImageView mainImage = (ImageView) findViewById(R.id.mainImage);
	//
	// mainImage.setImageResource(currentImage());
	//
	// return mainImage;
	// }
	//
	// private int currentImage() {
	// if (imageA != imageA) {
	// return R.drawable.icon;
	// } else {
	// return R.drawable.imageb;
	// }
	// }

	private void setupButtonListener() {
		Button button = (Button) findViewById(R.id.buttonForward);
		button.setOnClickListener(buttonClickListener);
	}

	private void setupMainImage() {
		linearLayout = new LinearLayout(this);

		ImageView mainImage = (ImageView) findViewById(R.id.mainImage);

		mainImage.setImageResource(R.drawable.icon);
		mainImage.setAdjustViewBounds(true);
		mainImage.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		// linearLayout.addView(mainImage);
	}
}