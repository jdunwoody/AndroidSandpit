package com.james.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Flock extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        defineButtons();
    }

    private void defineButtons() {
        Button normalButton = (Button) findViewById(R.id.button1);

        normalButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent();

                myIntent.setClassName("com.james", "com.james.FlockPageActivity");
                // myIntent.putExtra("com.james.SpecialValue", "Hello, Joe!");
                startActivity(myIntent);

                // myIntent.setClassName("com.james", GLSurfaceViewActivity.class.getName());
                // // myIntent.putExtra("com.james.SpecialValue", "Hello, Joe!");
                // startActivity(myIntent);
            }
        });

    }
}
