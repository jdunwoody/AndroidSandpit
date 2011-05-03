package com.james;

import static com.james.GameState.EMPTY_THING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.james.thing.Avatar;

public class GameStateTest {
    @Test
    public void shouldShowEmptyMap() throws Exception {
        GameState state = new GameState(new File("/home/james/projects/AndroidSandpit/ATale/data/map.empty.data"));
        assertEquals(state.get(0, 0), EMPTY_THING);

        assertEquals("          \n          \n          \n          \n          \n          \n          \n          \n          \n          \n",
                state.display());
    }

    @Test
    public void shouldShowPopulatedMap() throws Exception {
        GameState state = new GameState(new File("/home/james/projects/AndroidSandpit/ATale/data/map.populated.data"));
        assertEquals("+         \n +        \n  +       \n   +      \n    +     \n     +    \n      +   \n       +  \n        + \n         +\n",
                state.display());
    }

    @Test
    public void shouldMoveNorth() throws Exception {
        GameState state = new GameState(new File("/home/james/projects/AndroidSandpit/ATale/data/map.centre.data"));
        assertEquals(state.get(4, 3), EMPTY_THING);
        assertTrue(state.get(4, 4) instanceof Avatar);
        state.moveNorth();
        assertTrue(state.get(4, 3).getClass().getName() + " should be " + Avatar.class.getName(), state.get(4, 3) instanceof Avatar);
        assertEquals(state.get(4, 4), EMPTY_THING);
    }

    @Test
    public void shouldMoveSouth() throws Exception {
        GameState state = new GameState(new File("/home/james/projects/AndroidSandpit/ATale/data/map.centre.data"));
        assertEquals(state.get(4, 5), EMPTY_THING);
        assertTrue(state.get(4, 4) instanceof Avatar);
        state.moveSouth();
        assertTrue(state.get(4, 5).getClass().getName() + " should be " + Avatar.class.getName(), state.get(4, 5) instanceof Avatar);
        assertEquals(state.get(4, 4), EMPTY_THING);
    }

    @Test
    public void shouldMoveWest() throws Exception {
        GameState state = new GameState(new File("/home/james/projects/AndroidSandpit/ATale/data/map.centre.data"));
        assertEquals(state.get(3, 4), EMPTY_THING);
        assertTrue(state.get(4, 4) instanceof Avatar);
        state.moveWest();
        assertTrue(state.get(3, 4).getClass().getName() + " should be " + Avatar.class.getName(), state.get(3, 4) instanceof Avatar);
        assertEquals(state.get(4, 4), EMPTY_THING);
    }

    @Test
    public void shouldMoveEast() throws Exception {
        GameState state = new GameState(new File("/home/james/projects/AndroidSandpit/ATale/data/map.centre.data"));
        assertEquals(state.get(5, 4), EMPTY_THING);
        assertTrue(state.get(4, 4) instanceof Avatar);
        state.moveEast();
        assertTrue(state.get(5, 4).getClass().getName() + " should be " + Avatar.class.getName(), state.get(5, 4) instanceof Avatar);
        assertEquals(state.get(4, 4), EMPTY_THING);
    }

    @Test
    public void shouldNotMoveNorthOfMap() throws Exception {
        GameState state = new GameState(new File("/home/james/projects/AndroidSandpit/ATale/data/map.empty.data"));
        state.avatar.set(1, 0);

        state.moveNorth();
        assertEquals(1, state.avatar.x);
        assertEquals(0, state.avatar.y);
    }

    @Test
    public void shouldNotMoveSouthOfMap() throws Exception {
        GameState state = new GameState(new File("/home/james/projects/AndroidSandpit/ATale/data/map.empty.data"));
        state.avatar.set(1, 9);

        state.moveSouth();
        assertEquals(1, state.avatar.x);
        assertEquals(9, state.avatar.y);
    }

    @Test
    public void shouldNotMoveEastOfMap() throws Exception {
        GameState state = new GameState(new File("/home/james/projects/AndroidSandpit/ATale/data/map.empty.data"));
        state.avatar.set(9, 4);

        state.moveEast();
        assertEquals(9, state.avatar.x);
        assertEquals(4, state.avatar.y);
    }

    @Test
    public void shouldNotMoveWestOfMap() throws Exception {
        GameState state = new GameState(new File("/home/james/projects/AndroidSandpit/ATale/data/map.empty.data"));
        state.avatar.set(0, 4);

        state.moveWest();
        assertEquals(0, state.avatar.x);
        assertEquals(4, state.avatar.y);
    }

    @Test
    public void shouldNotMoveOnTopOfMonster() throws Exception {
        GameState state = new GameState("7*");
        state.avatar.set(0, 4);

        state.moveWest();
        assertEquals(0, state.avatar.x);
        assertEquals(4, state.avatar.y);
    }

}
