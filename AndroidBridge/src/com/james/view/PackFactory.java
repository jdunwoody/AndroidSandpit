package com.james.view;

import static com.james.logging.Logging.log;

import java.lang.reflect.Field;
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
        String cardResource = rank + "_" + suit;
        log("CardResourceFile: " + cardResource);

        Field cardResourceField = null;
        try {
            cardResourceField = R.drawable.class.getField(cardResource);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        Card card = null;
        try {
            card = cardFactory.newInstance(res, cardResourceField.getInt(null), rank, suit);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return card;
    }
}
