public class Player {
    private int chips = 0;
    private int wins = 0;
    private int losses = 0;
    private int totalHandsPlayed = 0;

    int getChips() {
        return chips;
    }

    void addChips(int chips) {
        this.chips += chips;
    }

    void resetChips() {
        this.chips = 0;
    }
}

class Dealer {

}
