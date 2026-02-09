package domain;

import java.util.Scanner;

public class HumanPlayer extends Player {
    private final Scanner scanner;

    public HumanPlayer(String name, Scanner scanner) {
        super(name);
        this.scanner = scanner;
    }

    @Override
    public boolean wantToHit(Card dealerVisibleCard) {
        while (true) {
            System.out.println(getName() + ", your hand: " + getHand() + ". Dealer shows: " + dealerVisibleCard);
            System.out.print("Do you want to (H)it or (S)tand? ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("H") || input.equals("HIT")) {
                return true;
            } else if (input.equals("S") || input.equals("STAND")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'H' or 'S'.");
            }
        }
    }
}
