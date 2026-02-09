package strategy;

import domain.Hand;
import domain.Card;

public class RiskyStrategy implements PlayStrategy {
    @Override
    public boolean shouldHit(Hand hand, Card dealerVisibleCard) {
        return hand.calculateScore() < 18;
    }
}
