package com.james.view;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import android.content.res.Resources;

import com.james.R;
import com.james.domain.Card;
import com.james.domain.Pack;
import com.james.domain.Rank;
import com.james.domain.Suit;

public class PackFactory {

    private final CardFactory cardFactory;

    public PackFactory() {
        cardFactory = new CardFactory();
    }

    public Pack newInstance(Resources res) {
        List<Card> cards = new ArrayList<Card>();

        for (Suit suit : EnumSet.allOf(Suit.class)) {
            for (Rank rank : EnumSet.allOf(Rank.class)) {
                cards.add(addCard(res, suit, rank));
            }
        }

        return new Pack(cards);
    }

    private Card addCard(Resources res, Suit suit, Rank rank) {
        return cardFactory.newInstance(res, R.drawable.king_hearts, rank, suit);
    }
}
