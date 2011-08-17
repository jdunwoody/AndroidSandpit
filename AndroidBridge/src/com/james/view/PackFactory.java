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

    public PackFactory(Screen screen) {
        cardFactory = new CardFactory(screen);
    }

    public Pack newInstance(Resources res) {
        List<Card> cards = new ArrayList<Card>();

        try {
            for (Suit suit : EnumSet.allOf(Suit.class)) {
                for (Rank rank : EnumSet.allOf(Rank.class)) {
                    cards.add(addCard(res, suit, rank));
                }
            }
        } catch (Exception e) {
            log(e.getMessage());
            throw new RuntimeException(e);
        }

        return new Pack(cards);
    }

    private Card addCard(Resources res, Suit suit, Rank rank) throws SecurityException, NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        String cardResource = (rank + "_" + suit).toLowerCase();

        Field cardResourceField = R.drawable.class.getField(cardResource);

        return cardFactory.newInstance(res, cardResourceField.getInt(null), rank, suit);
    }
}
