package strategy;

import domain.Hand;
import domain.Card;

public interface PlayStrategy {
    boolean shouldHit(Hand hand, Card dealerVisibleCard);
}
