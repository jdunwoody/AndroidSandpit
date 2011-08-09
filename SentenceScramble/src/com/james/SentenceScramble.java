package com.james;

import java.util.LinkedList;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class SentenceScramble extends Activity implements OnInitListener, OnClickListener, OnTouchListener {
    private static final String       TAG = "SentenceScramble";
    private LinkedList<TalkingButton> buttons;
    private TalkingButton             draggingButton;
    private int                       height;
    private TextToSpeech              textToSpeech;

    @Override
    public void onClick(View view) {
        if (view instanceof TalkingButton) {
            TalkingButton button = (TalkingButton) view;
            textToSpeech.speak(String.valueOf(button.getText()), TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goFullscreen();

        setContentView(R.layout.main);
        textToSpeech = new TextToSpeech(this, this);

        // LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);

        // buttons = new LinkedList<TalkingButton>();
        // TalkingButton button1 = create_button(layout, "Go", Color.RED, -1);
        // height = button1.getHeight();
        // buttons.add(button1);
        // buttons.add(create_button(layout, "to", Color.YELLOW, height));
        // buttons.add(create_button(layout, "bed", Color.GRAY, height));
        // buttons.add(create_button(layout, "Miss", Color.GREEN, height));
        // buttons.add(create_button(layout, "Hayley", Color.CYAN, height));
        // buttons.add(create_button(layout, "Dunwoody", Color.WHITE, height));
    }

    @Override
    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }

        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e(TAG, "Language is not available.");
            } else {

                for (Button button : buttons) {
                    button.setEnabled(true);
                }
            }
        } else {
            // Initialization failed.
            Log.e(TAG, "Could not initialize TextToSpeech.");
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent me) {
        if (!(view instanceof TalkingButton)) {
            return true;
        }

        if (me.getAction() == MotionEvent.ACTION_DOWN) {
            draggingButton = (TalkingButton) view;
            // image = new ImageView(this);
            // image.setImageBitmap(btn.getDrawingCache());
            // layout.addView(image, params);
        }
        if (me.getAction() == MotionEvent.ACTION_UP) {
            draggingButton = null;
        } else if (me.getAction() == MotionEvent.ACTION_MOVE) {
            if (draggingButton != null) {
                // LayoutParams params = new LinearLayout.LayoutParams(0, height);
                // draggingButton.setLayoutParams(params);
                // draggingButton.setPadding((int) me.getRawX(), (int) me.getRawY(), 0, 0);
                draggingButton.invalidate();
            }
        }
        return false;
    }

    private TalkingButton create_button(LinearLayout layout, String label, int color, int height) {
        TalkingButton button = new TalkingButton(this);
        button.setText(label);
        button.setTextSize(40);
        button.setWidth(250);
        // if (height != -1) {
        // button.setHeight(height);
        // }
        button.setEnabled(false);
        button.setBackgroundColor(color);

        button.setOnClickListener(this);
        button.setOnTouchListener(this);

        layout.addView(button);
        // final LayoutParams layoutParams = new LayoutParams(button.getWidth(), button.getHeight());
        // button.setLayoutParams(layoutParams);
        return button;
    }

    private void goFullscreen() {
        // otherwise add android:theme="@android:style/Theme.NoTitleBar.Fullscreen" to AndroidManifest.xml
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}