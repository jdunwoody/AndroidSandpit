package com.james.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.james.view.Player;

public class Pack {
    private final List<Card>        cards;
    private final Map<Player, Hand> hands = new HashMap<Player, Hand>();

    public Pack(List<Card> cards) {
        this.cards = cards;
    }

    public void deal() {
        Hand north = new Hand();
        Hand south = new Hand();
        Hand west = new Hand();
        Hand east = new Hand();

        for (int i = 0; i < 13; i++) {
            north.add(cards.get(4 * i));
            south.add(cards.get(4 * i + 1));
            west.add(cards.get(4 * i + 2));
            east.add(cards.get(4 * i + 3));
        }

        hands.put(Player.NORTH, north);
        hands.put(Player.SOUTH, south);
        hands.put(Player.WEST, west);
        hands.put(Player.EAST, east);
    }

    public Hand getHand(Player player) {
        return hands.get(player);
    }

    public void shuffle() {

    }
}
