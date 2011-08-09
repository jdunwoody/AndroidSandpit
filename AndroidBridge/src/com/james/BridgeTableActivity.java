package com.james;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BridgeTableActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        List<ImageView> cards = loadCards();
        mainLayout.addView(cards.get(0));
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (!(view instanceof ImageView)) {
            return false;
        }

        event.getreturn false;
    }

    private ImageView createCard(int cardId) {
        ImageView newCard = new ImageView(this);
        newCard.setImageResource(cardId);
        return newCard;
    }

    private List<ImageView> loadCards() {
        List<ImageView> cards = new ArrayList<ImageView>();
        cards.add(createCard(R.drawable.king_clubs));
        cards.add(createCard(R.drawable.king_diamonds));
        cards.add(createCard(R.drawable.king_hearts));
        cards.add(createCard(R.drawable.king_spades));
        cards.add(createCard(R.drawable.queen_clubs));
        cards.add(createCard(R.drawable.queen_diamonds));
        cards.add(createCard(R.drawable.queen_hearts));
        cards.add(createCard(R.drawable.queen_spades));
        return cards;
    }
}