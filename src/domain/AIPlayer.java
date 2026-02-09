package domain;

import strategy.PlayStrategy;

public class AIPlayer extends Player {
    private final PlayStrategy strategy;

    public AIPlayer(String name, PlayStrategy strategy) {
        super(name);
        this.strategy = strategy;
    }

    @Override
    public boolean wantToHit(Card dealerVisibleCard) {
        return strategy.shouldHit(getHand(), dealerVisibleCard);
    }
}
