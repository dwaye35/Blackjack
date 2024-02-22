public class Card {
    int value;
    char suit;
    char suitUnicodeSymbol;
    String name;
    public Card(int value, char suit, String name) {
        if (value < 2 || value > 11) {
            System.out.println("Invalid card value (" + value + ") . Exiting...");
            System.exit(1);
        }
        else if (suit != 'H' && suit != 'D' && suit != 'S' && suit != 'C') {
            System.out.println("Invalid suit value (" + suit + ") . Exiting...");
            System.exit(1);
        }

        this.value = value;
        this.suit = suit;
        this.name = name;
        switch (suit) {
            case 'H':
                this.suitUnicodeSymbol = '\u2764'; break;
            case 'D':
                this.suitUnicodeSymbol = '\u2666'; break;
            case 'S':
                this.suitUnicodeSymbol = '\u2660'; break;
            case 'C':
                this.suitUnicodeSymbol = '\u2663'; break;
        }
    }
}
