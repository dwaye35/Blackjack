import java.util.Scanner;



public class GameManager {
    Player player = new Player();
    Dealer dealer = new Dealer();
    Scanner scn = new Scanner(System.in);
    MegaDeck deck = new MegaDeck();
    boolean playing = true;

    public void startGame() throws InterruptedException {
        System.out.print("Welcome to Blackjack.\n");
        while (playing) {
            player.resetChips();
            System.out.print("Enter an amount of chips to start with (1 - 10,000): ");
            player.addChips(scn.nextInt());
            player.totalChipsAdded += player.getChips();
            if (player.getChips() <= 0 || player.getChips() > 10000) {
                System.out.println("Invalid number of chips...\n");
                continue;
            }
            while (playing) {
                if (player.getChips() < 10) {
                    System.out.println("1 to go to cashier\n" +
                            "Any other key to exit");
                    char exitVal = scn.next().charAt(0);
                    if (exitVal != '1') {
                        playing = false;
                        break;
                    }
                    cashier();
                }
                dealHand();
            }
        }
    }

    void dealHand() throws InterruptedException {
        while (playing) {
            int playerOpt = 0;
            while (player.totalHandsPlayed > 0 && playerOpt != 1 && playerOpt != 3) {
                System.out.println("1 to play again\n" +
                        "2 to print statistics\n" +
                        "3 to quit");
                playerOpt = scn.nextInt();
                if (playerOpt == 2) {
                    player.printStats();
                }
                else if (playerOpt == 3) {
                    playing = false;
                    playerOpt = 3;
                    break;
                }
            }
            if (!playing)
                break;
            System.out.println("Player Chips: " + player.getChips());
            if (player.getChips() < 10) {
                System.out.println("Not enough chips.. Removing player.");
                break;
            }
            System.out.print("Enter bet amount (minimum 10): ");
            int betAmount = scn.nextInt();
            if (betAmount < 10) {
                System.out.println("Bet too low.");
                continue;
            } else if (betAmount > player.getChips()) {
                System.out.println("Not enough chips in Bank (" + player.getChips() + ")");
                continue;
            }
            player.hand = new Hand(deck);
            dealer.hand = new Hand(deck);
            //Deal 1st 2 cards and print to screen
            System.out.println("Dealing. Good luck...");
            for (int i = 0; i < 2; i++) {
                if (i == 1) {
                    Thread.sleep(2000);
                    System.out.println("\n\n\n\n\n\n\n\n\n\n");
                }
                player.hand.dealCard();
                if (i == 0)
                    Thread.sleep(1700);
                player.printHand();
                dealer.hand.dealCard();
                if (i == 0)
                    Thread.sleep(1700);
                dealer.printHand();
            }

            boolean playersTurn = true;
            boolean playerBust = false;
            boolean blackjack = false;
            if (player.hand.totalValue == 21) {
                System.out.println("Blackjack!!");
                blackjack = true;
                playersTurn = false;
            }
            while (playersTurn) {
                System.out.println("h to hit\ns to stand");
                if (player.hand.cards.size() == 2)
                    System.out.println("d to double:");
                char playerChoice = scn.next().charAt(0);
                switch (playerChoice) {
                    case 'h':
                        player.hand.dealCard();
                        player.printHand();
                        dealer.printHand();
                        Thread.sleep(2500);
                        if (player.hand.totalValue > 21) {
                            System.out.println("Busted! Player loses.");
                            playersTurn = false;
                            playerBust = true;
                            Thread.sleep(2000);
                        }
                        else if (player.hand.totalValue == 21)
                            playersTurn = false;
                        break;
                    case 's':
                        playersTurn = false;
                        break;
                    case 'd':
                        if (player.hand.cards.size() > 2) {
                            System.out.println("Can only double on the first turn!");
                        }
                        else if (player.getChips() < (betAmount * 2)) {
                            System.out.println("Not enough chips to double bet..\n" +
                                    "Player Bank: " + player.getChips() +
                                    "\nDouble amount: " + betAmount * 2);
                        }
                        else {
                            System.out.println("New bet size: " + betAmount * 2 +
                                    "\n Good luck.");
                            Thread.sleep(2500);
                            betAmount *= 2;
                            player.hand.dealCard();
                            player.printHand();
                            dealer.printHand();
                            Thread.sleep(2500);
                            if (player.hand.totalValue > 21) {
                                System.out.println("Busted! Player loses.");
                                playerBust = true;
                            }
                            playersTurn = false;
                        }
                        break;
                }
            } //end of player's turn
            //now to dealer
                if (dealer.hand.totalValue > 16) {
                    player.printHand();
                    dealer.printCompleteHand();
                    Thread.sleep(3000);
                } else {
                    dealer.printCompleteHand();
                    while (dealer.hand.totalValue < 17) {
                        dealer.hand.dealCard();
                        Thread.sleep(3000);
                        dealer.printCompleteHand();
                        if (dealer.hand.totalValue > 17)
                            Thread.sleep(1500);
                    }
                    if (!playerBust && !blackjack) {
                        System.out.print("Enter to continue..");
                        scn.nextLine();
                        scn.nextLine();
                    }
                    player.printHand();
                    dealer.printCompleteHand();
                    Thread.sleep(2500);
                }
                settleGame(betAmount);
        }
    }

    private void settleGame(int betAmount) {
        if (player.hand.totalValue == 21 && player.hand.cards.size() == 2) {
            betAmount = (int)(betAmount * 1.5);
            System.out.println("Blackjack pays 3/2!\n" +
                    "You win " + betAmount + " chips!");
            player.addChips(betAmount);
            player.wins++;
        }
        else if (player.hand.totalValue > 21) {
            System.out.println("Player busted");
            player.removeChips(betAmount);
            player.losses++;
        }
        else if (dealer.hand.totalValue > 21) {
            System.out.println("Dealer busts!\nPlayer wins " + betAmount + " chips!");
            player.addChips(betAmount);
            player.wins++;
        }
        else if (player.hand.totalValue > dealer.hand.totalValue) {
            System.out.println("Player wins " + betAmount + " chips!");
            player.addChips(betAmount);
            player.wins++;
        }
        else if (player.hand.totalValue < dealer.hand.totalValue) {
            System.out.println("Dealer wins.");
            player.losses++;
            player.removeChips(betAmount);
        }
        else if (player.hand.totalValue == dealer.hand.totalValue) {
            System.out.println("Push!");
        }
        player.totalHandsPlayed++;
    }

    void cashier() throws InterruptedException {
        System.out.print("---CASHIER---\n" +
                "Enter the amount of chips: ");
        int chipsToAdd = 0;
        chipsToAdd = scn.nextInt();
        player.addChips(chipsToAdd);
        player.totalChipsAdded += chipsToAdd;
        System.out.println("\nAdded " + player.getChips() + " chips.\n" +
                "Good luck..");
        Thread.sleep(1200);

    }
}