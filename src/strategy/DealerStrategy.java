package strategy;

import domain.Hand;
import domain.Card;

public class DealerStrategy implements PlayStrategy {
    @Override
    public boolean shouldHit(Hand hand, Card dealerVisibleCard) {
        //Всегда берёт если на руке < 16
        return hand.calculateScore() < 16;
    }
}
