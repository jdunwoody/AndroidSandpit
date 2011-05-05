package com.james;

import com.james.thing.Thing;

public class GameMap implements Displayable {
    private final int width;
    private final int height;
    private Thing[][] data;

    public GameMap(Thing[][] data) {
        this.data = data;
        this.height = data.length;
        this.width = data[0].length;
    }

    @Override
    public String display() {
        StringBuffer sb = new StringBuffer();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(data[y][x].display());
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public Thing at(int x, int y) {
        return data[y][x];
    }

    public void set(int x, int y, Thing thing) {
        data[y][x] = thing;
    }
}
