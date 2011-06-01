package com.james.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.james.thing.Avatar;
import com.james.thing.Empty;
import com.james.thing.Thing;
import com.james.thing.ThingRenderer;
import com.james.thing.Wall;

public class GameState implements Renderable {
    static final int             HEIGHT     = 10;
    static final int             WIDTH      = 10;
    Avatar                       avatar;
    public final Thing           emptyThing;
    GameMap                      map;
    private final GameMapBuilder mapBuilder = new GameMapBuilder();
    private final ThingRenderer  renderer;
    public final Thing           wallThing;

    public GameState(ThingRenderer renderer, BufferedReader reader) throws Exception {
        this.renderer = renderer;
        emptyThing = new Empty(renderer);
        wallThing = new Wall(renderer);
        avatar = new Avatar(renderer, 0, 0);

        char[] buffer = new char[1024];
        int size = reader.read(buffer);
        map = mapBuilder.build(avatar, new String(buffer, 0, size));
    }

    public GameState(ThingRenderer renderer, File file) throws Exception {
        this(renderer, new BufferedReader(new FileReader(file)));
    }

    public GameState(ThingRenderer renderer, InputStream is) throws Exception {
        this(renderer, new BufferedReader(new InputStreamReader(is)));
    }

    public GameState(ThingRenderer renderer, String buffer) throws Exception {
        this(renderer, new BufferedReader(new StringReader(buffer)));
    }

    public Thing get(int x, int y) {
        return map.at(x, y);
    }

    private boolean isLegalMove(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    private void moveAvatar(int oldX, int newX, int oldY, int newY) {
        map.set(oldX, oldY, emptyThing);
        map.set(newX, newY, avatar);
    }

    public void moveEast() {
        int y = avatar.y;
        int oldX = avatar.x;
        int newX = avatar.x + 1;

        if (!isLegalMove(newX, y)) {
            return;
        }

        avatar.x++;

        moveAvatar(oldX, newX, y, y);
    }

    public void moveNorth() {
        int x = avatar.x;
        int oldY = avatar.y;
        int newY = avatar.y - 1;

        if (!isLegalMove(x, newY)) {
            return;
        }

        avatar.y--;

        moveAvatar(x, x, oldY, newY);
    }

    public void moveSouth() {
        int x = avatar.x;
        int oldY = avatar.y;
        int newY = avatar.y + 1;

        if (!isLegalMove(x, newY)) {
            return;
        }

        avatar.y++;

        moveAvatar(x, x, oldY, newY);
    }

    public void moveWest() {
        int y = avatar.y;
        int oldX = avatar.x;
        int newX = avatar.x - 1;

        if (!isLegalMove(newX, y)) {
            return;
        }

        avatar.x--;

        moveAvatar(oldX, newX, y, y);
    }

    @Override
    public void render(Canvas canvas, Paint paint) {
        map.render(canvas, paint);
        // canvas.drawLine(0.0f, 0.0f, 100.0f, 100.0f, paint);
        // canvas.drawLine(0.0f, 0.0f, -100.0f, 100.0f, paint);
        // canvas.drawLine(0.0f, 0.0f, -100.0f, -100.0f, paint);
        // canvas.drawLine(10.0f, 10.0f, 20.0f, 20.0f, paint);
    }
}