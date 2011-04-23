package com.james;

import java.io.BufferedReader;
import java.io.FileReader;

public class GameState {
    private static final int WIDTH  = 10;
    private static final int HEIGHT = 10;
    private char             map[][];

    public GameState(String mapFilename) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(mapFilename));

        char row[] = new char[WIDTH];

        map = new char[HEIGHT][];
        for (int y = 0; y < HEIGHT; y++) {
            reader.read(row);
            map[y] = new char[WIDTH];
            for (int x = 0; x < WIDTH; x++) {
                map[y][x] = (char) row[x];
            }
        }

        reader.close();
    }

    public void moveForward() {
    }

    public void moveBack() {
    }

    public void moveLeft() {
    }

    public void moveRight() {
    }

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