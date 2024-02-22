import java.util.Scanner;

public class GameManager {
    Player player = new Player();
    Dealer dealer = new Dealer();
    Scanner scn = new Scanner(System.in);

    public void startGame() {
        boolean gameActive = true;
        System.out.print("Welcome to Blackjack.\n");
        while (gameActive == true) {
            player.resetChips();
            System.out.print("Enter an amount of chips to start with (1 - 10,000): ");
            player.addChips(scn.nextInt());
            if (player.getChips() <= 0 || player.getChips() > 10000) {
                System.out.println("Invalid number of chips...\n");
                continue;
            }
            while (player.getChips() > 0) {
                System.out.println("Player bank: " + player.getChips() + "\nStarting hand..\n\n");
                dealHand();
            }
        }
    }

    void dealHand() {

    }

}
