import java.util.ArrayList;

public class Hand {
    ArrayList<Card> cards;
    int numAces11 = 0;
    MegaDeck deck;
    int totalValue;
    Hand(MegaDeck deck) {
        cards = new ArrayList<Card>(2);
        numAces11 = 0;
        this.deck = deck;
        totalValue = 0;
    }

    void dealCard() {
        totalValue = 0;
        cards.add(deck.pop());
        //check if ace is present
        for (Card card : cards) {
            if (card.name == "ACE")
                numAces11++;
        }
        totalValue = calcTotalValue();
    }

    int calcTotalValue() {
        int total = 0;
        for (Card card : cards) {
            total += card.value;
        }
        while (total > 21 && numAces11 > 0) {
            total -= 10;
            numAces11 -= 1;
        }
        return total;
    }

    void printHand() {
        for (int i = 0; i < this.cards.size(); i++) {
            cards.get(i).printCard();
            System.out.print(" ");
        }
        if (cards.size() > 1)
            System.out.println("(" + totalValue + ")");
        System.out.println("");
    }
}
