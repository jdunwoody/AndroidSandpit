package com.james.domain;

import com.james.view.CardView;

public class Card {
    private final Suit     suit;
    private final Rank     value;
    private final CardView view;

    public Card(CardView view, Rank value, Suit suit) {
        this.view = view;
        this.value = value;
        this.suit = suit;
    }

    public CardView getView() {
        return view;
    }
}
