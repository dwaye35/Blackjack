public class Player {
    private int chips = 0;
    public int wins = 0;
    public int losses = 0;
    public int totalHandsPlayed = 0;
    Hand hand;

    int getChips() {
        return chips;
    }

    void addChips(int chips) {
        this.chips += chips;
    }

    void removeChips(int numChips) {
        this.chips -= numChips;
    }

    void resetChips() {
        this.chips = 0;
    }

    void printHand() {
        System.out.println("---PLAYER HAND---");
        this.hand.printHand();
    }

    void printStats() {
        System.out.println("Hands played: " + totalHandsPlayed);
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Win rate: " + ((double)wins / (double)totalHandsPlayed) * 100 + "%");
    }
}

class Dealer {
    Hand hand;
    void printHand() {
        System.out.println("---DEALER HAND---");
        for (int i = 0; i < this.hand.cards.size(); i++) {
            if (i == 0) {
                hand.cards.get(i).printCard();
                System.out.print(" ");
            }
            else {
                System.out.print("\u2753");
            }
        }
        System.out.println("\n");
    }

    void printCompleteHand() {
        System.out.println("---DEALER HAND---");
        this.hand.printHand();
    }
}