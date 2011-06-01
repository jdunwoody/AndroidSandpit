package com.james.game;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.james.thing.Thing;
import com.james.thing.ThingRenderer;

public class GameMap implements Renderable {
    final Thing[][]             data;
    final int                   height;
    private final ThingRenderer renderer;
    final int                   width;

    public GameMap(ThingRenderer renderer, Thing[][] data) {
        this.renderer = renderer;
        this.data = data;
        height = data.length;
        width = height == 0 ? 0 : data[0].length;
    }

    public Thing at(int x, int y) {
        return data[y][x];
    }

    @Override
    public void render(Canvas canvas, Paint paint) {
        for (int x = 0; x < width; x++) {
            canvas.drawLine(x, 0, x, height, paint);
        }
        for (int y = 0; y < height; y++) {
            canvas.drawLine(0, y, width, y, paint);
            for (int x = 0; x < width; x++) {
                at(x, y).render(canvas, paint);
            }
        }
    }

    public void set(int x, int y, Thing thing) {
        data[y][x] = thing;
    }
}
