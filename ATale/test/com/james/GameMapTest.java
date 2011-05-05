package com.james;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.james.thing.Thing;

public class GameMapTest {
    @Test
    public void should() {
        Thing[][] data = new Thing[0][];
        GameMap map = new GameMap(data);
        assertEquals("", map.display());
    }
}
