package com.james.view;

import static com.james.logging.Logging.log;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.james.Direction;
import com.james.DragAdapter;
import com.james.DragNotifiable;
import com.james.R;

public class AnimatedSmallCardsView extends View implements DragNotifiable {
    private final CardFactory cardFactory;
    private final List<Card>  cards;
    private final Context     context;
    private final DragAdapter dragAdaper;
    private final int         screenHeight;
    private final int         screenWidth;

    public AnimatedSmallCardsView(Context context, int width, int height) {
        super(context);
        this.context = context;
        screenWidth = width;
        screenHeight = height;
        dragAdaper = new DragAdapter(this);

        Resources res = context.getResources();

        cardFactory = new CardFactory();

        cards = new ArrayList<Card>();
        cards.add(createCard(res, R.drawable.king_hearts, 0));
        cards.add(createCard(res, R.drawable.king_spades, 30));
        cards.add(createCard(res, R.drawable.king_clubs, 60));
        cards.add(createCard(res, R.drawable.king_diamonds, 90));
    }

    @Override
    public void drag(Direction direction) {
        if (direction == Direction.NONE) {
            return;
        }

        Card card = cards.get(0);
        int x = card.x;
        int y = card.y;
        switch (direction) {
            case DRAG_LEFT:
                x = card.x - 10;
                y = card.y;
                break;
            case DRAG_RIGHT:
                x = card.x + 10;
                y = card.y;
                break;
            case DRAG_UP:
                x = card.x;
                y = card.y - 10;
                break;
            case DRAG_DOWN:
                x = card.x;
                y = card.y + 10;
                break;
        }

        card.move(x, y);
        invalidate();

    }

    @Override
    public void onDraw(Canvas canvas) {
        log("drawing");
        for (Card card : cards) {
            card.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return dragAdaper.onTouchEvent(event);
    }

    private Card createCard(Resources res, int resource, int offset) {
        Card card = cardFactory.newInstance(res, resource);
        card.move(offset, 0);
        return card;
    }
}