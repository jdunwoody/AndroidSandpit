package com.james.view;

import static com.james.logging.Logging.log;

import java.util.EnumSet;
import java.util.Map;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.james.Direction;
import com.james.DragAdapter;
import com.james.DragNotifiable;
import com.james.domain.Card;
import com.james.domain.Hand;
import com.james.domain.Pack;

public class AnimatedSmallCardsView extends View implements DragNotifiable {
    private final Context     context;
    private final DragAdapter dragAdaper;
    private final Pack        pack;
    private final PackFactory packFactory;
    private final int         screenHeight;
    private final int         screenWidth;

    public AnimatedSmallCardsView(Context context, int width, int height) {
        super(context);
        this.context = context;
        screenWidth = width;
        screenHeight = height;
        dragAdaper = new DragAdapter(this);

        Resources res = context.getResources();
        packFactory = new PackFactory();
        pack = packFactory.newInstance(res);

        pack.shuffle();
        Map<Player, Hand> hands = pack.deal();

    }

    @Override
    public void drag(Direction direction) {
        if (direction == Direction.NONE) {
            return;
        }

        CardView card = pack.getHand(Player.NORTH).getCard(0).getView();
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
        for (Player player : EnumSet.allOf(Player.class)) {
            Hand hand = pack.getHand(player);
            for (Card card : hand.cards()) {
                card.getView().draw(canvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return dragAdaper.onTouchEvent(event);
    }

}