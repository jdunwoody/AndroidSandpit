package com.camellia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {
    public static final String ADDRESS_FIELD = "address";
    public static final String NAME_FIELD    = "name";
    private final MainActivity mainActivity;

    public MainActivity() {
        mainActivity = this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ImageButton searchButton = (ImageButton) findViewById(R.id.search);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mainActivity, SearchActivity.class);
                i.putExtra(NAME_FIELD, "Adam Smith");
                i.putExtra(ADDRESS_FIELD, "Melbourne");
                startActivity(i);
            }
        });
    }
}