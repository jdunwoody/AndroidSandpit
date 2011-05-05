package com.james;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleGame {
    public static void main(String args[]) throws Exception {
        new SimpleGame().run();
    }

    private GameState      gameState;
    private BufferedReader reader;

    public SimpleGame() throws Exception {
        gameState = new GameState("/home/james/projects/AndroidSandpit/ATale/data/map.centre.data");
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private char getInput(String message) throws IOException {
        char input = ' ';
        while (true) {
            System.out.println(message);

            String line = reader.readLine().trim().toUpperCase();
            if (line.length() == 0) {
                continue;
            } else {
                input = line.charAt(0);
                break;
            }
        }
        return input;
    }

    private void goEast() {
        gameState.moveEast();
    }

    private void goNorth() {
        gameState.moveNorth();
    }

    private void goSouth() {
        gameState.moveSouth();
    }

    private void goWest() {
        gameState.moveWest();
    }

    private void run() throws IOException {
        while (true) {
            System.out.println(gameState.display());
            navigate();
        }
    }

    private void navigate() throws IOException {
        char input = getInput("(N) (S) (W) (E) (Q)");
        if (input == 'Q') {
            quitGame();
        }
        switch (input) {
            case 'N':
                goNorth();
                break;
            case 'S':
                goSouth();
                break;
            case 'W':
                goWest();
                break;
            case 'E':
                goEast();
                break;
            default:
        }
    }

    private void quitGame() {
        System.exit(0);
    }
}
