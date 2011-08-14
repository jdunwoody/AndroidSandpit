package com.james.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<Card>(13);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> cards() {
        return cards;
    }

    public Card getCard(int i) {
        return cards.get(i);
    }
}
