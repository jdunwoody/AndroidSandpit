package com.james.thing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AvatarTest {
    @Test
    public void shouldTurnLeft() {
        Avatar avatar = new Avatar(0, 0);

        assertEquals(avatar.direction, Avatar.NORTH);
        avatar.turnLeft();
        assertEquals(avatar.direction, Avatar.WEST);
        avatar.turnLeft();
        assertEquals(avatar.direction, Avatar.SOUTH);
        avatar.turnLeft();
        assertEquals(avatar.direction, Avatar.EAST);
    }

    @Test
    public void shouldTurnRight() {
        Avatar avatar = new Avatar(0, 0);

        assertEquals(avatar.direction, Avatar.NORTH);
        avatar.turnRight();
        assertEquals(avatar.direction, Avatar.EAST);
        avatar.turnRight();
        assertEquals(avatar.direction, Avatar.SOUTH);
        avatar.turnRight();
        assertEquals(avatar.direction, Avatar.WEST);
    }
}
