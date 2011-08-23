package com.camellia.header;

import static com.camellia.logging.Logging.log;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camellia.MainActivity;
import com.camellia.R;

public class HeaderRowDelegate {
    private final Context context;

    public HeaderRowDelegate(Context context) {
        this.context = context;
    }

    public void handleGoHome() {
        Intent goHome = new Intent(context, MainActivity.class);
        goHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(goHome);
    }

    public void handleProfileMenu() {

    }

    public void handleSearchPopup(LayoutInflater layoutInflater, ViewGroup parentViewGroup) {
        log("handleSearchPopup");

        alertDialog(layoutInflater, parentViewGroup);

        // initiatePopupWindow(layoutInflater);
        // PopupWindow window = new PopupWindow();
        // context.
        // window.showAtLocation(, gravity, 0, 0)

        // Dialog dialog = new Dialog(context);
        // dialog.setContentView(R.layout.search_popup);
        // dialog.setTitle("This is my custom dialog box");
        // dialog.setCancelable(true);
        // dialog.show();

        // Intent openSearchPopup = new Intent(context, SearchPopupActivity.class);
        // context.startActivity(openSearchPopup);
    }

    private void alertDialog(LayoutInflater layoutInflater, ViewGroup parentViewGroup) {
        View layout = layoutInflater.inflate(R.layout.search_popup, parentViewGroup);

        // TextView text = (TextView) layout.findViewById(R.id.text);
        // text.setText("Hello, this is a custom dialog!");
        // ImageView image = (ImageView) layout.findViewById(R.id.image);
        // image.setImageResource(R.drawable.android);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(layout);

        // builder.setOnCancelListener(new OnCancelListener() {
        // @Override
        // public void onCancel(DialogInterface dialog) {
        // log("onCancel dialog");
        // dialog.dismiss();
        // // finish();
        // }
        // });

        builder.create();
    }

    // private void initiatePopupWindow(LayoutInflater layoutInflater) {
    // try {
    // View layout = layoutInflater.inflate(R.layout.search_popup, R.id.senull);
    // PopupWindow popupWindow = new PopupWindow(layout, context.get300, 0, true);
    // popupWindow.showAtLocation(layout, Gravity.TOP & Gravity.LEFT, 0, 0);
    //
    // // Button cancelButton = (Button) layout.findViewById(R.id.end_data_send_button);
    // // makeBlack(cancelButton);
    // // cancelButton.setOnClickListener(cancel_button_click_listener);
    //
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
}