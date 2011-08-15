package com.james.domain;

import com.james.view.CardView;

public class Card {
    private final Rank     rank;
    private final Suit     suit;
    private final CardView view;

    public Card(CardView view, Rank rank, Suit suit) {
        this.view = view;
        this.rank = rank;
        this.suit = suit;
    }

    public CardView getView() {
        return view;
    }

    @Override
    public String toString() {
        return suit + " " + rank;
    }
}