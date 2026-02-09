package domain;

public abstract class Player {
    private String name;
    private Hand hand;
    private int wins;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
        this.wins = 0;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public void clearHand() {
        hand.clear();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public int getWins() {
        return wins;
    }

    public void incrementWins() {
        this.wins++;
    }

    public abstract boolean wantToHit(Card dealerVisibleCard);
}
