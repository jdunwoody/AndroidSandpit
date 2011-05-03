package com.james;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.james.thing.Avatar;
import com.james.thing.Empty;
import com.james.thing.Thing;
import com.james.thing.Wall;

public class GameState implements Displayable {
    public static final Thing EMPTY_THING = new Empty();
    static final int          HEIGHT      = 10;
    public static final Thing WALL_THING  = new Wall();
    static final int          WIDTH       = 10;
    Avatar                    avatar      = new Avatar(0, 0);
    private GameMap           map;
    private GameMapBuilder    mapBuilder  = new GameMapBuilder();

    public GameState(File file) throws Exception {
        char[] buffer = new char[1024];
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int size = reader.read(buffer);

        map = mapBuilder.build(avatar, new String(buffer, 0, size));

        reader.close();
    }

    public GameState(String buffer) {
        map = mapBuilder.build(avatar, new String(buffer));
    }

    public Thing get(int x, int y) {
        return map.at(x, y);
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

    private boolean isLegalMove(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    private void moveAvatar(int oldX, int newX, int oldY, int newY) {
        map.set(oldX, oldY, EMPTY_THING);
        map.set(newX, newY, avatar);
    }

    @Override
    public String display() {
        return map.display();
    }
}