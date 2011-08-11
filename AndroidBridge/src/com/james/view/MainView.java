package com.james.view;

import static com.james.logging.Logging.log;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.james.DragAdapter;
import com.james.DragNotifiable;
import com.james.R;

public class MainView extends ImageView implements DragNotifiable {

    private final List<Integer> cards;
    private final Context       context;
    private int                 currentCardIndex;
    private final DragAdapter   dragAdapter = new DragAdapter(this);

    public MainView(Context context) {
        super(context);
        this.context = context;
        cards = loadCards();
        currentCardIndex = 0;
        drawCard();
    }

    @Override
    public void dragLeft() {
        log("dragLeft");
        if (currentCardIndex > 0) {
            currentCardIndex--;
            drawCard();
        }
    }

    @Override
    public void dragRight() {
        log("dragRight");
        if (currentCardIndex < cards.size() - 1) {
            currentCardIndex++;
            drawCard();
        }
    }

    // private ImageView createCard(int cardId) {
    // ImageView newCard = new ImageView(context);
    // newCard.setImageResource(cardId);
    // return newCard;
    // }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return dragAdapter.onTouchEvent(event);
    }

    private void drawCard() {
        setImageResource(cards.get(currentCardIndex));
    }

    private List<Integer> loadCards() {
        List<Integer> cards = new ArrayList<Integer>();
        cards.add(R.drawable.king_clubs);
        cards.add(R.drawable.king_diamonds);
        cards.add(R.drawable.king_hearts);
        cards.add(R.drawable.king_spades);
        cards.add(R.drawable.queen_clubs);
        cards.add(R.drawable.queen_diamonds);
        cards.add(R.drawable.queen_hearts);
        cards.add(R.drawable.queen_spades);
        return cards;

        // List<ImageView> cards = new ArrayList<ImageView>();
        // cards.add(createCard(R.drawable.king_clubs));
        // cards.add(createCard(R.drawable.king_diamonds));
        // cards.add(createCard(R.drawable.king_hearts));
        // cards.add(createCard(R.drawable.king_spades));
        // cards.add(createCard(R.drawable.queen_clubs));
        // cards.add(createCard(R.drawable.queen_diamonds));
        // cards.add(createCard(R.drawable.queen_hearts));
        // cards.add(createCard(R.drawable.queen_spades));
        // return cards;
    }
}
