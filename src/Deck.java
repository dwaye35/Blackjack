import java.util.ArrayDeque;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
    private ArrayDeque<Card> deck;
    private int[] cardValues;
    private String[] names;
    private char[] suits;

    public Deck() {
        deck = new ArrayDeque<Card>(52);
        cardValues = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
        names = new String[]{"TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN",
                "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE"};
        suits = new char[]{'H', 'D', 'S', 'C'};
        createDeck();
    }

    private void createDeck() {
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < cardValues.length; j++) {
                deck.add(new Card(cardValues[j], suits[i], names[j]));
            }
        }
    }

    public static void shuffleDeck(ArrayDeque<Card> deck) {
        Card[] array = deck.toArray(new Card[deck.size()]);
        for (int q = 0; q < 5; q++) {
            //Fisher-Yates shuffle (5x)
            for (int i = array.length - 1; i > 0; i--) {
                int j = ThreadLocalRandom.current().nextInt(i + 1);
                Card temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        deck.clear();
        for (Card element : array) {
            deck.add(element);
        }
    }
    protected ArrayDeque<Card> getDeck() {
        return this.deck;
    }

    public void printDeck() {
        System.out.println("TOTAL SIZE OF DECK: " + deck.size());
        for (Card card : deck){
            System.out.println(card.name + " " + card.suitUnicodeSymbol);
        }
    }
}