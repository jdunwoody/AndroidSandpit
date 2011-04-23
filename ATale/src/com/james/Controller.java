package com.james;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Controller {
    private final ATale     aTale;
    private final GameState state;

    public Controller(ATale aTale, GameState state) {
        this.aTale = aTale;
        this.state = state;
    }

    public void pressed(View view) {
        ImageButton button = (ImageButton) view;

        TextView status = (TextView) aTale.findViewById(R.id.messageView);
        status.setText("excellent " + button.getId());
    }

    public void forward() {
        state.moveForward();
    }

    public void back() {
        state.moveBack();
    }

    public void left() {
        state.moveLeft();
    }

    public void right() {
        state.moveRight();
    }
}