package com.james.io;

import com.james.activity.ATale;
import com.james.game.GameState;

import android.view.View;
import android.widget.ImageButton;

public class Controller {
    private final ATale     aTale;
    private final GameState state;

    public Controller(ATale aTale, GameState state) {
        this.aTale = aTale;
        this.state = state;
    }

    public void back() {
        state.moveSouth();
    }

    public void forward() {
        state.moveNorth();
    }

    public void left() {
        state.moveWest();
    }

    public void pressed(View view) {
        ImageButton button = (ImageButton) view;

        // TextView status = (TextView) aTale.findViewById(R.id.messageView);
        // status.setText("excellent " + button.getId());
    }

    public void right() {
        state.moveEast();
    }
}