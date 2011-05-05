package com.james;

import com.james.thing.Monster;

public class MonsterBuilder {
    public Monster build(char type) {
        switch (type) {
            case '7':
                return new Monster("Monster 7", type);
            default:
                throw new RuntimeException("Unknown monster type: " + type);
        }
    }
}
