package com.james;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MyHelloWorld extends Activity {
    protected static final String PATH_TO_FILE         = "/sdcard/MP3/Andy Caldwell/Universal Truth/04 Warrior";
    private static final int      DIALOG_FILE_EXPLORER = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.main);

            define_simple_buttons();

            define_spinner();

            // define_file_explorer_dialog();

            // define_mediaplayer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    // private void define_file_explorer_dialog() {
    // Button fileExplorerButton = (Button)
    // findViewById(R.id.button_file_explorer);
    //
    // fileExplorerButton.setOnClickListener(new OnClickListener() {
    // public void onClick(View v) {
    // showDialog(DIALOG_FILE_EXPLORER);
    // }
    // });
    // }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog;
        try {
            switch (id) {
                case DIALOG_FILE_EXPLORER:
                    // dialog = createAlertDialog();

                    dialog = createMyDialog();
                    break;
                default:
                    dialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            dialog = null;
        }
        return dialog;
    }

    private Dialog createMyDialog() {
        Dialog dialog = new Dialog(getApplicationContext());

        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Custom Dialog");

        // TextView text = (TextView) dialog.findViewById(R.id.text);
        // text.setText("Hello, this is a custom dialog!");
        // ImageView image = (ImageView)
        // dialog.findViewById(R.id.image);
        // image.setImageResource(R.drawable.icon);
        return dialog;
    }

    private AlertDialog createAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MyHelloWorld.this.finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        return builder.create();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TextView footer = (TextView) findViewById(R.id.text_footer);

        switch (item.getItemId()) {
            case R.id.new_game:
                footer.setText(R.string.new_game_pressed);
                break;
            case R.id.quit:
                footer.setText(R.string.quit_pressed);
                break;
        }
        return false;
    }

    private void define_spinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(
                R.array.planets));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showToast("Spinner: position=" + position + " id=" + id);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                showToast("Spinner: unselected");
            }
        });
    }

    private void showToast(String text) {
        Context context = getApplicationContext();

        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    // private void define_mediaplayer() {
    // Button startMediaPlayerButton = (Button)
    // findViewById(R.id.button_start_mediaplayer);
    //
    // startMediaPlayerButton.setOnClickListener(new OnClickListener() {
    // @Override
    // public void onClick(View v) {
    //
    // MediaPlayer mp = new MediaPlayer();
    // try {
    // mp.setDataSource(PATH_TO_FILE);
    //
    // mp.prepare();
    //
    // mp.start();
    // } catch (Exception e) {
    // showToast(e.getMessage());
    // }
    // }
    // });
    // }

    private void define_simple_buttons() {
        Button normalButton = (Button) findViewById(R.id.button_normal);
        Button smallButton = (Button) findViewById(R.id.button_small);
        Button toggleButton = (Button) findViewById(R.id.button_toggle);

        final TextView footer = (TextView) findViewById(R.id.text_footer);

        normalButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                footer.setText(R.string.normal_pressed);
            }
        });

        smallButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                footer.setText(R.string.small_pressed);
            }
        });

        toggleButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                footer.setText(R.string.toggle_pressed);
            }
        });
    }
}
