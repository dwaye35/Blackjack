import java.util.ArrayDeque;

public class MegaDeck {
    private ArrayDeque<Card> megaDeck;
    public MegaDeck() {
        megaDeck = new ArrayDeque<Card>();
        //create 4 decks one at a time
        //shuffle each deck, then add to mega deck
        for (int i = 0; i < 4; i++) {
            Deck deck = new Deck();
            Deck.shuffleDeck(deck.getDeck());
            megaDeck.addAll(deck.getDeck());
        }
        //shuffle entire megadeck
        Deck.shuffleDeck(megaDeck);
    }

    public Card pop(){
        return this.megaDeck.pop();
    }
}