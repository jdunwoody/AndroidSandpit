package com.james.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.james.game.GameState;

public class GameMapView extends View {
    private final GameState gameState;
    private final Paint     paint;

    public GameMapView(Context context, GameState gameState) {
        super(context);
        this.gameState = gameState;
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);

        gameState.render(canvas, paint);
    }
}