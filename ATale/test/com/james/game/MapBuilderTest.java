package com.james.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.james.game.GameMapBuilder;
import com.james.thing.Avatar;

public class MapBuilderTest {
    GameMapBuilder mapBuilder = new GameMapBuilder();
    Avatar         avatar     = new Avatar(0, 0);

    @Test
    public void shouldBuildFromEmptyBuffer() {
        assertEquals(" 7\n", mapBuilder.build(avatar, "07").display());
    }
}
