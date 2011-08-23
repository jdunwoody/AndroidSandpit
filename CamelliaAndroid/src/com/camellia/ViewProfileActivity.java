package com.camellia;

import static com.camellia.logging.Logging.log;

import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camellia.header.HeaderRowDelegate;
import com.camellia.header.HeaderRowSupported;
import com.camellia.http.WebInteraction;
import com.camellia.profile.ViewProfileService;

public class ViewProfileActivity extends Activity implements HeaderRowSupported {

    private HeaderRowDelegate  headerRowDelegate;
    private LayoutInflater     layoutInflater;
    private ViewProfileService viewProfileService;

    public ViewProfileActivity() {
        log("ViewProfileActivity...");
    }

    public void goBack(View view) {
        log("gotoBack");
        finish();
    }

    @Override
    public void goHome(View view) {
        headerRowDelegate.handleGoHome();
    }

    public void gotoAddProfileProfile(View view) {
        log("gotoAddProfileProfile");
    }

    public void gotoMoreProfile(View view) {
        log("gotoMoreProfile");
    }

    public void gotoNextProfile(View view) {
        log("gotoNextProfile");
    }

    public void gotoPreviousProfile(View view) {
        log("gotoPreviousProfile");
    }

    @Override
    public void onProfileMenu(View view) {
        headerRowDelegate.handleProfileMenu();
    }

    @Override
    public void onSearchPopup(View view) {
        headerRowDelegate.handleSearchPopup(layoutInflater, (ViewGroup) findViewById(R.id.header_row));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile);

        viewProfileService = new ViewProfileService(new WebInteraction(new DefaultHttpClient()));
        layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        headerRowDelegate = new HeaderRowDelegate(this);

        // String profileId = getIntent().getStringExtra(ViewProfileAction.PROFILE_ID);
        // Profile profile = viewProfileService.load(profileId, profileId);
        //
        // LinearLayout homeSection = (LinearLayout) findViewById(R.id.view_profile_home_section);
        // for (ContactPoint contactPoint : profile.getHomeSection().getContactPoints()) {
        // LinearLayout contactPointView = (LinearLayout) layoutInflater.inflate(R.id.contact_point_icon_and_value, null);
        // // ImageView icon = (ImageView) contactPointView.getChildAt(0);
        // // TextView text = (TextView) contactPointView.getChildAt(1);
        // //
        // // text.setText(contactPoint.getText());
        // //
        // // homeSection.addView(contactPointView);
        // }
    }
}