package com.james;

import org.junit.Test;

public class GameStateTest {
    @Test
    public void shouldShowEmptyMap() throws Exception {
        GameState state = new GameState("/home/james/projects/AndroidSandpit/ATale/data/map.data");
        System.out.println(state.toString());
    }
}
