package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void clear() {
        cards.clear();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public int calculateScore() {
        int score = 0;
        int aceCount = 0;

        for (Card card : cards) {
            int value = card.getRank().getValue();
            if (card.getRank() == Card.Rank.ACE) {
                aceCount++;
            }
            score += value;
        }

        while (score > 21 && aceCount > 0) {
            score -= 10;
            aceCount--;
        }

        return score;
    }

    @Override
    public String toString() {
        return cards.toString() + " (Score: " + calculateScore() + ")";
    }
    
    public int size() {
        return cards.size();
    }
}
