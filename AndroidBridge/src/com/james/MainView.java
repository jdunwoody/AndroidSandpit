package com.james;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainView extends ImageView {
    private static final int    DRAG_LEFT   = 1;
    private static final int    DRAG_RIGHT  = 2;
    private static final float  MIN_CHANGE  = 5;
    private static final int    NO_DRAGGING = 3;
    private final List<Integer> cards;
    private final Context       context;
    private int                 currentCardIndex;
    private boolean             isDragging  = false;
    private float               startX;
    private float               startY;

    public MainView(Context context) {
        super(context);
        this.context = context;
        cards = loadCards();
        currentCardIndex = 0;
        drawCard();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                log("actionDown");
                isDragging = true;
                startX = event.getX();
                startY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                if (isDragging) {
                    switch (isDraggedEnough(event)) {
                        case DRAG_LEFT:
                            dragLeft();
                            isDragging = false;
                            break;
                        case DRAG_RIGHT:
                            dragRight();
                            isDragging = false;
                            break;
                        default:
                            break;
                    }
                }
                return true;
            case MotionEvent.ACTION_UP:
                log("actionUp");
                if (isDragging) {
                    switch (isDraggedEnough(event)) {
                        case DRAG_LEFT:
                            dragLeft();
                            break;
                        case DRAG_RIGHT:
                            dragRight();
                            break;
                        default:
                            break;
                    }
                    isDragging = false;
                }
                return true;
        }
        return false;
    }

    private void dragLeft() {
        log("dragLeft");
        if (currentCardIndex > 0) {
            currentCardIndex--;
            drawCard();
        }
    }

    // private ImageView createCard(int cardId) {
    // ImageView newCard = new ImageView(context);
    // newCard.setImageResource(cardId);
    // return newCard;
    // }

    private void dragRight() {
        log("dragRight");
        if (currentCardIndex < cards.size() - 1) {
            currentCardIndex++;
            drawCard();
        }
    }

    private void drawCard() {
        setImageResource(cards.get(currentCardIndex));
    }

    private int isDraggedEnough(MotionEvent event) {
        float xDifference = event.getX() - startX;
        float absXDifference = Math.abs(xDifference);
        float yDifference = event.getY() - startY;
        float absYDifference = Math.abs(yDifference);

        if (absXDifference < MIN_CHANGE && absYDifference < MIN_CHANGE) {
            return NO_DRAGGING;
        }

        if (absXDifference >= absYDifference) {
            if (xDifference >= 0) {
                return DRAG_RIGHT;
            } else {
                return DRAG_LEFT;
            }
        }
        return NO_DRAGGING;
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

    private void log(String message) {
        Log.i("MainView", message);
    }
}