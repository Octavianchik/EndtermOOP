package app;

import domain.AIPlayer;
import domain.HumanPlayer;
import domain.Player;
import repository.InMemoryPlayerRepository;
import repository.PlayerRepository;
import service.GameService;
import strategy.DealerStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Blackjack!");

        // Инициализация всякого
        PlayerRepository playerRepository = new InMemoryPlayerRepository();
        GameService gameService = new GameService(playerRepository);
        Scanner scanner = new Scanner(System.in);

        // Сэтап Player
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine().trim();
        Player human = new HumanPlayer(playerName, scanner);
        gameService.addPlayer(human);

        // Сэтап Dealer
        Player dealer = new AIPlayer("Dealer", new DealerStrategy());
        gameService.addPlayer(dealer);

        // Луп с игрой
        boolean playing = true;
        while (playing) {
            try {
                gameService.playRound();
            } catch (Exception e) {
                System.err.println("An error occurred during the game: " + e.getMessage());
                e.printStackTrace();
            }

            System.out.print("\nPlay another round? (Y)es / (N)o / (D)elete history & exit: ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("D") || input.equals("DELETE")) {
                gameService.deletePlayerHistory(human.getName());
                playing = false;
            } else if (!input.equals("Y") && !input.equals("YES")) {
                playing = false;
            }
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
