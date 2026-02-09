package service;

import domain.Card;
import domain.Deck;
import domain.Player;

import repository.PlayerRepository;
import java.util.ArrayList;
import java.util.List;

public class GameService {
    private final Deck deck;
    private final List<Player> players;
    private final PlayerRepository playerRepository;

    public GameService(PlayerRepository playerRepository) {
        this.deck = new Deck();
        this.players = new ArrayList<>();
        this.playerRepository = playerRepository;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void playRound() {
        if (players.isEmpty()) {
            System.out.println("No players!");
            return;
        }

        // Сброс руки и колоды
        deck.shuffle();
        for (Player player : players) {
            player.clearHand();
        }

        System.out.println("\n--- New Round ---");

        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.addCard(deck.deal());
            }
        }

        Player dealer = players.getLast();
        Card dealerVisibleCard = dealer.getHand().getCards().getFirst(); // Показывает первую карту

        // Ход
        for (Player player : players) {
            System.out.println("\n" + player.getName() + "'s turn.");
            if (player == dealer) {
                continue;
            }
            playTurn(player, dealerVisibleCard);
        }

        // Ход крупье
        System.out.println("\nDealer's turn.");
        System.out.println("Dealer's hand: " + dealerVisibleCard + " and [Hidden]");
        // Выскрытье карты
        System.out.println("Dealer reveals hand: " + dealer.getHand());
        playTurn(dealer, dealerVisibleCard); // Логика крупье

        determineWinners(dealer);
    }

    private void playTurn(Player player, Card dealerVisibleCard) {
        if (player.getScore() == 21 && player.getHand().size() == 2) {
            System.out.println(player.getName() + " has Blackjack!");
            return;
        }

        while (player.getScore() < 21) {
            boolean hit = player.wantToHit(dealerVisibleCard);
            if (hit) {
                Card card = deck.deal();
                player.addCard(card);
                System.out.println(player.getName() + " hits and gets " + card + ". Score: " + player.getScore());
            } else {
                System.out.println(player.getName() + " stands.");
                break;
            }
        }

        if (player.getScore() > 21) {
            System.out.println(player.getName() + " busted!");
        }
    }

    private void determineWinners(Player dealer) {
        System.out.println("\n--- Results ---");
        int dealerScore = dealer.getScore();
        System.out.println("Dealer score: " + dealerScore + (dealerScore > 21 ? " (Bust)" : ""));

        for (Player player : players) {
            if (player == dealer)
                continue;

            int playerScore = player.getScore();
            System.out.println(player.getName() + " score: " + playerScore + (playerScore > 21 ? " (Bust)" : ""));

            if (playerScore > 21) {
                System.out.println(player.getName() + " loses (Bust).");
                dealer.incrementWins();
            } else if (dealerScore > 21) {
                System.out.println(player.getName() + " wins (Dealer Bust)!");
                player.incrementWins();
            } else if (playerScore > dealerScore) {
                System.out.println(player.getName() + " wins!");
                player.incrementWins();
            } else if (playerScore < dealerScore) {
                System.out.println(player.getName() + " loses.");
                dealer.incrementWins();
            } else {
                System.out.println(player.getName() + " pushes (Tie).");
            }

            // Сохранение резов игры
            playerRepository.save(player);
        }

        playerRepository.save(dealer);

        printStats();
    }

    private void printStats() {
        System.out.println("\n--- Current Stats ---");
        for (Player p : players) {
            System.out.println(p.getName() + " Wins: " + p.getWins());
        }
    }

    public void deletePlayerHistory(String name) {
        playerRepository.deleteByName(name);
        System.out.println("Player " + name + " history deleted.");
    }
}
