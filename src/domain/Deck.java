package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import exception.DeckEmptyException;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        cards.clear();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        if (cards.isEmpty()) {
            throw new DeckEmptyException("Deck is empty!");
        }
        return cards.removeLast(); // Deal from top (end of list)
    }

}
