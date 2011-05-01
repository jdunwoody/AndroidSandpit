package com.james;

import java.io.BufferedReader;
import java.io.FileReader;

import com.james.thing.Avatar;
import com.james.thing.Empty;
import com.james.thing.Thing;
import com.james.thing.Wall;

public class GameState {
    public static final Thing EMPTY_THING = new Empty();
    private static final int  HEIGHT      = 10;
    public static final Thing WALL_THING  = new Wall();
    private static final int  WIDTH       = 10;
    Avatar                    avatar      = new Avatar(0, 0);
    private Thing             map[][];

    public GameState(String mapFilename) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(mapFilename));

        char row[] = new char[WIDTH + 1];

        map = new Thing[HEIGHT][];
        for (int y = 0; y < HEIGHT; y++) {
            reader.read(row);
            map[y] = new Thing[WIDTH];
            for (int x = 0; x < WIDTH; x++) {
                map[y][x] = build(row[x], x, y);
            }
        }

        reader.close();
    }

    private Thing build(char val, int x, int y) {
        switch (val) {
            case '0':
                return EMPTY_THING;
            case '+':
                return WALL_THING;
            case '*':
                avatar.set(x, y);
                return avatar;
            default:
                throw new RuntimeException("Unknown data value: " + val);
        }
    }

    public Thing get(int x, int y) {
        return map[y][x];
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
        map[oldY][oldX] = EMPTY_THING;
        map[newY][newX] = avatar;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                sb.append(map[y][x]);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}