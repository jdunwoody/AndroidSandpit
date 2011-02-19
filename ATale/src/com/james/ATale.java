package com.james;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ATale extends Activity {
	private LinearLayout linearLayout;
	private boolean imageA = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// setupButtonListener();
		// setupMainImage();

		setContentView(R.layout.main);
	}

	// private OnClickListener buttonClickListener = new OnClickListener() {
	// public void onClick(View v) {
	// changeImage();
	// }
	// };

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

	// private void setupButtonListener() {
	// Button button = (Button) findViewById(R.id.mainImage);
	// button.setOnClickListener(buttonClickListener);
	// }

	private void setupMainImage() {
		linearLayout = new LinearLayout(this);

		ImageView mainImage = (ImageView) findViewById(R.id.mainImage);

		mainImage.setImageResource(R.drawable.icon);
		mainImage.setAdjustViewBounds(true);
		mainImage.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		// linearLayout.addView(mainImage);
	}
}